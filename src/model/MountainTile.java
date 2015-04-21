package model;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
		this.occupiable = true;
		this.isOccupied = false;
		this.moveCost = 10;
		this.occupant = null;
	}


	

}
