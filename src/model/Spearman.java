package model;

import java.awt.Graphics;
import java.awt.Image;

/**
 * This class represents the Spearman unit
 * 
 * @author Team Barbs
 *
 */
public class Spearman extends Unit {

	
	/**
	 * Spearman
	 * 
	 * this is the constructor of the Spearman unit
	 * 
	 * @param name (String)
	 * @param player (Player)
	 */
	public Spearman(String name, Player player) {
		super(name, player);
		this.moveMultiplier = 1;
		this.hitpoints = 150;
		this.attack = 20;
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
