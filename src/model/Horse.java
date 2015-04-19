package model;

/**
 * this class represents the horse unit
 * 
 * @author Team Barbs
 *
 */
public class Horse extends Unit {

	
	/**
	 * Horse
	 * 
	 * this is the constructor
	 * 
	 * @param name (String)
	 * @param player (Player)
	 */
	public Horse(String name, Player player) {
		super(name, player);
		this.hitpoints = 80;
		this.moveMultiplier = 5;
		this.attack = 20;
	}
	
}
