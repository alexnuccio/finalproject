package model;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MountainTile extends Tile {
	/**
	 * The constructor for MountainTiles
	 * Sets moveCost to 10
	 */
	public MountainTile(){
		this.occupancy = false;
		this.moveCost = 10;
		this.setPreferredSize(new Dimension(100, 100));
	}
	/**
	 * A method to check whether or not the MountainTile is occupiable
	 * @return Whether or not the MountainTile is occupiable
	 */
	public boolean isOccupiable(){
		return false;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		File file = new File("mountain-square.jpg");
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
