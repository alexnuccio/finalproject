package model;

/**
 * TrapTile
 * 
 * A class representing a tile that has had a trap set on it. Very dangerous!
 * 
 * @author Alex Nuccio, Eric Myre, Angel Cornejo
 *
 */
public class TrapTile extends Tile {
	/**
	 * TrapTile
	 * 
	 * The constructor for a trap tile
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public TrapTile(){
		this.occupiable = true;
		this.isOccupied = false;
		this.moveCost = 1;
		this.occupant = null;
		}
}
