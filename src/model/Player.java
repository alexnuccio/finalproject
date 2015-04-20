package model;

import java.util.*;
/**
 * The Player class represents one of the two players in the game.
 * @author emyre
 *
 */
public class Player {
	/**
	 * A List containing all units the Player controls
	 */
	public List<Unit> units;
	/**
	 * A List containing all items the Player has
	 */
	public List<Item> items;
	
	public boolean turn;
	public boolean won;
	
	public Player() {
		units = new ArrayList<Unit>();
		items = new ArrayList<Item>();
		turn = false;
		won = false;
	}
	
	/**
	 * A method to check who's turn it is
	 * @return Whether or not it is this Player's turn
	 */
	public boolean getTurn(){
		return turn;
	}
	
	public void setTurn(boolean t) {
		this.turn = t;
	}
	
	public boolean addUnit(Unit u) {
		if(units.contains(u) == true) {
			return false;
		} else {
			units.add(u);
			return true;
		}
	}
	
	public boolean addItem(Item i) {
		return items.add(i);
	}
	/**
	 * A get method for a List all units the Player controlls
	 * @return A copy of the List containing all units the Player controlls
	 */
	public List<Unit> listUnits(){
		return this.units;
	}
	/**
	 * A get method for a List all items the Player has
	 * @return A copy of the List containing all items the Player has
	 */
	public List<Item> listItems(){
		return this.items;
	}
	/**
	 * Performs a check to see if win conditions have been met.
	 * @return Whether or not the Player has won.
	 */
	public boolean hasWon(){
		return this.won;
	}
	
	public void setHasWon(boolean w) {
		this.won = w;
	}
	/**
	 * @param T The tile that becomes a trap tile
	 * @param P The trap to be used
	 * Lays down a trap at the given location
	 * @return Whether or not the attempt to lay a trap succeeded or not
	 */
	public boolean layTrap(Tile T, Trap P){
		return false;
	}
	/**
	 * Uses an item on the given tile
	 * @param I The item to be used
	 * @param T The tile where the item will be used
	 * @return Whether or not the attempt to use an item succeeded or not
	 */
	public boolean useItem(Tile T, Item I){
		return false;
	}
}
