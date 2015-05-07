package model;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * RiverTile
 * 
 * A tile representing a peaceful river.
 * 
 * @author Alex Nuccio, Eric Myre, Angel Cornejo
 */
public class RiverTile extends Tile {
	/**
	 * RiverTile
	 * 
	 * The constructor for RiverTiles
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public RiverTile(){
		this.moveCost = 100;
		this.occupiable = false;
		this.isOccupied = false;
		this.occupant = null;
	}


}
