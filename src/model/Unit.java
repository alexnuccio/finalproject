package model;

/**
 * This class is the abstract Unit class. All decendents will inherit these methods
 * 
 * @author Alex Nuccio, Eric Myre, Angel Cornejo, Raymond
 *
 */
public abstract class Unit {

	public String name;
	public Player player;
	public int hitpoints;
	public int moveMultiplier;
	public int attack;
	
	/**
	 * Unit
	 * 
	 * This is the constructor for the abstract unit class
	 * 
	 * @param name (String)
	 * @param player (Player)
	 */
	public Unit(String name, Player player) {
		this.name = name;
		this.player = player;
		moveMultiplier = 2;
		hitpoints = 100;
		attack = 20;
	}
	
	/**
	 * move
	 * 
	 * this method moves the unit
	 * 
	 * @param direction (int)
	 * @return boolean
	 */
	public boolean move(Direction d) {
		switch (d) {
		case UP:
			
			break;
		case DOWN: 
			
			break;
		case LEFT:
			
			break;
		case RIGHT:
			
			break;
		default:
			break;
		
		}
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
	
	public int getHitpoints() {
		return this.hitpoints;
	}
	
	public void setHitpoints(int hit) {
		this.hitpoints = hit;
	}
}
