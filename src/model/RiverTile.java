package model;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
	}


}
