package model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * StrengthPotion
 * 
 * This class represents a StrenghPotion; increases the attack power of a unit.
 * 
 * @author Alex Nuccio, Eric Myre, Angel Cornejo
 *
 */
public class StrengthPotion extends Item {

	public BufferedImage img;
	/**
	 * StrengthPotion
	 * 
	 * This is the constructor
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
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
