package model;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MapOne extends JPanel {

	public Tile[][] array;
	public BufferedImage plain, cursor, icon;
	
	public MapOne() {
		try {
			plain = ImageIO.read(new File("plain-test.png"));
			cursor = ImageIO.read(new File("Cursor.png"));
			icon = ImageIO.read(new File("Icon.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		array = new PlainTile[8][8];
		this.setLayout(new GridLayout(8, 8));
		for(int i = 0; i < 8; i++) {
			
			for(int j = 0; j < 8; j++) {
				array[i][j] = new PlainTile();
			}
		}
		this.setPreferredSize(new Dimension(8 * plain.getWidth(), 8* plain.getHeight()));
		this.setVisible(true);
		
	}

	public int getImageWidth() {
		return plain.getWidth();
	}
	
	public int getImageHeight() {
		return plain.getHeight();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				g.drawImage(plain, j * plain.getWidth(), i * plain.getHeight(), null);
				
				if(array[i][j].isOccupied() == true) {
					array[i][j].getOccupant().draw(g, j * plain.getWidth(), i * plain.getHeight()); //has unit occupying tile draw itself 
					if(array[i][j].getOccupant().isTurn == true) {
						//draw symbol to show this unit is moving
						drawIcon(g, j, i);
					}
				}
				if(array[i][j].hasCursor == true) {
					drawCursor(g, j, i);
				}
			}
		}
	}
	
	public void drawCursor(Graphics g, int x, int y) {
		g.drawImage(cursor, x * cursor.getWidth(), y * cursor.getHeight(), null);
	}
	
	public void drawIcon(Graphics g, int x, int y) {
		g.drawImage(icon, x * icon.getWidth(), y * icon.getHeight(), null);
	}

}

