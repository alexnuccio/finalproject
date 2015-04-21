package model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

public class PlainTile extends Tile {
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
