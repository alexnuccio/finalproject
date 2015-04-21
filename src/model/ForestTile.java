package model;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
	}


	

}
