package model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpeedShoes extends Item {

	public BufferedImage img;
	
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
