package model;

import javax.swing.JPanel;
import java.awt.*;

/**
 * Tile
 * 
 * Abstract class to represent the tiles to that make up the map's grid.
 * 
 * @author Alex Nuccio, Eric Myre, Angel Cornejo
 *
 */
public abstract class Tile {

	public int moveCost;
	public boolean occupiable;
	public boolean isOccupied;
	public Unit occupant; //if occupied, this is the Unit occupying Tile
	public Item item; //if this tile has an item, this is the Item on tile
	public boolean trap;
	public boolean hasCursor;
	public boolean hasItem;
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Tile
	 * 
	 * The constructor for Tile
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public Tile(){
		this.hasCursor = false;
		this.moveCost = 1;
		this.trap = false;
		this.occupiable = true;
		this.isOccupied = false;
		this.occupant = null;
		this.hasItem = false;
		this.item = null;
	}

	
	/**
	 * hasATrap
	 * 
	 * Detects if the tile is a trapped tile
	 * 
	 * @return boolean
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public boolean hasATrap(){
		return this.trap;
	}
	
	/**
	 * isOccupiable
	 * 
	 * A method to check whether or not the Tile is occupiable
	 * 
	 * @return boolean
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public boolean isOccupiable(){
		return occupiable;
	}
	
	/**
	 * isOccupiable
	 * 
	 * Returns whether a tile is occupied
	 * 
	 * @return boolean
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public boolean isOccupied() {
		return this.isOccupied;
	}
	
	/**
	 * setOccupant
	 * 
	 * Sets the occupant of a tile
	 * 
	 * @param u the unit to be set on the tile
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public void setOccupant(Unit u) {
		this.occupant = u;
		this.isOccupied = true; //sets occupied to true
	}
	
	/**
	 * getOccupant
	 * 
	 * Returns the unit occupying a tile
	 * 
	 * @return Unit
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public Unit getOccupant() {
		if(isOccupied() == true) {
			return this.occupant;
		} else {
			return null;
		}
	}
	
	/**
	 * setItem
	 * 
	 * Sets an item on a tile
	 * 
	 * @param i the item being set
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public void setItem(Item i) {
		this.item = i;
		this.hasItem = true;
	}
	
	/**
	 * getItem
	 * 
	 * Retrieves an item
	 * 
	 * @return boolean
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public Item getItem() {
		if(this.hasItem == true) {
			return this.item;
		} else {
			return null;
		}
	}

}
