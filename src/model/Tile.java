package model;

import javax.swing.JPanel;
import java.awt.*;

public abstract class Tile {
	/**
	 * The move cost of going through this tile
	 */
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
	 * The constructor for Tile
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
	 * Detects if the tile is a trapped tile
	 * @return Whether or not the tile is a trapped tile
	 */
	public boolean hasATrap(){
		return this.trap;
	}
	/**
	 * A method to check whether or not the Tile is occupiable
	 * @return Whether or not the Tile is occupiable
	 */
	public boolean isOccupiable(){
		return occupiable;
	}
	
	public boolean isOccupied() {
		return this.isOccupied;
	}
	
	public void setOccupant(Unit u) {
		this.occupant = u;
		this.isOccupied = true; //sets occupied to true
	}
	
	public Unit getOccupant() {
		if(isOccupied() == true) {
			return this.occupant;
		} else {
			return null;
		}
	}
	
	public void setItem(Item i) {
		this.item = i;
		this.hasItem = true;
	}
	
	public Item getItem() {
		if(this.hasItem == true) {
			return this.item;
		} else {
			return null;
		}
	}

}
