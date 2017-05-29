package com.ponta.tutorial.objects.animals;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.ponta.tutorial.framework.GameObject;
import com.ponta.tutorial.framework.ObjectId;
import com.ponta.tutorial.window.Animation;
import com.ponta.tutorial.window.Camera;
import com.ponta.tutorial.window.Handler;

public class Mistret extends Animals {

	public Mistret(float x, float y, Handler handler, Camera cam, ObjectId id) {
		super(x, y, handler, cam, id);
		width = 81;
		height = 63;
		velX = 1;
		animalsAnimation = new Animation(5, tex.mistret[0], tex.mistret[1], tex.mistret[2]);
	}
	
	public void tick(LinkedList<GameObject> object) {
		super.tick(object);

		x += velX;
		y += velY;		
		if(velX < 0) facing = -1;
		else if(velX > 0) facing = 1;		
		if(falling || jumping)
		{
			velY += gravity;			
			if(velY > MAX_SPEED)
				velY = MAX_SPEED;
		}
		Collision(object);		
	}

	public void render(Graphics g){
		super.render(g);
//		Graphics2D g2d = (Graphics2D) g;
//		g2d.draw(getBounds());
//		g2d.draw(getBoundsTop());
//		g2d.draw(getBoundsLeft());
//		g2d.draw(getBoundsRight());
	}
	
	
	public Rectangle getBounds() {
		return new Rectangle((int) (x + width / 11),(int) ((int)y+(height/2)), (int) (width - width / 6), (int) height/2);
	}	
	public Rectangle getBoundsTop() {
		return new Rectangle((int) (x + width / 11),(int) y, (int) (width - width / 6), (int) height/2);
	}
	public Rectangle getBoundsRight() {
		return new Rectangle((int) ((int) x+width-5),(int) (y + height / 2 - 3), (int) 5, (int) height / 2);
	}	
	public Rectangle getBoundsLeft() {
		return new Rectangle((int) x,(int) (y + height / 2 - 3), (int) 5, (int) height / 2);
	}
	
}
