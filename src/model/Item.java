package model;

import java.awt.Graphics;

/**
 * Item
 * 
 * This is the Item class. Items are able to be picked up and the human player can use any in their possession during their turn
 * 
 * @author Alex Nuccio, Eric Myre, Angel Cornejo
 *
 */
public abstract class Item {

	private static final long serialVersionUID = 1L;
	/**
	 * Item
	 * 
	 * This is the constructor.
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public Item() {
		
	}

	/**
	 * setPosition
	 * 
	 * Sets the position of the item on the map before it is picked up.
	 * 
	 * @param x	the x position on the map's grid
	 * 
	 * @param y the y position on the map's grid
	 * 
	 * @param m the map
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 * 
	 */
	public void setPosition(int x, int y, MapOne m) {
		if((x >= 0 && x < m.getNumRow()) && (y >= 0 && y < m.getNumCol())) {
			m.array[x][y].hasItem = true;
			m.array[x][y].item = this;
		} else {
			//invalid move
			return;
		}
	}
	
	public abstract void draw(Graphics g, int x, int y);
	
	@Override
	public boolean equals(Object i) {
		if(this instanceof HealthPotion && i instanceof HealthPotion) {
			return true;
		} else if(this instanceof SpeedShoes && i instanceof SpeedShoes) {
			return true;
		} else if (this instanceof StrengthPotion && i instanceof StrengthPotion) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * toString
	 * 
	 * Retrieves information about the different items.
	 * 
	 * @return String
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public String toString() {
		String result = "";
		if(this instanceof HealthPotion) {
			result += "Health Potion: restores 30 hp";
		} else if(this instanceof StrengthPotion) {
			result += "Strength Potion: adds 20 to attack";
		} else if(this instanceof Trap) {
			result+= "Trap: lay trap on tile to instantly kill enemy";
		} else {
			result += "Speed Shoes: move 1 extra space";
		}
		return result;
	}

}
