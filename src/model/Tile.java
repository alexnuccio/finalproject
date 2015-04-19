package model;

import javax.swing.JPanel;

public abstract class Tile extends JPanel{
	/**
	 * The move cost of going through this tile
	 */
	public int moveCost = 1;
	public boolean occupancy = true;
	/**
	 * Detects if the tile is a trapped tile
	 * @return Whether or not the tile is a trapped tile
	 */
	public boolean hasATrap(){
		return false;
	}
	/**
	 * A method to check whether or not the Tile is occupiable
	 * @return Whether or not the Tile is occupiable
	 */
	public boolean isOccupiable(){
		return occupancy;
	}
	/**
	 * The constructor for Tile
	 */
	public Tile(){
		
	}
}
