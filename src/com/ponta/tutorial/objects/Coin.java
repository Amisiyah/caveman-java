package com.ponta.tutorial.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.ponta.tutorial.framework.GameObject;
import com.ponta.tutorial.framework.ObjectId;
import com.ponta.tutorial.framework.Texture;
import com.ponta.tutorial.window.Animation;
import com.ponta.tutorial.window.Game;
import com.ponta.tutorial.window.Handler;

public class Coin extends GameObject{
	Texture tex;
	private Animation coinAnimation;
	
	public Coin(int x, int y, Handler handler, ObjectId type){
		super(x, y, type);
		this.handler = handler;
		tex = Game.getInstance();
		coinAnimation = new Animation(5, tex.coins[0], tex.coins[1], tex.coins[2], tex.coins[3], tex.coins[4],
										 tex.coins[5], tex.coins[6], tex.coins[7], tex.coins[8]);
	}

	public void tick(LinkedList<GameObject> objects) {
		if(touch_player(objects)) {
			PlayerStats.coins++;
			remove();
		}
		coinAnimation.runAnimation();
	}

	public boolean touch_player(LinkedList<GameObject> objects){
		for(GameObject object : objects){
			if(object instanceof Player){
				if(getBounds().intersects(object.getBounds()) || getBounds().intersects(((Player) object).getBoundsTop()) ||
     			   getBounds().intersects(((Player) object).getBoundsRight()) || getBounds().intersects(((Player) object).getBoundsLeft()))
					return true;
			}
		}
		return false;
	}
	
	public void render(Graphics g) { coinAnimation.drawAnimation(g, (int)x, (int)y); }
	public Rectangle getBounds() { return new Rectangle((int) x, (int) y, 32, 32); }
}
