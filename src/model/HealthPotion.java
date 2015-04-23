package model;

/**
 * this class reprents a HealthPotion
 * 
 * @author alexnuccio
 *
 */
public class HealthPotion extends Item {

	/**
	 * HealthPotion
	 * 
	 * this is the constructor
	 * 
	 */
	public HealthPotion() {
		
	}

	/**
	 * use
	 * 
	 * applies a health potion to the Player that used it
	 * 
	 * @return boolean
	 * 
	 */
	public boolean use(Unit u) {
		if(u.getHitpoints() < u.maxHP - 20){
			u.setHitpoints(u.getHitpoints() + 20);
			return true;
		}else if(u.getHitpoints() > 0){
			u.setHitpoints(u.maxHP);
			return true;
		}
		return false;
	}

}
