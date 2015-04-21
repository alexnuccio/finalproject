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
	public BufferedImage plain, guy;
	
	public MapOne() {
		try {
			plain = ImageIO.read(new File("plain-test.png"));
			guy = ImageIO.read(new File("sample-guy.png"));
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
					g.drawImage(guy, j * plain.getWidth(), i * plain.getHeight(), null);
				}
			}
		}
	}

}