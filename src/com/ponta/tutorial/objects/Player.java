package com.ponta.tutorial.objects;

import java.awt.Color;
//import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.ponta.tutorial.framework.GameObject;
import com.ponta.tutorial.framework.ObjectId;
import com.ponta.tutorial.framework.Texture;
import com.ponta.tutorial.window.Animation;
import com.ponta.tutorial.window.Camera;
import com.ponta.tutorial.window.Game;
import com.ponta.tutorial.window.Handler;

public class Player extends GameObject {
		
	private Camera cam;	
	Texture tex = Game.getInstance();	
	private Animation playerWalk, playerWalkLeft;
	
	public Player(float x, float y,Handler handler, Camera cam, ObjectId id){
		super(x, y, id);
		hp = 3;
		width = 48;
		height = 96;
		this.handler = handler;
		this.cam = cam;		
		playerWalk = new Animation(5, tex.player[1], tex.player[2], tex.player[3], 
									  tex.player[4], tex.player[5], tex.player[6]);
		playerWalkLeft = new Animation(5, tex.player[8], tex.player[9], tex.player[10], 
										  tex.player[11], tex.player[12], tex.player[13]);
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
		playerWalk.runAnimation();
		playerWalkLeft.runAnimation();
		PlayerStats.hp = this.hp;
	}


	public void render(Graphics g){
		g.setColor(Color.blue);		
		if(jumping){
			if(facing == 1)
				g.drawImage(tex.player_jump[2], (int) x, (int) y, 48, 96, null);
			else if(facing == -1)
				g.drawImage(tex.player_jump[3], (int) x, (int) y, 48, 96, null);
		} else {
			if(velX != 0){
				if(facing == 1)
					playerWalk.drawAnimation(g, (int) x, (int) y, 48, 96);
				else
					playerWalkLeft.drawAnimation(g, (int) x, (int) y, 48, 96);
			} else {
				if(facing ==1)
					g.drawImage(tex.player[0], (int) x, (int) y, 48 , 96 , null);
				else if(facing == -1)
					g.drawImage(tex.player[7], (int) x, (int) y, 48 , 96 , null);
			}
		}
	
	}
	
}
