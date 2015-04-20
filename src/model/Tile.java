package model;

import javax.swing.JPanel;

public abstract class Tile extends JPanel{
	/**
	 * The move cost of going through this tile
	 */
	public int moveCost;
	public boolean occupiable;
	public boolean isOccupied;
	public Unit occupant; //if occupied, this is the Unit occupying Tile
	public boolean trap;
	
	/**
	 * The constructor for Tile
	 */
	public Tile(){
		this.moveCost = 1;
		this.trap = false;
		this.occupiable = true;
		this.isOccupied = false;
		this.occupant = null;
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
		if(this.occupant != null) {
			return this.occupant;
		} else {
			return null;
		}
	}

}
