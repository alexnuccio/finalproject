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
		this.occupiable = true;
		this.isOccupied = false;
		this.moveCost = 1;
		this.occupant = null;
		this.setPreferredSize(new Dimension(60, 60));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		File file = new File("plain-test.png");
		BufferedImage img;
		try {
			img = ImageIO.read(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		g.drawImage(img, 0, 0, null);
		if(this.isOccupied == true) {
			//PAINT UNIT OVER TILE
			
		}
	}
}
