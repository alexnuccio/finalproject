package model;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MapTwo extends MapOne {

	BufferedImage river;
	public MapTwo() {
		
		try {
			plain = ImageIO.read(new File("plain-test.png"));
			river = ImageIO.read(new File("river-tile.png"));
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
				if(j > 6) {
					// make col 7-9 a river
					array[i][j] = new RiverTile();
				} else {
					//make col 0-6 plain tile
					array[i][j] = new PlainTile();
				}
				
			}
			
		}
		this.setPreferredSize(new Dimension(10 * plain.getWidth(), 7 * plain.getHeight()));
		this.setVisible(true);
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 10; j++) {
				if(array[i][j] instanceof PlainTile) {
					g.drawImage(plain, j * plain.getWidth(), i * plain.getHeight(), null);
				} else {
					g.drawImage(river, j* plain.getWidth(), i * plain.getHeight(), null);
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
