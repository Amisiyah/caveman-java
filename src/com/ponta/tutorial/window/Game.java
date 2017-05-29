package com.ponta.tutorial.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.ponta.tutorial.framework.KeyInput;
import com.ponta.tutorial.framework.ObjectId;
import com.ponta.tutorial.framework.Texture;
import com.ponta.tutorial.objects.PlayerStats;

public class Game extends Canvas implements Runnable{	
	private final Font font = new Font("TimesRoman", Font.BOLD, 20);	
	private static final long serialVersionUID = -8511450172027928061L;

	private boolean running = false;
	private Thread thread;	
	public static int WIDTH,HEIGHT;	
	public BufferedImage level = null , clouds = null;
	
	Handler handler;
	Camera cam;
	static Texture tex;
	Random rand = new Random();
	public static int LEVEL = 1;
	
	private void init()
	{
		WIDTH = getWidth();
		HEIGHT = getHeight();
		tex = new Texture();
		
		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.loadImage("/level.png"); //loading the level
		clouds = loader.loadImage("/clouds.png"); // loading background
		cam = new Camera(0,0);
		handler = new Handler(cam);
		handler.LoadImageLevel(level);
		this.addKeyListener(new KeyInput(handler));
	}
	
	public synchronized void start(){
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void run()
	{
		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;

				render();
				frames++;
				delta--;
			}
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames + " Ticks: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}
	
	private void tick()
	{
		handler.tick();
		for(int i=0 ; i < handler.object.size() ; i++)
		{
			if(handler.object.get(i).getId() == ObjectId.Player){
				cam.tick(handler.object.get(i));
			}
		}
	}
	
	private void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null)	{
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;		
		//Draw here
		
		g.setColor(new Color(112,199,240));
		g.fillRect(0, 0, getWidth(), getHeight());		
		g2d.translate(cam.getX(), cam.getY()); //begin of cam
			
			for(int xx = 0 ; xx < clouds.getWidth() * 3 ; xx += clouds.getWidth())
				g.drawImage(clouds, xx, 50, this);
			handler.render(g);
		
		g2d.translate(-cam.getX(), -cam.getY()); // end of cam
		
	    g.setFont(font);	     
	    g.setColor(Color.BLACK);	    
	    g.drawString("Coins: " + PlayerStats.coins, 16, 16);
	    
	    for(int i = 0; i < PlayerStats.hp; i++){
			g.drawImage(tex.hp[0], 16 + i * 16, 24, 32, 32, null);
	    }
		

		g.dispose();
		bs.show();
	}
		
	public static Texture getInstance() { return tex; }	
	public static void main(String args[]) { new Window(800, 600, "Man of the past", new Game()); }
}
