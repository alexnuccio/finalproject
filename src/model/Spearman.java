package model;

/**
 * This class represents the Spearman unit
 * 
 * @author Team Barbs
 *
 */
public class Spearman extends Unit {

	public String name;
	public Player player;
	public int moveMultiplier;
	public int hitpoints;
	
	/**
	 * Spearman
	 * 
	 * this is the constructor of the Spearman unit
	 * 
	 * @param name (String)
	 * @param player (Player)
	 */
	public Spearman(String name, Player player) {
		super(name, player);
		moveMultiplier = 1;
		hitpoints = 150;
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
