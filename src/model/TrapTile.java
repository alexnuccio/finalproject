package model;

public class TrapTile extends Tile {
	/**
	 * The constructor for RiverTiles
	 */
	public TrapTile(){
		this.occupiable = true;
		this.isOccupied = false;
		this.moveCost = 1;
		this.occupant = null;
		}
}
