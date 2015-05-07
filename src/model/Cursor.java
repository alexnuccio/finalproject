package model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * Cursor
 * The cursor that players will control that allows them to select tiles within the game.
 * 
 * @author Alex Nuccio, Eric Myre, Angel Cornejo
 */
public class Cursor {
	int row, col;

	/**
	 * Cursor
	 * The constructor the cursor. Empty.
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public Cursor() {
	}
	
	/**
	 * getRow
	 * 
	 * returns the row the cursor is currently at
	 * 
	 * @return int
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public int getRow() {
		return this.row;
	}
	
	/**
	 * getCol
	 * 
	 * returns the column the cursor is currently at
	 * 
	 * @return int
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public int getCol() {
		return this.col;
	}
	
	/**
	 * setPosition
	 * 
	 * Sets the position to a location on the map
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
			this.row = x;
			this.col = y;
			m.array[x][y].hasCursor = true;
		} else {
			//invalid move
			return;
		}
	}
	
	/**
	 * move
	 * 
	 * Moves the cursor.
	 * 
	 * @param d	the direction to move
	 * @param m	the map
	 * @return boolean
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public boolean move(Direction d, MapOne m) {
		int currRow = getRow();
		int currCol = getCol();
		switch(d) {
		case UP:
			if((currRow - 1) < 0) {
				//invalid move
				return false;
			}
			this.setPosition(currRow - 1, currCol, m);
			m.array[currRow][currCol].hasCursor = false;
			break;
		case DOWN:
			if((currRow + 1) >= m.getNumRow()) {
				//invalid move
				return false;
			}
			this.setPosition(currRow + 1, currCol, m);
			m.array[currRow][currCol].hasCursor = false;
			break;
		case LEFT:
			if((currCol - 1) < 0) {
				//invalid move
				return false;
			}
			this.setPosition(currRow, currCol - 1, m);
			m.array[currRow][currCol].hasCursor = false;
			break;
		case RIGHT:
			if((currCol + 1) >= m.getNumCol()) {
				//invalid move
				return false;
			}
			this.setPosition(currRow, currCol + 1, m);
			m.array[currRow][currCol].hasCursor = false;
			break;
		}
		m.repaint();
		return true;
	}

}
