package model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

/**
 * PlainTile
 * 
 * A tile that represents plains.
 * The basic tile.
 * 
 * @author Alex Nuccio, Eric Myre, Angel Cornejo
 */
public class PlainTile extends Tile implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * PlainTile
	 * The PlainTile's constructor
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public PlainTile(){
		this.occupiable = true;
		this.isOccupied = false;
		this.moveCost = 1;
		this.occupant = null;
	}

	

}
