package com.ponta.tutorial.objects.animals;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.ponta.tutorial.framework.GameObject;
import com.ponta.tutorial.framework.ObjectId;
import com.ponta.tutorial.framework.Texture;
import com.ponta.tutorial.objects.Player;
import com.ponta.tutorial.window.Animation;
import com.ponta.tutorial.window.Camera;
import com.ponta.tutorial.window.Game;
import com.ponta.tutorial.window.Handler;

public class Animals extends GameObject{
	protected Camera cam;	
	protected Texture tex =  Game.getInstance();
	protected Animation animalsAnimation;
	
	public Animals(float x, float y, Handler handler, Camera cam, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		this.cam = cam;
	}

	public void tick(LinkedList<GameObject> object) {
		super.tick(object);
		animalsAnimation.runAnimation();
	}

	public void render(Graphics g) {
		animalsAnimation.drawAnimation(g, (int)x, (int)y);
	}

	public Rectangle getBounds() { return new Rectangle((int) x, (int) y, 87, 47);	}

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
				
				if(getBoundsRight().intersects(tempObject.getBounds())) {
					velX = -4;
					x = tempObject.getX() - width;
				}
				
				if(getBoundsLeft().intersects(tempObject.getBounds())) { 
					velX = 4;
					x = tempObject.getX() + 35;
				}
				
			} else if(tempObject.getId() == ObjectId.Flag) { 
				if(getBounds().intersects(tempObject.getBounds())) { handler.switchLevel(); }			
			} else if(tempObject instanceof Player){
				
				if(getBoundsRight().intersects(tempObject.getBounds())) {
					hurt(tempObject, 1);
					tempObject.setJumping(true);
					tempObject.setVelY(-10);
					tempObject.setVelX(+5);
				}
				
				if(getBoundsLeft().intersects(tempObject.getBounds())) { 
					hurt(tempObject, 1);
					tempObject.setJumping(true);
					tempObject.setVelY(-10);
					tempObject.setVelX(-5);
				}
				
				if(getBoundsTop().intersects(tempObject.getBounds())) {
					tempObject.setJumping(true);
					tempObject.setVelY(-15);
					hurt(this, 1);
				}
				
			} else if(tempObject.getId() == ObjectId.LavaI){
				if(this.getBoundsTop().intersects(tempObject.getBounds())) handler.removeObject(this);
				if(this.getBounds().intersects(tempObject.getBounds())) handler.removeObject(this);
				if(this.getBoundsRight().intersects(tempObject.getBounds())) handler.removeObject(this);
				if(this.getBoundsLeft().intersects(tempObject.getBounds())) handler.removeObject(this);
			}
			
			
		}
		
	}
	


}
