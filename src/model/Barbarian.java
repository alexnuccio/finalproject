package model;

/**
 * this class represents the Barbarian unit
 * 
 * @author Team Barbs
 *
 */
public class Barbarian extends Unit {

	public String name;
	public Player player;
	public int hitpoints;
	public int moveMultiplier;
	
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
		hitpoints = 100;
		moveMultiplier = 2;
	}
	
	/**
	 * move
	 * 
	 * this method moves the unit
	 * 
	 * @param direction (int)
	 * @return boolean
	 */
	public boolean move(int direction) {
		return false;
	}
	
	/**
	 * useItem
	 * 
	 * this method allows the unit to use an item
	 * 
	 * @param item (Item)
	 * @return boolean 
	 */
	public boolean useItem(Item item) {
		return false;
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
	
	/**
	 * getName
	 * 
	 * @return String representing name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * getPlayer
	 * 
	 * @return Player object representing player
	 */
	public Player getPlayer() {
		return this.player;
	}

}
