package model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * this class represents the Archer unit
 * 
 * @author Team Barbs
 *
 */
public class Archer extends Unit {	
	
	private BufferedImage img;
	
	/**
	 * Archer
	 * 
	 * this is the constructor
	 * 
	 * @param name (String)
	 * @param player (Player)
	 */
	public Archer(String name, Player player) {
		super(name, player);
<<<<<<< HEAD
		// load in image
		
		this.hitpoints = 100;
		this.moveMultiplier = 2;
		this.attack = 30;
=======
		this.hitpoints = 80;
		this.maxHP = 80;
>>>>>>> origin/master
	}

	/**
	 * attack
	 * 
	 * this method allows the unit to attack other units
	 * 
	 * @param direction (int)
	 * @return boolean
	 */
	public boolean attack(int direction) {
		return false;
	}

	@Override
	public void draw(Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Image getImage() {
		if (this.img != null) {
			return this.img;
		} else {
			return null;
		}
	}
}
