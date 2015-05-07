package model;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * MountainTile
 * 
 * The tile that represents mountain terrain. Has a greater move cost
 * 
 * @author Alex Nuccio, Eric Myre, Angel Cornejo
 *
 */
public class MountainTile extends Tile {
	/**
	 * MountainTile
	 * The constructor for MountainTiles
	 * Sets moveCost to 10
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public MountainTile(){
		this.occupiable = true;
		this.isOccupied = false;
		this.moveCost = 10;
		this.occupant = null;
	}


	

}
