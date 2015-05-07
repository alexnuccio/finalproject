package model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import controller.GameController;
import view.GameView;

/**
 * Unit
 * 
 * This class is the abstract Unit class. All units will inherit these
 * methods
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
	public Team team; // team unit is on (USER or AI)
	private static final long serialVersionUID = 1L;
	public int xPosition, yPosition;

	/**
	 * Unit
	 * 
	 * This is the constructor for the abstract unit class
	 * 
	 * @param name	the name of the unit
	 *            
	 * @param player	the player the Unit belongs to
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 *           
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

	/**
	 * setTeam
	 * 
	 * Sets the team for the unit
	 * 
	 * @param t	the team
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public void setTeam(Team t) {
		this.team = t;
	}

	/**
	 * getTeam
	 * 
	 * Gets the team of the unit
	 * 
	 * @return Team
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public Team getTeam() {
		if (this.team != null) {
			return this.team;
		} else {
			return null;
		}
	}

	public abstract void draw(Graphics g, int x, int y);

	public abstract BufferedImage getImage();

	/**
	 * setPosition
	 * 
	 * Sets the unit's position
	 * 
	 * @param row	the row the unit occupies
	 * @param col	the column the unit occupies
	 * @param m		the map
	 * @return boolean
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public boolean setPosition(int row, int col, MapOne m) {
		this.xPosition = col * this.getImage().getWidth();
		this.yPosition = row * this.getImage().getHeight();
		if (m.array[row][col].isOccupied() == false) {
			m.array[row][col].setOccupant(this);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * move
	 * 
	 * This method moves the unit
	 * 
	 * @param d	the direction the unit will move
	 * 
	 * @param m the map
	 *            
	 * @return boolean
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */

	public boolean move(Direction D, MapOne m) {
		int currRow, currCol;
		currRow = this.getRow(m);
		currCol = this.getCol(m);
		switch (D) {
		case UP:
			if ((currRow - 1) < 0) {
				// invalid move
				return false;
			}
			if (m.array[currRow - 1][currCol].isOccupied() == true
					|| m.array[currRow - 1][currCol].isOccupiable() == false) {
				return false;
			} else if ((currRow - 1) >= 0) {
				if (m.array[currRow - 1][currCol].hasItem == true) {
					// if tile we're moving to has an item
					this.player
							.addItem(m.array[currRow - 1][currCol].getItem());
					m.array[currRow - 1][currCol].hasItem = false;
				}
				if (m.array[currRow - 1][currCol].hasATrap()
						&& this.getTeam() == Team.AI) {
					m.array[currRow][currCol].isOccupied = false;
					GameController.myList.remove(this);
					GameController.ai.units.remove(this);
					m.array[currRow - 1][currCol].trap = false;
					Unit.currMove = 10;
					return true;
				}
				m.array[currRow - 1][currCol].setOccupant(this);
				m.array[currRow][currCol].isOccupied = false;
				Unit.currMove += m.array[currRow - 1][currCol].moveCost;
			} else {
				// do nothing
			}
			break;
		case DOWN:
			if ((currRow + 1) >= m.getNumRow()) {
				// invalid move
				return false;
			}
			if (m.array[currRow + 1][currCol].isOccupied() == true
					|| m.array[currRow + 1][currCol].isOccupiable() == false) {
				return false;
			} else if ((currRow + 1) < m.getNumRow()) {
				if (m.array[currRow + 1][currCol].hasItem == true) {
					// if tile we're moving to has an item
					this.player
							.addItem(m.array[currRow + 1][currCol].getItem());
					m.array[currRow + 1][currCol].hasItem = false;
				}
				if (m.array[currRow + 1][currCol].hasATrap()
						&& this.getTeam() == Team.AI) {
					m.array[currRow][currCol].isOccupied = false;
					GameController.myList.remove(this);
					GameController.ai.units.remove(this);
					m.array[currRow + 1][currCol].trap = false;
					Unit.currMove = 10;
					return true;
				}
				m.array[currRow + 1][currCol].setOccupant(this);
				m.array[currRow][currCol].isOccupied = false;
				Unit.currMove += m.array[currRow + 1][currCol].moveCost;
			} else {
				// do nothing
			}
			break;
		case LEFT:
			if ((currCol - 1) < 0) {
				// invalid move
				return false;
			}
			if (m.array[currRow][currCol - 1].isOccupied() == true
					|| m.array[currRow][currCol - 1].isOccupiable() == false) {
				return false;
			} else if ((currCol - 1) >= 0) {
				if (m.array[currRow][currCol - 1].hasItem == true) {
					// if tile we're moving to has an item
					this.player
							.addItem(m.array[currRow][currCol - 1].getItem());
					m.array[currRow][currCol - 1].hasItem = false;
				}
				if (m.array[currRow][currCol - 1].hasATrap()
						&& this.getTeam() == Team.AI) {
					m.array[currRow][currCol].isOccupied = false;
					GameController.myList.remove(this);
					GameController.ai.units.remove(this);
					m.array[currRow][currCol - 1].trap = false;
					Unit.currMove = 10;
					return true;
				}
				m.array[currRow][currCol - 1].setOccupant(this);
				m.array[currRow][currCol].isOccupied = false;
				Unit.currMove += m.array[currRow][currCol - 1].moveCost;
			} else {
				// do nothing
			}
			break;
		case RIGHT:
			if ((currCol + 1) >= m.getNumCol()) {
				// invalid move
				return false;
			}
			if (m.array[currRow][currCol + 1].isOccupied() == true
					|| m.array[currRow][currCol + 1].isOccupiable() == false) {
				return false;
			} else if ((currCol + 1) < m.getNumCol()) {
				if (m.array[currRow][currCol + 1].hasItem == true) {
					// if tile we're moving to has an item
					this.player
							.addItem(m.array[currRow][currCol + 1].getItem());
					m.array[currRow][currCol + 1].hasItem = false;
				}
				if (m.array[currRow][currCol + 1].hasATrap()
						&& this.getTeam() == Team.AI) {
					m.array[currRow][currCol].isOccupied = false;
					GameController.myList.remove(this);
					GameController.ai.units.remove(this);
					m.array[currRow][currCol + 1].trap = false;
					Unit.currMove = 10;
					return true;
				}
				m.array[currRow][currCol + 1].setOccupant(this);
				m.array[currRow][currCol].isOccupied = false;
				Unit.currMove += m.array[currRow][currCol + 1].moveCost;
			} else {
				// do nothing
			}
			break;
		}
		m.repaint();
		return true;
	}

	/**
	 * useItem
	 * 
	 * This method allows the unit to use an item
	 * 
	 * @param item	the item to be used
	 *           
	 * @return boolean
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public boolean useItem(Item item) {
		if (item instanceof HealthPotion) {
			if (maxhp > hitpoints) {
				if (hitpoints < maxhp - 30) {
					hitpoints += 30;
				} else {
					hitpoints = maxhp;
				}
				return true;
			} else {
				return false;
			}
		} else if (item instanceof StrengthPotion) {
			attack += 20;
			return true;
		} else if (item instanceof SpeedShoes) {
			moveMultiplier++;
			return true;
		} else if (item instanceof Trap) {
			MapOne m = GameView.getMap();
			m.array[getRow(m)][getCol(m)].trap = true;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * attack
	 * 
	 * This method allows the unit to attack other units
	 * 
	 * @param d	the direction to attack in
	 * 
	 * @param m	the map
	 *            
	 * @return boolean
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public boolean attack(Direction D, MapOne m) {
		int row, col;
		row = this.getRow(m);
		col = this.getCol(m);
		switch (D) {
		case UP:
			if ((row - 1) < 0) {
				// invalid move
				return false;
			}
			if (m.array[row - 1][col].isOccupied() == true
					&& this.getTeam() != m.array[row - 1][col].getOccupant()
							.getTeam()) {
				m.array[row - 1][col].getOccupant().setHitpoints(
						m.array[row - 1][col].getOccupant().getHitpoints()
								- this.attack);
				if (m.array[row - 1][col].getOccupant().getHitpoints() <= 0) {
					if (m.array[row - 1][col].getOccupant().getTeam() == Team.AI) {
						GameController.ai.units.remove(m.array[row - 1][col].getOccupant());
						GameController.myList.remove(m.array[row - 1][col].getOccupant());
					} else {
						GameController.player1.units.remove(m.array[row - 1][col].getOccupant());
						GameController.myList.remove(m.array[row - 1][col].getOccupant());
					}
					m.array[row - 1][col].isOccupied = false;

				}
			} else {
				return false;
			}
			break;
		case DOWN:
			if ((row + 1) > (m.getNumRow() - 1)) {
				// invalid move
				return false;
			}
			if (m.array[row + 1][col].isOccupied() == true
					&& this.getTeam() != m.array[row + 1][col].getOccupant()
							.getTeam()) {
				m.array[row + 1][col].getOccupant().setHitpoints(
						m.array[row + 1][col].getOccupant().getHitpoints()
								- this.attack);
				if (m.array[row + 1][col].getOccupant().getHitpoints() <= 0) {
					if (m.array[row + 1][col].getOccupant().getTeam() == Team.AI) {
						GameController.ai.units.remove(m.array[row + 1][col].getOccupant());
						GameController.myList.remove(m.array[row + 1][col].getOccupant());
					} else {
						GameController.player1.units.remove(m.array[row + 1][col].getOccupant());
						GameController.myList.remove(m.array[row + 1][col].getOccupant());
					}
					m.array[row + 1][col].isOccupied = false;

				}

			} else {
				return false;
			}
			break;
		case LEFT:
			if ((col - 1) < 0) {
				// invalid move
				return false;
			}
			if (m.array[row][col - 1].isOccupied() == true
					&& this.getTeam() != m.array[row][col - 1].getOccupant()
							.getTeam()) {
				m.array[row][col - 1].getOccupant().setHitpoints(
						m.array[row][col - 1].getOccupant().getHitpoints()
								- this.attack);
				if (m.array[row][col - 1].getOccupant().getHitpoints() <= 0) {
					if (m.array[row][col - 1].getOccupant().getTeam() == Team.AI) {
						GameController.ai.units.remove(m.array[row][col - 1].getOccupant());
						GameController.myList.remove(m.array[row][col - 1].getOccupant());
					} else {
						GameController.player1.units.remove(m.array[row][col - 1].getOccupant());
						GameController.myList.remove(m.array[row][col - 1].getOccupant());
					}
					m.array[row][col - 1].isOccupied = false;

				}
			} else {
				return false;
			}
			break;
		case RIGHT:
			if ((col + 1) > (m.getNumCol() - 1)) {
				// invalid move
				return false;
			}
			if (m.array[row][col + 1].isOccupied() == true && this.getTeam() != m.array[row][col + 1].getOccupant()
					.getTeam()) {
				m.array[row][col + 1].getOccupant().setHitpoints(
						m.array[row][col + 1].getOccupant().getHitpoints()
								- this.attack);
				if (m.array[row][col + 1].getOccupant().getHitpoints() <= 0) {
					if (m.array[row][col + 1].getOccupant().getTeam() == Team.AI) {
						GameController.ai.units.remove(m.array[row][col + 1].getOccupant());
						GameController.myList.remove(m.array[row][col + 1].getOccupant());
					} else {
						GameController.player1.units.remove(m.array[row][col + 1].getOccupant());
						GameController.myList.remove(m.array[row][col + 1].getOccupant());
					}
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
	 * Returns the name of the unit
	 * 
	 * @return String
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * getPlayer
	 * 
	 * Return the player this unit belongs to
	 * 
	 * @return Player 
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public Player getPlayer() {
		return this.player;
	}

	/**
	 * getRow
	 * 
	 * Returns the row the unit is on
	 * 
	 * @param m	the map
	 * 
	 * @return int
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public int getRow(MapOne m) {
		for (int i = 0; i < m.getNumRow(); i++) {
			for (int j = 0; j < m.getNumCol(); j++) {
				if (m.array[i][j].isOccupied() == false) {
					continue;
				} else if (m.array[i][j].getOccupant().equals(this)) {
					return i;
				}
			}
		}
		return -1; // -1 if it is not present in array
	}

	/**
	 * getCol
	 * 
	 * Returns the column this unit is on
	 * 
	 * @param m the map
	 * 
	 * @return int
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public int getCol(MapOne m) {
		for (int i = 0; i < m.getNumRow(); i++) {
			for (int j = 0; j < m.getNumCol(); j++) {
				if (m.array[i][j].isOccupied() == false) {
					continue;
				} else if (m.array[i][j].getOccupant().equals(this)) {
					return j;
				}
			}
		}
		return -1; // -1 if it is not present in array
	}

	/**
	 * getHitPoints
	 * 
	 * Returns the current hit points of the unit
	 * 
	 * @return int
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public int getHitpoints() {
		return this.hitpoints;
	}

	/**
	 * setHitPoints
	 * 
	 * Sets the unit's hit points to be a certain value
	 * 
	 * @param hit	the hp value to be set
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public void setHitpoints(int hit) {
		this.hitpoints = hit;
	}

	/**
	 * equals
	 * 
	 * Determines whether two units are equal
	 * 
	 * @param other	the unit being compared to
	 * 
	 * @return boolean
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public boolean equals(Unit other) {
		if (this.name.equals(other.name)) {
			return true;
		} else {
			return false;
		}
	}
}
