package com.ponta.tutorial.framework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.ponta.tutorial.objects.Bullet;
import com.ponta.tutorial.window.Game;
import com.ponta.tutorial.window.Game.GameState;
import com.ponta.tutorial.window.Handler;
import com.ponta.tutorial.window.screens.Inventory;

public class KeyInput extends KeyAdapter 
{
	Handler handler;	
	public KeyInput(Handler handler) { this.handler = handler; }
	
	public void keyPressed(KeyEvent e) {
		if(Game.state == GameState.INGAME){
			int key = e.getKeyCode();
			
			for(int i=0 ; i < handler.object.size(); i++) {
				GameObject tempObject = handler.object.get(i);
				
				if(tempObject.getId() == ObjectId.Player) {
					
					if(key == KeyEvent.VK_D) tempObject.setVelX(5);
					if(key == KeyEvent.VK_A) tempObject.setVelX(-5);
					if(key == KeyEvent.VK_W && !tempObject.isJumping()) {
						tempObject.setJumping(true);
						tempObject.setVelY(-15);
					}
					if(key == KeyEvent.VK_SPACE) {
						handler.addObject(new Bullet(tempObject.getX(), tempObject.getY()+48, ObjectId.Bullet, tempObject.getFacing() * 10 ));
					}
				
				}

				if(key == KeyEvent.VK_ESCAPE){
					Game.state = GameState.MENU;
				}
				
				if(key == KeyEvent.VK_E){
					Game.state = GameState.MENU;
					handler.game.setMenu(new Inventory(handler.game));
				}
				
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		if(Game.state == GameState.INGAME){
			int key = e.getKeyCode();
			
			for(int i=0 ; i < handler.object.size(); i++) {
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getId() == ObjectId.Player) {
					if(key == KeyEvent.VK_D) tempObject.setVelX(0);
					if(key == KeyEvent.VK_A) tempObject.setVelX(0);
					if(key == KeyEvent.VK_W && !tempObject.isJumping()) { 
						tempObject.setJumping(true);
						tempObject.setVelY(-10);
					}
				}
			}
		}
	}	
}
