package model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This class represents the Mage unit
 * 
 * @author Alex Nuccio, Eric Myre, Angel Cornejo, Raymond
 *
 */
public class Mage extends Unit {
	BufferedImage img;
	/**
	 * Mage
	 * 
	 * this is the constructor of the Mage unit
	 * 
	 * @param name (String)
	 * @param player (Player)
	 */
	public Mage(String name, Player player) {
		super(name, player);
		try {
			img = ImageIO.read(new File("Mage.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.moveMultiplier = 2;
		this.hitpoints = 100;
		this.attack = 10;
	}

	@Override
	public void draw(Graphics g, int x, int y) {
		g.drawImage(img, x, y, null);
	}
	
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
				if(m.array[row - 1][col].isOccupied() == true){
					m.array[row - 1][col].getOccupant().setHitpoints(
							m.array[row - 1][col].getOccupant().getHitpoints() - 
							this.attack/2);
					if(m.array[row - 1][col].getOccupant().getHitpoints() <= 0) {
						m.array[row - 1][col].isOccupied = false;
					}
				}
				if((row - 3) >= 0) {
					if(m.array[row - 3][col].isOccupied() == true){
						m.array[row - 3][col].getOccupant().setHitpoints(
								m.array[row - 3][col].getOccupant().getHitpoints() - 
								this.attack/2);
						if(m.array[row - 3][col].getOccupant().getHitpoints() <= 0) {
							m.array[row - 3][col].isOccupied = false;
						}
					}
				}
				if((col - 1) >= 0){
					if(m.array[row - 2][col - 1].isOccupied() == true){
						m.array[row - 2][col - 1].getOccupant().setHitpoints(
								m.array[row - 2][col - 1].getOccupant().getHitpoints() - 
								this.attack/2);
						if(m.array[row - 2][col - 1].getOccupant().getHitpoints() <= 0) {
							m.array[row - 2][col - 1].isOccupied = false;
						}
					}
				}
				if((col + 1) <= (m.getNumCol() - 1)){
					if(m.array[row - 2][col + 1].isOccupied() == true){
						m.array[row - 2][col + 1].getOccupant().setHitpoints(
								m.array[row - 2][col + 1].getOccupant().getHitpoints() - 
								this.attack/2);
						if(m.array[row - 2][col + 1].getOccupant().getHitpoints() <= 0) {
							m.array[row - 2][col + 1].isOccupied = false;
						}
					}
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
				if(m.array[row + 1][col].isOccupied() == true){
					m.array[row + 1][col].getOccupant().setHitpoints(
							m.array[row + 1][col].getOccupant().getHitpoints() - 
							this.attack/2);
					if(m.array[row + 1][col].getOccupant().getHitpoints() <= 0) {
						m.array[row + 1][col].isOccupied = false;
					}
				}
				if((row + 3) <= (m.getNumRow() - 1)) {
					if(m.array[row + 3][col].isOccupied() == true){
						m.array[row + 3][col].getOccupant().setHitpoints(
								m.array[row + 3][col].getOccupant().getHitpoints() - 
								this.attack/2);
						if(m.array[row + 3][col].getOccupant().getHitpoints() <= 0) {
							m.array[row + 3][col].isOccupied = false;
						}
					}
				}
				if((col - 1) >= 0){
					if(m.array[row + 2][col - 1].isOccupied() == true){
						m.array[row + 2][col - 1].getOccupant().setHitpoints(
								m.array[row + 2][col - 1].getOccupant().getHitpoints() - 
								this.attack/2);
						if(m.array[row + 2][col - 1].getOccupant().getHitpoints() <= 0) {
							m.array[row + 2][col - 1].isOccupied = false;
						}
					}
				}
				if((col + 1) <= (m.getNumCol() - 1)){
					if(m.array[row + 2][col + 1].isOccupied() == true){
						m.array[row + 2][col + 1].getOccupant().setHitpoints(
								m.array[row + 2][col + 1].getOccupant().getHitpoints() - 
								this.attack/2);
						if(m.array[row + 2][col + 1].getOccupant().getHitpoints() <= 0) {
							m.array[row + 2][col + 1].isOccupied = false;
						}
					}
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
				if(m.array[row][col - 1].isOccupied() == true){
					m.array[row][col - 1].getOccupant().setHitpoints(
							m.array[row][col - 1].getOccupant().getHitpoints() - 
							this.attack/2);
					if(m.array[row][col - 1].getOccupant().getHitpoints() <= 0) {
						m.array[row][col - 1].isOccupied = false;
					}
				}
				if((col - 3) >= 0) {
					if(m.array[row][col - 3].isOccupied() == true){
						m.array[row][col - 3].getOccupant().setHitpoints(
								m.array[row][col - 3].getOccupant().getHitpoints() - 
								this.attack/2);
						if(m.array[row][col - 3].getOccupant().getHitpoints() <= 0) {
							m.array[row][col - 3].isOccupied = false;
						}
					}
				}
				if((row - 1) >= 0){
					if(m.array[row - 1][col - 2].isOccupied() == true){
						m.array[row - 1][col - 2].getOccupant().setHitpoints(
								m.array[row - 1][col - 2].getOccupant().getHitpoints() - 
								this.attack/2);
						if(m.array[row - 1][col - 2].getOccupant().getHitpoints() <= 0) {
							m.array[row - 1][col - 2].isOccupied = false;
						}
					}
				}
				if((row + 1) <= (m.getNumRow() - 1)){
					if(m.array[row + 1][col - 2].isOccupied() == true){
						m.array[row + 1][col - 2].getOccupant().setHitpoints(
								m.array[row + 1][col - 2].getOccupant().getHitpoints() - 
								this.attack/2);
						if(m.array[row + 1][col - 2].getOccupant().getHitpoints() <= 0) {
							m.array[row + 1][col - 2].isOccupied = false;
						}
					}
					
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
				if(m.array[row][col + 1].isOccupied() == true){
					m.array[row][col + 1].getOccupant().setHitpoints(
							m.array[row][col + 1].getOccupant().getHitpoints() - 
							this.attack/2);
					if(m.array[row][col + 1].getOccupant().getHitpoints() <= 0) {
						m.array[row][col + 1].isOccupied = false;
					}
				}
				if((col + 3) <= (m.getNumCol() - 1)) {
					if(m.array[row][col + 3].isOccupied() == true){
						m.array[row][col + 3].getOccupant().setHitpoints(
								m.array[row][col + 3].getOccupant().getHitpoints() - 
								this.attack/2);
						if(m.array[row][col + 3].getOccupant().getHitpoints() <= 0) {
							m.array[row][col + 3].isOccupied = false;
						}
					}
				}
				if((row - 1) >= 0){
					if(m.array[row - 1][col + 2].isOccupied() == true){
						m.array[row - 1][col + 2].getOccupant().setHitpoints(
								m.array[row - 1][col + 2].getOccupant().getHitpoints() - 
								this.attack/2);
						if(m.array[row - 1][col + 2].getOccupant().getHitpoints() <= 0) {
							m.array[row - 1][col + 2].isOccupied = false;
						}
					}
				}
				if((row + 1) <= (m.getNumRow() - 1)){
					if(m.array[row + 1][col + 2].isOccupied() == true){
						m.array[row + 1][col + 2].getOccupant().setHitpoints(
								m.array[row + 1][col + 2].getOccupant().getHitpoints() - 
								this.attack/2);
						if(m.array[row + 1][col + 2].getOccupant().getHitpoints() <= 0) {
							m.array[row + 1][col + 2].isOccupied = false;
						}
					}
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
	public Image getImage() {
		return this.img;
	}
}
