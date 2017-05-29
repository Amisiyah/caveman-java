package com.ponta.tutorial.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.ponta.tutorial.framework.GameObject;
import com.ponta.tutorial.framework.ObjectId;
import com.ponta.tutorial.framework.Texture;
import com.ponta.tutorial.window.Game;

public class Block extends GameObject 
{
	Texture tex = Game.getInstance();
	private int type;

	public Block(float x, float y, int type , ObjectId id) {
		super(x, y, id);
		this.type = type;		
	}

	public void tick(LinkedList<GameObject> object) { }
	public void render(Graphics g) {
		if(type == 0) // dirt block
			g.drawImage(tex.block[0], (int)x, (int)y, null);
		if(type == 1) // grass block
			g.drawImage(tex.block[1], (int)x, (int)y, null);
		if(type == 2) // blue
			g.drawImage(tex.block[2], (int)x, (int)y, null);
		if(type == 3) // green
			g.drawImage(tex.block[3], (int)x, (int)y, null);
		if(type == 4) // grey
			g.drawImage(tex.block[4], (int)x, (int)y, null);
		if(type == 5) // purple
			g.drawImage(tex.block[5], (int)x, (int)y, null);
		if(type == 6) // red
			g.drawImage(tex.block[6], (int)x, (int)y, null);
		if(type == 7) // brown
			g.drawImage(tex.block[7], (int)x, (int)y, null);		
	}

	public Rectangle getBounds() { return new Rectangle((int) x, (int) y, 32, 32); }	
}
