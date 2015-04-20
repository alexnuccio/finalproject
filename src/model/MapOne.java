package model;

import java.awt.*;

import javax.swing.JPanel;

public class MapOne extends JPanel {

	public Tile[][] array;
	
	public MapOne() {
		array = new Tile[10][10];
		this.setLayout(new GridLayout(10, 10));
		for(int i = 0; i < 10; i++) {
			
			for(int j = 0; j < 10; j++) {
				PlainTile newTile = new PlainTile();
				this.add(newTile);
				array[i][j] = newTile;
			}
		}
		this.setVisible(true);
		
	}

	public MapOne(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public MapOne(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public MapOne(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

}
