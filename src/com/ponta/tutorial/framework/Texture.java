package com.ponta.tutorial.framework;

import java.awt.image.BufferedImage;

import com.ponta.tutorial.window.BufferedImageLoader;

public class Texture 
{
	SpriteSheet bs, ps, cs, ls, lsI, ml, hs, mil, cl;
	
	private BufferedImage menuBg = null;

	private BufferedImage block_sheet = null;
	private BufferedImage player_sheet = null;
	private BufferedImage coins_sheet = null;
	private BufferedImage lavaI_sheet = null;
	private BufferedImage lavaII_sheet = null;
	private BufferedImage mammoth_sheet = null;
	private BufferedImage hp_sheet = null;
	private BufferedImage mistret_sheet = null;
	private BufferedImage cave_sheet = null;
	
	public BufferedImage[] block = new BufferedImage[8];
	public BufferedImage[] player = new BufferedImage[14];
	public BufferedImage[] coins = new BufferedImage[9];
	public BufferedImage[] lava = new BufferedImage[3];
	public BufferedImage[] lavaI = new BufferedImage[3];
	public BufferedImage[] lavaII = new BufferedImage[3];
	public BufferedImage[] player_jump = new BufferedImage[6];
	public BufferedImage[] mammoth = new BufferedImage[1];
	public BufferedImage[] hp = new BufferedImage[1];
	public BufferedImage[] mistret = new BufferedImage[3];
	public BufferedImage[] cave = new BufferedImage[1];
	
	public Texture()
	{
		BufferedImageLoader loader = new BufferedImageLoader();
		try{
			block_sheet = loader.loadImage("/block_sheet.png");
			player_sheet = loader.loadImage("/player_sheet.png");
			coins_sheet = loader.loadImage("/coins_sheet.png");
			lavaI_sheet = loader.loadImage("/lavaI.png");
			lavaII_sheet = loader.loadImage("/lavaII.png");
			mammoth_sheet = loader.loadImage("/mammoth.png");
			hp_sheet = loader.loadImage("/life.png");
			mistret_sheet = loader.loadImage("/mistret.png");
			cave_sheet = loader.loadImage("/cave.png");
			
			menuBg = loader.loadImage("/menuBg.jpg");
		}catch(Exception e){
			e.printStackTrace();
		}		
		bs = new SpriteSheet(block_sheet);
		ps = new SpriteSheet(player_sheet);
		cs = new SpriteSheet(coins_sheet);	
		ls = new SpriteSheet(lavaI_sheet);
		lsI = new SpriteSheet(lavaII_sheet);
		ml = new SpriteSheet(mammoth_sheet);
		hs = new SpriteSheet(hp_sheet);
		mil = new SpriteSheet(mistret_sheet);
		cl = new SpriteSheet(cave_sheet);
		
		getTextures();
	}
	
	private void getTextures()
	{
		block[0] = bs.grabImage(1, 1, 32, 32); // dirt block
		block[1] = bs.grabImage(2, 1, 32, 32); // grass block
		block[2] = bs.grabImage(1, 4, 32, 32); // quartz blue
		block[3] = bs.grabImage(2, 4, 32, 32); // quartz green
		block[4] = bs.grabImage(3, 4, 32, 32); // quartz grey
		block[5] = bs.grabImage(4, 4, 32, 32); // quartz purple
		block[6] = bs.grabImage(5, 4, 32, 32); // quartz red
		block[7] = bs.grabImage(6, 4, 32, 32); // quartz brown
		//cave
		cave[0] = cl.grabImage(1, 1, 74, 63);
		//lava river animation
		lava[0] = ls.grabImage(1, 1, 32, 32);
		lava[1] = ls.grabImage(2, 1, 32, 32);
		lava[2] = ls.grabImage(3, 1, 32, 32);
		// lava below
		lavaI[0] = lsI.grabImage(1, 1, 32, 32);
		lavaI[1] = lsI.grabImage(2, 1, 32, 32);
		lavaI[2] = lsI.grabImage(3, 1, 32, 32);
		//coins
		coins[0] = cs.grabImage(1, 1, 32, 32);
		coins[1] = cs.grabImage(2, 1, 32, 32);
		coins[2] = cs.grabImage(3, 1, 32, 32);
		coins[3] = cs.grabImage(4, 1, 32, 32);
		coins[4] = cs.grabImage(5, 1, 32, 32);
		coins[5] = cs.grabImage(6, 1, 32, 32);
		coins[6] = cs.grabImage(1, 2, 32, 32);
		coins[7] = cs.grabImage(2, 2, 32, 32);
		coins[8] = cs.grabImage(3, 2, 32, 32);		
		//going right
		player[0] = ps.grabImage(1, 1, 32, 64); // idle frame for player
		player[1] = ps.grabImage(2, 1, 32, 64); // walking animation
		player[2] = ps.grabImage(3, 1, 32, 64); 
		player[3] = ps.grabImage(4, 1, 32, 64); 
		player[4] = ps.grabImage(5, 1, 32, 64); 
		player[5] = ps.grabImage(6, 1, 32, 64); 
		player[6] = ps.grabImage(7, 1, 32, 64); 		
		// going left
		player[7] = ps.grabImage(20, 1, 32, 64); // idle frame for player
		player[8] = ps.grabImage(19, 1, 32, 64); 
		player[9] = ps.grabImage(18, 1, 32, 64); 
		player[10] = ps.grabImage(17, 1, 32, 64); 
		player[11] = ps.grabImage(16, 1, 32, 64); 
		player[12] = ps.grabImage(15, 1, 32, 64); 
		player[13] = ps.grabImage(14, 1, 32, 64); 
		//mammoth
		mammoth[0] = ml.grabImage(1, 1, 82, 47);
		//mistret
		mistret[0] = mil.grabImage(1, 1, 81, 63);
		mistret[1] = mil.grabImage(2, 1, 81, 63);
		mistret[2] = mil.grabImage(3, 1, 81, 63);
		// jumping animation
		player_jump[0] = ps.grabImage(8, 2, 32, 65); 
		player_jump[1] = ps.grabImage(9, 2, 32, 65); 
		player_jump[2] = ps.grabImage(10, 2, 32, 65); 
		player_jump[3] = ps.grabImage(11, 2, 32, 65); 
		player_jump[4] = ps.grabImage(12, 2, 32, 65); 
		player_jump[5] = ps.grabImage(13, 2, 32, 65); 
		// hp
		hp[0] = hs.grabImage(1, 1, 32, 32);
	}
	
	public BufferedImage getMenuBackground(){ return menuBg; }
}
