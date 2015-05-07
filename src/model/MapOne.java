package model;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * MapOne
 * 
 * The first map, which is an array of Tiles. 
 * Also the root class of the other two maps.
 * 
 * @author Alex Nuccio, Eric Myre, Angel Cornejo
 * 
 */
public class MapOne extends JPanel {

	public Tile[][] array;
	public BufferedImage plain, cursor, icon, indicator;
	
	
	/**
	 * MapOne
	 * 
	 * Constructor for MapOne
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public MapOne() {
		try {
			plain = ImageIO.read(new File("plain-test.png"));
			cursor = ImageIO.read(new File("Cursor.png"));
			icon = ImageIO.read(new File("Icon.png"));
			indicator = ImageIO.read(new File("Indicator.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		array = new PlainTile[7][10];
		this.setLayout(new GridLayout(7, 10));
		for(int i = 0; i < 7; i++) {
			
			for(int j = 0; j < 10; j++) {
				array[i][j] = new PlainTile();
			}
		}
		this.setPreferredSize(new Dimension(10 * plain.getWidth(), 7 * plain.getHeight()));
		this.setVisible(true);
		
	}
	
	/**
	 * getNumRow
	 * 
	 * Returns the number of rows.
	 * 
	 * @return int
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public int getNumRow() {
		return 7;
	}
	
	/**
	 * getNumCol
	 * 
	 * Returns the number of columns
	 * 
	 * @return int
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public int getNumCol() {
		return 10;
	}

	/**
	 * getImageWidth
	 * 
	 * Returns the width of the image.
	 * 
	 * @return int
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public int getImageWidth() {
		return plain.getWidth();
	}
	
	/**
	 * getImageHeight
	 * 
	 * Returns the height of the image.
	 * 
	 * @return int
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public int getImageHeight() {
		return plain.getHeight();
	}
	
	/**
	 * paintComponent
	 * 
	 * Draws the map.
	 * 
	 * @param g
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 10; j++) {
				g.drawImage(plain, j * plain.getWidth(), i * plain.getHeight(), null);
				
				if(array[i][j].isOccupied() == true) {
					array[i][j].getOccupant().draw(g, j * plain.getWidth(), i * plain.getHeight()); //has unit occupying tile draw itself 
					if(array[i][j].getOccupant().isTurn == true) {
						//draw symbol to show this unit is moving
						drawIcon(g, j, i);
					}
					if(array[i][j].getOccupant().getTeam() == Team.AI) {
						//draw red indicator to show on AI's team
						drawEnemyIndicator(g, j, i);
					}
				}
				if(array[i][j].hasCursor == true) {
					drawCursor(g, j, i);
				}
				if(array[i][j].hasItem == true) {
					array[i][j].getItem().draw(g, j * plain.getWidth(), i * plain.getHeight());
				}
			}
		}
	}
	
	/**
	 * drawCursor
	 * 
	 * Draws the cursor on the map.
	 * 
	 * @param g
	 * @param x	the x position on the map's grid
	 * @param y	the y position on the map's grid
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public void drawCursor(Graphics g, int x, int y) {
		g.drawImage(cursor, x * cursor.getWidth(), y * cursor.getHeight(), null);
	}
	
	/**
	 * drawIcon
	 * 
	 * Draws icon.
	 * 
	 * @param g
	 * @param x	the x position on the map's grid
	 * @param y	the y position on the map's grid
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public void drawIcon(Graphics g, int x, int y) {
		g.drawImage(icon, x * icon.getWidth(), y * icon.getHeight(), null);
	}
	
	/**
	 * drawEnemyIndicator
	 * 
	 * Draws the indicator that a unit is an enemy/
	 * 
	 * @param g
	 * @param x the x position on the map's grid
	 * @param y	the y position on the map's grid
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public void drawEnemyIndicator(Graphics g, int x, int y) {
		g.drawImage(indicator, x * indicator.getWidth(), y * indicator.getHeight(), null);
	}
	
	

}

