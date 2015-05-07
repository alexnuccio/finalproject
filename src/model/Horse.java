package model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Horse
 * 
 * This class represents the horse unit
 * 
 * @author Alex Nuccio, Eric Myre, Angel Cornejo
 *
 */
public class Horse extends Unit {

	BufferedImage img;
	/**
	 * Horse
	 * 
	 * This is the constructor
	 * 
	 * @param name (String)
	 * @param player (Player)
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public Horse(String name, Player player) {
		super(name, player);
		this.hitpoints = this.maxhp = 80;
		this.moveMultiplier = 4;
		this.attack = 20;
		this.type = "Horse";
		try {
			img = ImageIO.read(new File("Horse.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void draw(Graphics g, int x, int y) {
		g.drawImage(img, x, y, null);
		
	}

	@Override
	public BufferedImage getImage() {
		if (this.img != null) {
			return this.img;
		} else {
			return null;
		}
	}
	
}
