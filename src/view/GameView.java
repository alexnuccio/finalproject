package view;
import model.*;

import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JPanel;

/**
 * GameView
 * 
 * The GUI view for the game.
 * 
 * @author Alex Nuccio, Eric Myre, Angel Cornejo
 */
public class GameView extends JPanel {

	public static MapOne map;
	
	/**
	 * The constructor for the GameView.
	 * 
	 * @param map	the map that is being used in this game
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public GameView(MapOne map) {
		this.map = map;
		this.add(map);
		this.setPreferredSize(new Dimension(map.getNumCol() * map.getImageWidth(), map.getNumRow() * map.getImageHeight()));
		this.setVisible(true);
	}
	
	/**
	 * getMap
	 * 
	 * Returns the map
	 * 
	 * @return MapOne
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public static MapOne getMap() {
		return map;
	}

	

}
