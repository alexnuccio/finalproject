package model;

import java.awt.Graphics;
import java.awt.Image;

/**
 * this class represents the horse unit
 * 
 * @author Team Barbs
 *
 */
public class Horse extends Unit {

	
	/**
	 * Horse
	 * 
	 * this is the constructor
	 * 
	 * @param name (String)
	 * @param player (Player)
	 */
	public Horse(String name, Player player) {
		super(name, player);
		this.hitpoints = 80;
		this.maxHP = 80;
		this.moveMultiplier = 5;
	}

	@Override
	public void draw(Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
