package model;

import java.io.Serializable;
import java.util.Observable;
import view.*;

/**
 * The model class stores:
 * 	
 * 
 */
public class Model extends Observable implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private MapOne gameMap;
	private Player human;
	private Player computer;

	public Model (MapOne map, Player p1, Player p2) {
		gameMap = map;
		human = p1;
		computer = p2;
	}
	
	public MapOne getMap() {
		return gameMap;
	}
	
	public Player getHuman() {
		return human;
	}
	
	public Player getComputer() {
		return computer;
	}
	
}
