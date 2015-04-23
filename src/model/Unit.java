package model;

import java.awt.Graphics;
import java.awt.Image;

/**
 * This class is the abstract Unit class. All decendents will inherit these methods
 * 
 * @author Alex Nuccio, Eric Myre, Angel Cornejo
 *
 */
public abstract class Unit {

	public String name;
	public Player player;
	public int row;
	public int col;
	public int hitpoints;
	public int maxHP;
	public int moveMultiplier;
	public int attack;
	public static int currMove;
	
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
		this.name = name;
		this.player = player;
		player.addUnit(this);
		moveMultiplier = 2;
		hitpoints = 100;
		maxHP = 100;
		attack = 20;
	}
	
	public abstract void draw(Graphics g, int x, int y);
	
	public abstract Image getImage();
	
	public boolean setPosition(int row, int col, MapOne m) {
		if(m.array[row][col].isOccupied() == false) {
			m.array[row][col].setOccupant(this);
			this.row = row;
			this.col = col;
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
			if(m.array[currRow - 1][currCol].isOccupied() == true) {
				return false;
			} else if ((currRow - 1) >= 0) {
				m.array[currRow - 1][currCol].setOccupant(this);
				m.array[currRow][currCol].isOccupied = false;
			} else {
				// do nothing
			}
			break;
		case DOWN:
			if((currRow + 1) > 7) {
				//invalid move
				return false;
			}
			if(m.array[currRow + 1][currCol].isOccupied() == true) {
				return false;
			} else if ((currRow + 1) <= 7) {
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
			if(m.array[currRow][currCol - 1].isOccupied() == true) {
				return false;
			} else if ((currCol - 1) >= 0) {
				m.array[currRow][currCol - 1].setOccupant(this);
				m.array[currRow][currCol].isOccupied = false;
			}else {
				//do nothing
			}
			break;
		case RIGHT:
			if((currCol + 1) > 7) {
				//invalid move
				return false;
			}
			if(m.array[currRow][currCol + 1].isOccupied() == true) {
				return false;
			} else if ((currCol + 1) <= 7) {
				m.array[currRow][currCol + 1].setOccupant(this);
				m.array[currRow][currCol].isOccupied = false;
			}else {
				//do nothing
			}
			break;
		}
		Unit.currMove++;
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
		return item.use(this);
	}
	
	/**
	 * attack
	 * 
	 * this method allows the unit to attack other units
	 * 
	 * @param D (Direction)
	 * @param m (MapOne)
	 * @return boolean
	 */
	public boolean attack(Direction D, MapOne m) {
<<<<<<< HEAD
		int row, col;
		row = this.getRow(m);
		col = this.getCol(m);
=======
>>>>>>> origin/master
		switch(D){
		case UP:
			if((row - 1) < 0) {
				//invalid move
				return false;
			}
			if(m.array[row - 1][col].isOccupied() == true) {
				m.array[row - 1][col].getOccupant().setHitpoints(
<<<<<<< HEAD
						m.array[row - 1][col].getOccupant().getHitpoints() - 
						this.attack);
				if(m.array[row - 1][col].getOccupant().getHitpoints() <= 0) {
					m.array[row - 1][col].isOccupied = false;
				}
			} else {
				return false;
=======
						m.array[row - 1][col].getOccupant(this).getHitpoints() - 
						this.attack);
>>>>>>> origin/master
			}
			break;
		case DOWN:
			if((row + 1) > 7) {
				//invalid move
				return false;
			}
			if(m.array[row + 1][col].isOccupied() == true) {
				m.array[row + 1][col].getOccupant().setHitpoints(
<<<<<<< HEAD
						m.array[row + 1][col].getOccupant().getHitpoints() - 
						this.attack);
				if(m.array[row + 1][col].getOccupant().getHitpoints() <= 0) {
					m.array[row + 1][col].isOccupied = false;
				}
				
			} else {
				return false;
=======
						m.array[row + 1][col].getOccupant(this).getHitpoints() - 
						this.attack);
>>>>>>> origin/master
			}
			break;
		case LEFT:
			if((col - 1) < 0) {
				//invalid move
				return false;
			}
			if(m.array[row][col - 1].isOccupied() == true) {
<<<<<<< HEAD
				m.array[row][col - 1].getOccupant().setHitpoints(
						m.array[row][col - 1].getOccupant().getHitpoints() - 
						this.attack);
				if(m.array[row][col - 1].getOccupant().getHitpoints() <= 0) {
					m.array[row][col - 1].isOccupied = false;
				}
			} else {
				return false;
=======
				m.array[row][col - 1].setOccupant().setHitpoints(
						m.array[row][col - 1].getOccupant(this).getHitpoints() - 
						this.attack);
>>>>>>> origin/master
			}
			break;
		case RIGHT:
			if((col + 1) > 7) {
				//invalid move
				return false;
			}
			if(m.array[row][col + 1].isOccupied() == true) {
<<<<<<< HEAD
				m.array[row][col + 1].getOccupant().setHitpoints(
						m.array[row][col + 1].getOccupant().getHitpoints() - 
						this.attack);
				if(m.array[row][col + 1].getOccupant().getHitpoints() <= 0) {
					m.array[row][col + 1].isOccupied = false;
				}
			} else {
				return false;
=======
				m.array[row][col + 1].setOccupant().setHitpoints(
						m.array[row][col + 1].getOccupant(this).getHitpoints() - 
						this.attack);
>>>>>>> origin/master
			}
			break;
		}
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
		/*for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(m.array[i][j].isOccupied() == false) {
					continue;
				} else if(m.array[i][j].getOccupant().equals(this)) {
					return i;
				}
			}
		}
		return -1; //-1 if it is not present in array
		*/
		return row;
	}
	
	public int getCol(MapOne m) {
		/*for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(m.array[i][j].isOccupied() == false) {
					continue;
				} else if(m.array[i][j].getOccupant().equals(this)) {
					return j;
				}
			}
		}
		return -1; //-1 if it is not present in array
		*/
		return col;
	}
	
	public Tile getPosition(MapOne m){
		return m.array[row][col];
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
