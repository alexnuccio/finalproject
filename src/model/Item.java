package model;
/**
 * this is the Item class
 * 
 * @author Team Barbs
 *
 */
public abstract class Item {

	/**
	 * Item
	 * 
	 * this is the constructor
	 * 
	 */
	public Item() {
		
	}
	
	/**
	 * use
	 * 
	 * this abstract method will be implemented by all decendents to use the selected item
	 * 
	 * @return boolean
	 */
	public abstract boolean use();
}
