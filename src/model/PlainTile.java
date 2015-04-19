package model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

public class PlainTile extends Tile {
	/**
	 * The PlainTile's constructor
	 */
	public PlainTile(){
		this.occupancy = true;
		this.moveCost = 2;
		this.setPreferredSize(new Dimension(100, 100));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		File file = new File("plain-square.jpg");
		BufferedImage img;
		try {
			img = ImageIO.read(file);
			g.drawImage(img, 0, 0, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
	}
}
