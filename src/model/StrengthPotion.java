package model;

/**
 * this class represents a StrenghPotion
 * 
 * @author Team Barbs
 *
 */
public class StrengthPotion extends Item {

	/**
	 * StrengthPotion
	 * 
	 * this is the constructor
	 * 
	 */
	public StrengthPotion() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * use()
	 * 
	 * applies a strength potion to the Player that used it
	 * 
	 * @return boolean
	 * 
	 */
	public boolean use(Unit u) {
		u.attack += 10;
		return true;
	}

}
