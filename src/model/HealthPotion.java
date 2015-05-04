package model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * this class reprents a HealthPotion
 * 
 * @author alexnuccio
 *
 */
public class HealthPotion extends Item {

	public BufferedImage img;
	/**
	 * HealthPotion
	 * 
	 * this is the constructor
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
