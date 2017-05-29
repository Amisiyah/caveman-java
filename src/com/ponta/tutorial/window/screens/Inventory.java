package com.ponta.tutorial.window.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.ponta.tutorial.window.Game;
import com.ponta.tutorial.window.Game.GameState;

public class Inventory extends Menu{

	public Inventory(Game game) {
		super(game);
	}

	private int slots = 16;
	
	public void keyPressed(KeyEvent e) {
		if(Game.state == GameState.MENU){
			if(e.getKeyCode() == KeyEvent.VK_E) {
				game.setMenu(null);
				Game.state = GameState.INGAME;
			}
		}
	}
	
	public void render(Graphics g){
		int xx = Game.WIDTH - 600;
		int yy = Game.HEIGHT - 500;
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(xx, yy, 400, 400);
		
		for(int i = 0; i < slots; i++){
			
			g.setColor(menuBar);
			g.fillRect(xx + 25 + (i % 4) * 90, yy + 25 + (i / 4) * 90, 80, 80);
			
		}
	}
	
}
