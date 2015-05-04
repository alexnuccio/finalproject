package model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * this class represents a StrenghPotion
 * 
 * @author Team Barbs
 *
 */
public class StrengthPotion extends Item {

	public BufferedImage img;
	/**
	 * StrengthPotion
	 * 
	 * this is the constructor
	 * 
	 */
	public StrengthPotion() {
		try {
			img = ImageIO.read(new File("strength-potion.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public void draw(Graphics g, int x, int y) {
		g.drawImage(img, x, y, null);
	}

}
