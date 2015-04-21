package model;

import java.awt.Graphics;
import java.awt.Image;

/**
 * This class represents the Mage unit
 * 
 * @author Alex Nuccio, Eric Myre, Angel Cornejo, Raymond
 *
 */
public class Mage extends Unit {

	/**
	 * Mage
	 * 
	 * this is the constructor of the Mage unit
	 * 
	 * @param name (String)
	 * @param player (Player)
	 */
	public Mage(String name, Player player) {
		super(name, player);
		this.moveMultiplier = 2;
		this.hitpoints = 100;
		this.attack = 10;
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
