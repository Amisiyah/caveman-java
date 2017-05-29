package com.ponta.tutorial.objects.inventory.items;

import java.awt.image.BufferedImage;

public class Item {
	
	String name;
	BufferedImage icon;
	int damage = 0;

	public Item(String name, BufferedImage icon, int damage){
		this.name = name;
		this.icon = icon;
		this.damage = damage;
	}
	
	
}
