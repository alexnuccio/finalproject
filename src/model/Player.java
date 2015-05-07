package model;

import java.util.*;
/**
 * Player
 * 
 * The Player class represents one of the two players in the game.
 * 
 * @author Alex Nuccio, Eric Myre, Angel Cornejo
 *
 */
public class Player {

	public List<Unit> units;
	public List<Item> items;
	
	public boolean turn;
	public boolean won;
	
	/**
	 * Player
	 * 
	 * Constructor.
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public Player() {
		units = new ArrayList<Unit>();
		items = new ArrayList<Item>();
		turn = false;
		won = false;
	}
	
	/**
	 * getTurn
	 * 
	 * A method to check if it is this player's turn.
	 * 
	 * @return boolean
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public boolean getTurn(){
		return turn;
	}
	
	/**
	 * setTurn
	 * 
	 * Sets the turn state of this Player
	 * 
	 * @param t	the turn state that this player is to be give
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public void setTurn(boolean t) {
		this.turn = t;
	}
	
	/**
	 * addUnit
	 * 
	 * Adds a unit to this player's list
	 * 
	 * @param u	a unit to be added
	 * 
	 * @return boolean
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public boolean addUnit(Unit u) {
		if(units.contains(u) == true) {
			return false;
		} else {
			units.add(u);
			return true;
		}
	}
	
	/**
	 * addItem
	 * 
	 * Adds an item to this player's list
	 * 
	 * @param i the item to be added
	 * 
	 * @return boolean
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public boolean addItem(Item i) {
		return items.add(i);
	}
	
	/**
	 * listUnits
	 * 
	 * A get method for a List all units the Player controls
	 * 
	 * @return List<Unit>
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public List<Unit> listUnits(){
		return this.units;
	}
	
	/**
	 * listItems
	 * 
	 * A get method for a List all items the Player has
	 * 
	 * @return List<Item>
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public List<Item> listItems(){
		return this.items;
	}
	/**
	 * hasWon
	 * 
	 * Performs a check to see if win conditions have been met.
	 * 
	 * @return boolean
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public boolean hasWon(){
		return this.won;
	}
	
	/**
	 * setHasWon
	 * 
	 * Sets whether a player has won or not
	 * 
	 * @param w	whether they have won 
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public void setHasWon(boolean w) {
		this.won = w;
	}
	/**
	 * layTrap
	 * 
	 * Lays a trap item.
	 * 
	 * @param T The tile that becomes a trap tile
	 * 
	 * @param P The trap to be used
	 * 
	 * @return boolean
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public boolean layTrap(Tile T, Trap P){
		return false;
	}
	/**
	 * useItem
	 * 
	 * Uses an item on the given tile
	 * 
	 * @param I The item to be used
	 * 
	 * @param T The tile where the item will be used
	 * 
	 * @return boolean
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public boolean useItem(Tile T, Item I){
		return false;
	}
}
