package model;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ForestTile extends Tile {
	/**
	 * The constructor for ForestTiles.
	 * It sets move cost to 2;
	 */
	public ForestTile(){
		this.occupiable = true;
		this.isOccupied = false;
		this.moveCost = 2;
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
