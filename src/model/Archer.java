package model;

/**
 * this class represents the Archer unit
 * 
 * @author Team Barbs
 *
 */
public class Archer extends Unit {
<<<<<<< HEAD


=======
>>>>>>> origin/master
	
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
<<<<<<< HEAD
		this.hitpoints = 100;
		this.moveMultiplier = 2;
		this.attack = 30;
	}


=======
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
>>>>>>> origin/master
}
