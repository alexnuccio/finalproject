package model;

import java.awt.Graphics;

/**
 * this is the Item class
 * 
 * @author Team Barbs
 *
 */
public abstract class Item {

	private static final long serialVersionUID = 1L;
	/**
	 * Item
	 * 
	 * this is the constructor
	 * 
	 */
	public Item() {
		
	}

	
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
