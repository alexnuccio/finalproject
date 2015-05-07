package model;

import java.io.Serializable;
import java.util.Observable;
import view.*;

/**
 * Model
 * 
 * The model class stores the map and both players.
 * 	
 * @author Alex Nuccio, Eric Myre, Angel Cornejo
 */
public class Model extends Observable implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private MapOne gameMap;
	private Player human;
	private Player computer;

	/**
	 * Model
	 * 
	 * Constructor for the model.
	 * 
	 * @param map The map of the game
	 * @param p1	The human player
	 * @param p2	The AI player
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public Model (MapOne map, Player p1, Player p2) {
		gameMap = map;
		human = p1;
		computer = p2;
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
	public MapOne getMap() {
		return gameMap;
	}
	
	/**
	 * getHuman
	 * 
	 * Returns the first player. The human controlled one.
	 * 
	 * @return Player
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public Player getHuman() {
		return human;
	}
	
	/**
	 * getComputer
	 * 
	 * Returns the second player. The AI controlled one.
	 * 
	 * @return Player
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public Player getComputer() {
		return computer;
	}
	
}
