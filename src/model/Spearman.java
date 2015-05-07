package model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Spearman
 * 
 * This class represents the Spearman unit
 * 
 *@author Alex Nuccio, Eric Myre, Angel Cornejo
 *
 */
public class Spearman extends Unit {

	BufferedImage img;
	/**
	 * Spearman
	 * 
	 * This is the constructor of the Spearman unit
	 * 
	 * @param name	the name of the Unit
	 * 
	 * @param player	the player the Unit belongs to 
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public Spearman(String name, Player player) {
		super(name, player);
		try {
			img = ImageIO.read(new File("Pikeman.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.type = "Spearman";
		this.moveMultiplier = 1;
		this.hitpoints = this.maxhp = 150;
		this.attack = 30;
	}

	@Override
	public void draw(Graphics g, int x, int y) {
		g.drawImage(img, x, y, null);
		
	}

	@Override
	public BufferedImage getImage() {
		return this.img;
	}
	


}
