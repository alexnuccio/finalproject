package model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Strategy
 * 
 * Abstract strategy class to be extended by the weak and medium strategies.
 * 
 * @author Alex Nuccio, Eric Myre, Angel Cornejo
 *
 */
public abstract class Strategy {

	private Random ran;
	private Player computer;
	
	/**
	 * Constructor
	 * 
	 * @param ai the computer player that will implement this strategy
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public Strategy (Player ai) {
		computer = ai;
	}
	
	/**
	 * aiMove
	 * 
	 * Conducts the actions that make up the unit's turn
	 * 
	 * @param currUnit The unit who's turn it is
	 * 
	 * @param map	the map
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public abstract void aiMove(Unit currUnit, MapOne map); 
}
