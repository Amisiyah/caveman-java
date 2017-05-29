package com.ponta.tutorial.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.ponta.tutorial.framework.GameObject;
import com.ponta.tutorial.framework.ObjectId;
import com.ponta.tutorial.framework.Texture;
import com.ponta.tutorial.window.Game;

public class Flag extends GameObject{
	
	Texture tex = Game.getInstance();
	//private int type;
	
	public Flag(float x, float y, ObjectId id) { 
		super(x, y, id);
		//this.type = type;
	}
	public void tick(LinkedList<GameObject> object) { }

	public void render(Graphics g) {
		g.drawImage(tex.cave[0], (int)x, (int)y, null);
	}

	public Rectangle getBounds() { return new Rectangle((int) x, (int) y, 32, 32); }
}
