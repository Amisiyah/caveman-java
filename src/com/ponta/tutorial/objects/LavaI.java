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

public class LavaI extends GameObject {
	Texture tex;
	private Animation lavaIAnimation;
	
	public LavaI(int x, int y, Handler handler, ObjectId type) {
		super(x, y, type);
		this.handler = handler;
		tex = Game.getInstance();
		lavaIAnimation = new Animation(5, tex.lava[0], tex.lava[1], tex.lava[2]);
	}

	public void tick(LinkedList<GameObject> object) { lavaIAnimation.runAnimation(); }

	public void render(Graphics g) { lavaIAnimation.drawAnimation(g, (int)x, (int)y); }
	public Rectangle getBounds() { return new Rectangle((int) x, (int) y, 32, 32); }
}
