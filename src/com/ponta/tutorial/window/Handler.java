package com.ponta.tutorial.window;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import com.ponta.tutorial.framework.GameObject;
import com.ponta.tutorial.framework.ObjectId;
import com.ponta.tutorial.objects.Block;
import com.ponta.tutorial.objects.Coin;
import com.ponta.tutorial.objects.Flag;
import com.ponta.tutorial.objects.LavaI;
import com.ponta.tutorial.objects.LavaII;
import com.ponta.tutorial.objects.Player;
import com.ponta.tutorial.objects.animals.Mammoth;
import com.ponta.tutorial.objects.animals.Mistret;

public class Handler 
{
	public LinkedList<GameObject> object = new LinkedList<GameObject>();	
	private GameObject tempObject;	
	private Camera cam;	
	private BufferedImage level2 = null , level3 = null;	

	public Game game;
	
	public Handler(Camera cam, Game game){
		this.game = game;
		this.cam = cam;		
		BufferedImageLoader loader = new BufferedImageLoader();
		level2 = loader.loadImage("/level2.png"); //loading the level 2
		level3 = loader.loadImage("/level3.png"); //loading the level 3)
	}
	

	public void tick()
	{
		for(int i = 0;i < object.size();i++)	{
			tempObject = object.get(i);
			if(tempObject.removed()) object.remove(i--);
			else tempObject.tick(object);
		}
	}
	
	public void render(Graphics g)
	{
		for(int i = 0;i <object.size();i++){
			tempObject = object.get(i);
			tempObject.render(g);
		}		
	}
	
	public void LoadImageLevel(BufferedImage image)
	{
		int w = image.getWidth();
		int h = image.getHeight();		
		System.out.println("width, height: " + w + " " + h);
		
		for(int xx = 0 ; xx < h ; xx++ )
		{
			for(int yy = 0 ; yy < w ; yy++ )
			{
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;				
				//loading blocks
				if(red == 255 && green == 255 && blue == 255) addObject(new Block(xx*32, yy*32, 0 , ObjectId.Block));
				if(red == 128 && green == 128 && blue == 128) addObject(new Block(xx*32, yy*32, 1 , ObjectId.Block));
				if(red == 0 && green == 148 && blue == 255) addObject(new Block(xx*32, yy*32, 2 , ObjectId.Block));
				if(red == 0 && green == 255 && blue == 33) addObject(new Block(xx*32, yy*32, 3 , ObjectId.Block));
				if(red == 48 && green == 48 && blue == 48) addObject(new Block(xx*32, yy*32, 4 , ObjectId.Block));
				if(red == 178 && green == 0 && blue == 255) addObject(new Block(xx*32, yy*32, 5 , ObjectId.Block));
				if(red == 127 && green == 0 && blue == 0) addObject(new Block(xx*32, yy*32, 6 , ObjectId.Block));
				if(red == 127 && green == 51 && blue == 0) addObject(new Block(xx*32, yy*32, 7 , ObjectId.Block));
				
				if(red ==255 && green == 233 && blue == 127) addObject(new Coin(xx*32, yy*32, this , ObjectId.Coin));
				
				if(red ==128 && green == 0 && blue == 0) addObject(new LavaI(xx*32, yy*32, this , ObjectId.LavaI));
				
				if(red ==127 && green == 0 && blue == 0) addObject(new LavaII(xx*32, yy*32, this , ObjectId.LavaII));
				
				if(red == 255 && green == 0 && blue == 0) addObject(new Player(xx*32, yy*32, this, cam, ObjectId.Player));
				
				if(red == 0 && green == 50 && blue == 30) addObject(new Mammoth(xx*32, yy*32, this, cam, ObjectId.Animals));
				if(red == 0 && green == 25 && blue == 254) addObject(new Mistret(xx*32, yy*32, this, cam, ObjectId.Animals));
				
				if(red == 255 && green == 216 && blue == 0) addObject(new Flag(xx*32, yy*32, ObjectId.Flag));
			}
		}		
	}	
	// get to next level
	public void switchLevel()
	{
		clearLevel();
		cam.setX(0);		
		switch(Game.LEVEL)
		{
		case 1:
			LoadImageLevel(level2);
			break;
		case 2:
			LoadImageLevel(level3);
			break;
		}
		Game.LEVEL++;
	}
	
	private void clearLevel() { object.clear(); }
	
	public void addObject(GameObject object) { this.object.add(object); }
	
	public void removeObject(GameObject object) { this.object.remove(object); }	
}
