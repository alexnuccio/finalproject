package model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * SpeedShoes
 * 
 * The speed shoes item. Increases the number of tiles a unit can move
 * 
 * @author Alex Nuccio, Eric Myre, Angel Cornejo
 */
public class SpeedShoes extends Item {

	public BufferedImage img;

	/**
	 * SpeedShoes
	 * 
	 * Constructor.
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public SpeedShoes() {
		try {
			img = ImageIO.read(new File("speed-shoes.png"));
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
