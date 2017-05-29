package com.ponta.tutorial.window.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.ponta.tutorial.framework.Texture;
import com.ponta.tutorial.objects.PlayerStats;
import com.ponta.tutorial.window.Game;
import com.ponta.tutorial.window.Game.GameState;

public class Menu implements KeyListener{
	
	String options[] = {"Start", "About", "Exit"};
	protected int currentOption = 0;
	Game game;
	Color menuBar = new Color(0, 0, 0, 150);
	
	public Menu(Game game){
		this.game = game;
		game.addKeyListener(this);
	}
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			currentOption++;
			if(currentOption > options.length - 1) currentOption = 0;
		} else if(e.getKeyCode() == KeyEvent.VK_UP) {
			currentOption--;
			if(currentOption < 0) currentOption = options.length - 1;
		} else if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			select(currentOption);
		}
		
	}

	private void select(int option) {
		switch(option){
			case 0:
				Game.state = GameState.INGAME;
				break;
				
			case 1:
				// TODO add about
				break;
				
			case 2:
				System.exit(0);
				break;
		}
	}

	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	
	public void render(Graphics g){
		g.drawImage(Game.getInstance().getMenuBackground(), 0, 0, Game.WIDTH, Game.HEIGHT, null);
	    g.setColor(menuBar); 
		g.fillRect(Game.WIDTH - 100, 0, 100, Game.HEIGHT);
		for(int i = 0; i < options.length; i++){
			g.setFont(Game.font);	     
		    
		    if(i == currentOption) g.setColor(Color.RED);
		    else g.setColor(Color.WHITE); 

		    g.drawString(options[i], Game.WIDTH - options[i].length() * 20 / 10 - 60, Game.HEIGHT / 2 + i * 25);
		}
	}
	
}
