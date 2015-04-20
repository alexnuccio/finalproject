package model;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class RiverTile extends Tile {
	/**
	 * The constructor for RiverTiles
	 */
	public RiverTile(){
		this.moveCost = 100;
		this.occupiable = false;
		this.isOccupied = false;
		this.occupant = null;
		this.setPreferredSize(new Dimension(60, 60));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		File file = new File("square.png");
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
