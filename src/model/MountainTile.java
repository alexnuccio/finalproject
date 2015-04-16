package model;

public class MountainTile extends Tile {
	/**
	 * The constructor for MountainTiles
	 * Sets moveCost to 10
	 */
	public MountainTile(){
		this.moveCost = 10;
	}
	/**
	 * A method to check whether or not the MountainTile is occupiable
	 * @return Whether or not the MountainTile is occupiable
	 */
	public boolean isOccupiable(){
		return false;
	}
}
