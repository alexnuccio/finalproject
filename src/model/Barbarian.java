package model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * this class represents the Barbarian unit
 * 
 * @author Alex Nuccio, Eric Myre, Angel Cornejo
 *
 */
public class Barbarian extends Unit {
	
	private BufferedImage img;

	/**
	 * Barbarian
	 * 
	 * This is the constructor.
	 * 
	 * @param name (String)
	 * @param player (Player)
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public Barbarian(String name, Player player) {
		super(name, player);
		try {
			img = ImageIO.read(new File("Barbarian.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.type = "Barbarian";
		this.hitpoints = this.maxhp = 100;
		this.moveMultiplier = 2;
		this.attack = 20;
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
