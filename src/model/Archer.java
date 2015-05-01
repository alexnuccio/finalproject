package model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * this class represents the Archer unit
 * 
 * @author Team Barbs
 *
 */
public class Archer extends Unit {	
	
	private BufferedImage img;
	
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
		// load in image
		try {
			img = ImageIO.read(new File("Archer.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.hitpoints = 100;
		this.moveMultiplier = 2;
		this.attack = 30;
	}

	/**
	 * attack
	 * 
	 * this method allows the unit to attack other units
	 * 
	 * @param Direction (D), MapOne (M)
	 * @return boolean
	 */
	public boolean attack(Direction D, MapOne m) {
		int row, col;
		row = this.getRow(m);
		col = this.getCol(m);
		switch(D){
		case UP:
			if((row - 2) < 0) {
				//invalid move
				return false;
			}
			if(m.array[row - 2][col].isOccupied() == true) {
				m.array[row - 2][col].getOccupant().setHitpoints(
						m.array[row - 2][col].getOccupant().getHitpoints() - 
						this.attack);
				if(m.array[row - 2][col].getOccupant().getHitpoints() <= 0) {
					m.array[row - 2][col].isOccupied = false;
				}
			} else {
				return false;
			}
			break;
		case DOWN:
			if((row + 2) > (m.getNumRow() - 1)) {
				//invalid move
				return false;
			}
			if(m.array[row + 2][col].isOccupied() == true) {
				m.array[row + 2][col].getOccupant().setHitpoints(
						m.array[row + 2][col].getOccupant().getHitpoints() - 
						this.attack);
				if(m.array[row + 2][col].getOccupant().getHitpoints() <= 0) {
					m.array[row + 2][col].isOccupied = false;
				}
				
			} else {
				return false;
			}
			break;
		case LEFT:
			if((col - 2) < 0) {
				//invalid move
				return false;
			}
			if(m.array[row][col - 2].isOccupied() == true) {
				m.array[row][col - 2].getOccupant().setHitpoints(
						m.array[row][col - 2].getOccupant().getHitpoints() - 
						this.attack);
				if(m.array[row][col - 2].getOccupant().getHitpoints() <= 0) {
					m.array[row][col - 2].isOccupied = false;
				}
			} else {
				return false;
			}
			break;
		case RIGHT:
			if((col + 2) > (m.getNumCol() - 1)) {
				//invalid move
				return false;
			}
			if(m.array[row][col + 2].isOccupied() == true) {
				m.array[row][col + 2].getOccupant().setHitpoints(
						m.array[row][col + 2].getOccupant().getHitpoints() - 
						this.attack);
				if(m.array[row][col + 2].getOccupant().getHitpoints() <= 0) {
					m.array[row][col + 2].isOccupied = false;
				}
			} else {
				return false;
			}
			break;
		}
		m.repaint();
		return true;
	}

	@Override
	public void draw(Graphics g, int x, int y) {
		g.drawImage(img, x, y, null);
	}

	@Override
	public Image getImage() {
		return this.img;
	}
}
