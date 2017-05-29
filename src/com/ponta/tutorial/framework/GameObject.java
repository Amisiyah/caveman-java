package com.ponta.tutorial.framework;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.ponta.tutorial.objects.animals.Mammoth;
import com.ponta.tutorial.window.Handler;

public abstract class GameObject{
	
	protected Handler handler;
	protected float x;
	protected float y;
	protected ObjectId id;
	protected float velX = 0, velY = 0;
	protected boolean falling = true;
	protected boolean jumping = false;
	protected int facing = 1;
	protected boolean remove = false;
	protected float gravity = 0.5f;
	protected final float MAX_SPEED = 10;	
	protected float width = 32,height = 32;	
	protected int hp = 3;
	protected int hurtDelay = 0;


	public GameObject(float x, float y, ObjectId id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public void tick(LinkedList<GameObject> object){
		if(hurtDelay > 0) hurtDelay--;
		if(hp <= 0)	handler.removeObject(this);
	}

	public void render(Graphics g){
	}
	
	public float getX() { return x; }
	public float getY() { return y; }
	public void setX(float x) { this.x = x; }
	public void setY(float y) { this.y = y; }
	public float getVelX() { return velX; }
	public float getVelY() { return velY; }
	public void setVelX(float velX) { this.velX = velX; }
	public void setVelY(float velY) { this.velY = velY; }
	
	public int getFacing() { return facing; }
	public ObjectId getId() { return id; }
	public boolean isFalling() { return falling; }
	public void setFalling(boolean falling) { this.falling = falling; }
	public boolean isJumping() { return jumping; }
	public void setJumping(boolean jumping) { this.jumping = jumping; }
	public boolean removed() { return remove; }
	public void remove() { this.remove = true;}

	protected void Collision (LinkedList<GameObject> object) {
		for(int i=0; i < handler.object.size(); i++ ) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Block) {
				if(getBoundsTop().intersects(tempObject.getBounds())) {
					y = tempObject.getY() + 32;
					velY = 0;
				}
				
				if(getBounds().intersects(tempObject.getBounds())) {
					y = tempObject.getY() - height;
					velY = 0;
					falling = false;
					jumping = false;
				} else
					falling = true;				
				if(getBoundsRight().intersects(tempObject.getBounds())) { x = tempObject.getX() - width; }				
				if(getBoundsLeft().intersects(tempObject.getBounds())) {	x = tempObject.getX() + 35; }
				
			} else if(tempObject.getId() == ObjectId.Flag) { 
				if(getBounds().intersects(tempObject.getBounds())) { handler.switchLevel(); }			
			} else if(tempObject.getId() == ObjectId.LavaI){
				
				if(getBoundsTop().intersects(tempObject.getBounds())) hp = 0;
				if(getBounds().intersects(tempObject.getBounds())) hp = 0;
				if(getBoundsRight().intersects(tempObject.getBounds()))	hp = 0;
				if(getBoundsLeft().intersects(tempObject.getBounds())) hp = 0;
			}
		}
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) ((int) x+(width/2)-((width/2)/2)),(int) ((int)y+(height/2)), (int) width/2, (int) height/2);
	}	
	public Rectangle getBoundsTop() {
		return new Rectangle((int) ((int) x+(width/2)-((width/2)/2)),(int) y + (int) height/4, (int) width/2, (int) height/4);
	}
	public Rectangle getBoundsRight() {
		return new Rectangle((int) ((int) x+width-5),(int) y+28, (int) 5, (int) height-32);
	}	
	public Rectangle getBoundsLeft() {
		return new Rectangle((int) x,(int) y+28, (int) 5, (int) height-32);
	}
	
	
	protected void hurt(GameObject e, int dmg) {
		if(hurtDelay == 0) e.hp -= dmg;
		hurtDelay = 15;
	}
	
}
