package model;

/**
 * This class represents the Spearman unit
 * 
 * @author Team Barbs
 *
 */
public class Spearman extends Unit {

	
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
		this.moveMultiplier = 1;
		this.hitpoints = 150;
		this.attack = 20;
	}
	


}
