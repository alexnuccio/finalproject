package model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Cursor {
	int row, col;

	public Cursor() {
	}
	
	public int getRow() {
		return this.row;
	}
	
	public int getCol() {
		return this.col;
	}
	
	public void setPosition(int x, int y, MapOne m) {
		this.row = x;
		this.col = y;
		m.array[x][y].hasCursor = true;
	}
	
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
			if((currRow + 1) > 7) {
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
			if((currCol + 1) > 7) {
				//invalid move
				return false;
			}
			this.setPosition(currRow, currCol + 1, m);
			m.array[currRow][currCol].hasCursor = false;
			break;
		}
		return true;
	}

}
