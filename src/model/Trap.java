package model;

/**
 * this class represents the Trap item
 * 
 * @author Team Barbs
 *
 */
public class Trap extends Item {

	/**
	 * trap
	 * 
	 * this is the constructor
	 * 
	 */
	public Trap() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * use
	 * 
	 * this lays a trap
	 * 
	 * @return boolean
	 * 
	 */
	public boolean use(Unit u) {
		// TODO Auto-generated method stub
		u.getPosition(MapOne m).trap = true;
	}

}
