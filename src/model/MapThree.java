package model;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * MapThree
 * 
 * The third map; extends MapOne.
 *
 * @author Alex Nuccio, Eric Myre, Angel Cornejo
 */
public class MapThree extends MapOne {

	BufferedImage forest;
	
	/**
	 * The constructor for the MapThree
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public MapThree() {
		
		try {
			plain = ImageIO.read(new File("plain-test.png"));
			forest = ImageIO.read(new File("Mountain.png"));
			cursor = ImageIO.read(new File("Cursor.png"));
			icon = ImageIO.read(new File("Icon.png"));
			indicator = ImageIO.read(new File("Indicator.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		array = new Tile[7][10];
		this.setLayout(new GridLayout(7, 10));
		for(int i = 0; i < 7; i++) {
			
			for(int j = 0; j < 10; j++) {
				switch(j){
				case 1:
				case 3:
				case 5:
				case 7:
					array[i][j] = new ForestTile();
					break;
				default:
					array[i][j] = new PlainTile();
					break;
				}
			}
			
		}
		this.setPreferredSize(new Dimension(10 * plain.getWidth(), 7 * plain.getHeight()));
		this.setVisible(true);
		
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
				if(array[i][j] instanceof PlainTile) {
					g.drawImage(plain, j * plain.getWidth(), i * plain.getHeight(), null);
				} else {
					g.drawImage(forest, j* plain.getWidth(), i * plain.getHeight(), null);
				}
				
				
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
}
