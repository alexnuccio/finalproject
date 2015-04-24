package view;
import model.*;

import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JPanel;

public class GameView extends JPanel {

	public static MapOne map;
	public GameView() {
		map = new MapOne();
		this.add(map);
		this.setPreferredSize(new Dimension(map.getNumCol() * map.getImageWidth(), map.getNumRow() * map.getImageHeight()));
		this.setVisible(true);
	}
	
	public MapOne getMap() {
		return map;
	}

	

}
