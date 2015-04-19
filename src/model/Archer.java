package model;

/**
 * this class represents the Archer unit
 * 
 * @author Team Barbs
 *
 */
public class Archer extends Unit {
	
	/**
	 * Archer
	 * 
	 * this is the constructor
	 * 
	 * @param name (String)
	 * @param player (Player)
	 */
	public Archer(String name, Player player) {
		super(name, player);
		hitpoints = 100;
		moveMultiplier = 2;
	}

	/**
	 * attack
	 * 
	 * this method allows the unit to attack other units
	 * 
	 * @param direction (int)
	 * @return boolean
	 */
	public boolean attack(int direction) {
		return false;
	}
}
