package model;

import java.awt.Graphics;
import java.awt.Image;

import controller.GameController;
import view.GameView;

/**
 * This class is the abstract Unit class. All decendents will inherit these methods
 * 
 * @author Alex Nuccio, Eric Myre, Angel Cornejo
 *
 */
public abstract class Unit {

	public String name;
	public Player player;
	public String type;
	public int hitpoints;
	public int maxhp;
	public int moveMultiplier;
	public int attack;
	public boolean isTurn;
	public static int currMove;
	public Team team; //team unit is on (USER or AI)
	private static final long serialVersionUID = 1L;
	
	/**
	 * Unit
	 * 
	 * This is the constructor for the abstract unit class
	 * 
	 * @param name (String)
	 * @param player (Player)
	 */
	public Unit(String name, Player player) {
		currMove = 0;
		this.isTurn = false;
		this.name = name;
		this.player = player;
		player.addUnit(this);
		moveMultiplier = 2;
		hitpoints = maxhp = 100;
		attack = 20;
	}
	
	public void setTeam(Team t) {
		this.team = t;
	}
	
	public Team getTeam() {
		if(this.team != null) {
			return this.team;
		} else {
			return null;
		}
	}
	
	public abstract void draw(Graphics g, int x, int y);
	
	public abstract Image getImage();
	
	public boolean setPosition(int row, int col, MapOne m) {
		if(m.array[row][col].isOccupied() == false) {
			m.array[row][col].setOccupant(this);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * move
	 * 
	 * this method moves the unit
	 * 
	 * @param direction (int)
	 * @return boolean
	 */
		
	public boolean move(Direction D, MapOne m) {
		int currRow, currCol;
		currRow = this.getRow(m);
		currCol = this.getCol(m);
		switch(D){
		case UP:
			if((currRow - 1) < 0) {
				//invalid move
				return false;
			}
			if(m.array[currRow - 1][currCol].isOccupied() == true || m.array[currRow - 1][currCol].isOccupiable() == false) {
				return false;
			} else if ((currRow - 1) >= 0) {
				if(m.array[currRow - 1][currCol].hasItem == true) {
					//if tile we're moving to has an item
					this.player.addItem(m.array[currRow - 1][currCol].getItem());
					m.array[currRow - 1][currCol].hasItem = false;
				}
				if(m.array[currRow - 1][currCol].hasATrap()){
					m.array[currRow][currCol].isOccupied = false;
					GameController.myList.remove(this);
					return true;
				}
				m.array[currRow - 1][currCol].setOccupant(this);
				m.array[currRow][currCol].isOccupied = false;
			} else {
				// do nothing
			}
			break;
		case DOWN:
			if((currRow + 1) >= m.getNumRow()) {
				//invalid move
				return false;
			}
			if(m.array[currRow + 1][currCol].isOccupied() == true || m.array[currRow + 1][currCol].isOccupiable() == false) {
				return false;
			} else if ((currRow + 1) < m.getNumRow()) {
				if(m.array[currRow + 1][currCol].hasItem == true) {
					//if tile we're moving to has an item
					this.player.addItem(m.array[currRow + 1][currCol].getItem());
					m.array[currRow + 1][currCol].hasItem = false;
				}
				if(m.array[currRow + 1][currCol].hasATrap()) {
					m.array[currRow][currCol].isOccupied = false;
					GameController.myList.remove(this);
					return true;
				}
				m.array[currRow + 1][currCol].setOccupant(this);
				m.array[currRow][currCol].isOccupied = false;
			} else {
				// do nothing
			}
			break;
		case LEFT:
			if((currCol - 1) < 0) {
				//invalid move
				return false;
			}
			if(m.array[currRow][currCol - 1].isOccupied() == true || m.array[currRow][currCol - 1].isOccupiable() == false) {
				return false;
			} else if ((currCol - 1) >= 0) {
				if(m.array[currRow][currCol - 1].hasItem == true) {
					//if tile we're moving to has an item
					this.player.addItem(m.array[currRow][currCol - 1].getItem());
					m.array[currRow][currCol - 1].hasItem = false;
				}
				if(m.array[currRow][currCol - 1].hasATrap()) {
					m.array[currRow][currCol].isOccupied = false;
					GameController.myList.remove(this);
					return true;
				}
				m.array[currRow][currCol - 1].setOccupant(this);
				m.array[currRow][currCol].isOccupied = false;
			}else {
				//do nothing
			}
			break;
		case RIGHT:
			if((currCol + 1) >= m.getNumCol()) {
				//invalid move
				return false;
			}
			if(m.array[currRow][currCol + 1].isOccupied() == true || m.array[currRow][currCol + 1].isOccupiable() == false) {
				return false;
			} else if ((currCol + 1) < m.getNumCol()) {
				if(m.array[currRow][currCol + 1].hasItem == true) {
					//if tile we're moving to has an item
					this.player.addItem(m.array[currRow][currCol + 1].getItem());
					m.array[currRow][currCol + 1].hasItem = false;
				}
				if(m.array[currRow][currCol + 1].hasATrap()) {
					m.array[currRow][currCol].isOccupied = false;
					GameController.myList.remove(this);
					return true;
				}
				m.array[currRow][currCol + 1].setOccupant(this);
				m.array[currRow][currCol].isOccupied = false;
			}else {
				//do nothing
			}
			break;
		}
		Unit.currMove++;
		m.repaint();
		return true;
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
		if(item instanceof HealthPotion){
			if(maxhp > hitpoints){
				if(hitpoints < maxhp-30){
					hitpoints += 30;
				}else{
					hitpoints = maxhp;
				}
				return true;
			}else{
				return false;
			}
		}else if(item instanceof StrengthPotion){
			attack += 20;
			return true;
		}else if(item instanceof SpeedShoes){
			moveMultiplier++;
			return true;
		}else if(item instanceof Trap){
			MapOne m = GameView.getMap();
			m.array[getRow(m)][getCol(m)].trap = true;
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * attack
	 * 
	 * this method allows the unit to attack other units
	 * 
	 * @param direction (int)
	 * @return boolean
	 */
	public boolean attack(Direction D, MapOne m) {
		int row, col;
		row = this.getRow(m);
		col = this.getCol(m);
		switch(D){
		case UP:
			if((row - 1) < 0) {
				//invalid move
				return false;
			}
			if(m.array[row - 1][col].isOccupied() == true) {
				m.array[row - 1][col].getOccupant().setHitpoints(
						m.array[row - 1][col].getOccupant().getHitpoints() - 
						this.attack);
				if(m.array[row - 1][col].getOccupant().getHitpoints() <= 0) {
					m.array[row - 1][col].isOccupied = false;
				}
			} else {
				return false;
			}
			break;
		case DOWN:
			if((row + 1) > (m.getNumRow() - 1)) {
				//invalid move
				return false;
			}
			if(m.array[row + 1][col].isOccupied() == true) {
				m.array[row + 1][col].getOccupant().setHitpoints(
						m.array[row + 1][col].getOccupant().getHitpoints() - 
						this.attack);
				if(m.array[row + 1][col].getOccupant().getHitpoints() <= 0) {
					m.array[row + 1][col].isOccupied = false;
				}
				
			} else {
				return false;
			}
			break;
		case LEFT:
			if((col - 1) < 0) {
				//invalid move
				return false;
			}
			if(m.array[row][col - 1].isOccupied() == true) {
				m.array[row][col - 1].getOccupant().setHitpoints(
						m.array[row][col - 1].getOccupant().getHitpoints() - 
						this.attack);
				if(m.array[row][col - 1].getOccupant().getHitpoints() <= 0) {
					m.array[row][col - 1].isOccupied = false;
				}
			} else {
				return false;
			}
			break;
		case RIGHT:
			if((col + 1) > (m.getNumCol() - 1)) {
				//invalid move
				return false;
			}
			if(m.array[row][col + 1].isOccupied() == true) {
				m.array[row][col + 1].getOccupant().setHitpoints(
						m.array[row][col + 1].getOccupant().getHitpoints() - 
						this.attack);
				if(m.array[row][col + 1].getOccupant().getHitpoints() <= 0) {
					m.array[row][col + 1].isOccupied = false;
				}
			} else {
				return false;
			}
			break;
		}
		m.repaint();
		return true;
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
	
	public int getRow(MapOne m) {
		for(int i = 0; i < m.getNumRow(); i++) {
			for(int j = 0; j < m.getNumCol(); j++) {
				if(m.array[i][j].isOccupied() == false) {
					continue;
				} else if(m.array[i][j].getOccupant().equals(this)) {
					return i;
				}
			}
		}
		return -1; //-1 if it is not present in array
	}
	
	public int getCol(MapOne m) {
		for(int i = 0; i < m.getNumRow(); i++) {
			for(int j = 0; j < m.getNumCol(); j++) {
				if(m.array[i][j].isOccupied() == false) {
					continue;
				} else if(m.array[i][j].getOccupant().equals(this)) {
					return j;
				}
			}
		}
		return -1; //-1 if it is not present in array
	}
	
	public int getHitpoints() {
		return this.hitpoints;
	}
	
	public void setHitpoints(int hit) {
		this.hitpoints = hit;
	}
	
	public boolean equals(Unit other) {
		if(this.name.equals(other.name)) {
			return true;
		} else {
			return false;
		}
	}
}
