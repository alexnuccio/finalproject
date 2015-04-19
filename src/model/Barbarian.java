package model;

/**
 * this class represents the Barbarian unit
 * 
 * @author Team Barbs
 *
 */
public class Barbarian extends Unit {

	/**
	 * Barbarian
	 * 
	 * this is the constructor
	 * 
	 * @param name (String)
	 * @param player (Player)
	 */
	public Barbarian(String name, Player player) {
		super(name, player);
		this.hitpoints = 100;
		this.moveMultiplier = 2;
		this.attack = 20;
	}
}
