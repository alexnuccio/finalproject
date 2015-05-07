package model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * HealthPotion
 * 
 * This class represents a HealthPotion.
 * 
 * @author Alex Nuccio, Eric Myre, Angel Cornejo
 *
 */
public class HealthPotion extends Item {

	public BufferedImage img;
	/**
	 * HealthPotion
	 * 
	 * Constructs a health potion
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 * 
	 */
	public HealthPotion() {
		try {
			img = ImageIO.read(new File("health-potion.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * setPosition
	 * 
	 * Sets the position of the health position before it gets picked up.
	 * 
	 * @param x	the x position on the map's grid where the potion will appear
	 * 
	 * @param y the y position on the map's grid where the potion will appear
	 * 
	 * @param m the map
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public void setPosition(int x, int y, MapOne m) {
		if((x >= 0 && x < m.getNumRow()) && (y >= 0 && y < m.getNumCol())) {
			m.array[x][y].hasItem = true;
			m.array[x][y].item = this;
		} else {
			//invalid move
			return;
		}
	}


	@Override
	public void draw(Graphics g, int x, int y) {
		g.drawImage(img, x, y, null);
	}


}
