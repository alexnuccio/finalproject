package model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

public class PlainTile extends Tile implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * The PlainTile's constructor
	 */
	public PlainTile(){
		this.occupiable = true;
		this.isOccupied = false;
		this.moveCost = 1;
		this.occupant = null;
	}

	

}
