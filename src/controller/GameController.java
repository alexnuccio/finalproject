package controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeSet;

import javax.swing.*;

import model.*;
import model.Cursor;
import view.*;
/**
 * The controller
 * @author Alex Nuccio, Eric Myre, Angel Cornejo
 *
 */
public class GameController extends JFrame{
	
	static MapOne map;
	JPanel mainPanel;
	private TreeSet<Character> keySet;
	static Unit currUnit;
	static ArrayList<Unit> myList;
	static int count;
	Cursor curs;
	public static boolean isValid;
	/** 
	 * 	This is the constructor
	 */
	public GameController(){
		
		isValid = false;
		keySet = new TreeSet<Character>();
		mainPanel = new JPanel();
		
		GameView view = new GameView();
		
		map = view.getMap();
		curs = new Cursor();
		curs.setPosition(0, 0, map);

		mainPanel.add(view);
		this.addKeyListener(new keyListener());
		this.add(mainPanel);
		this.setVisible(true);
		this.pack();
	}
	/**
	 * The main method
	 * @param args
	 * Command-line arguments
	 */
	public static void main(String[] args){
		new GameController();
		isValid = false;
		
		Player player1 = new Player();
		Player ai = new Player();
		//create the units
		Unit barb1 = new Barbarian("Barb1", player1);
		Unit barb2 = new Barbarian("Barb2", player1);
		Unit barb3 = new Barbarian("Barb3", player1);
		Unit horse1 = new Horse("Horse1", player1);
		Unit horse2 = new Horse("Horse2", player1);
		
		Unit barb4 = new Barbarian("Barb4", ai);
		Unit barb5 = new Barbarian("Barb5", ai);
		Unit barb6 = new Barbarian("Barb6", ai);
		Unit horse3 = new Horse("Horse3", ai);
		Unit horse4 = new Horse("Horse4", ai);
		//spawn 
		for(int i = 0; i < player1.listUnits().size(); i++) {
			player1.listUnits().get(i).setPosition(7, i, map);
		}
		for(int i = 0; i < ai.listUnits().size(); i++) {
			ai.listUnits().get(i).setPosition(0, i, map);
		}
		map.repaint();
		
		myList = new ArrayList<Unit>();
		for(int i = 0; i < player1.listUnits().size(); i++) {
			myList.add(player1.listUnits().get(i));
		}
		for(int i = 0; i < ai.listUnits().size(); i++) {
			myList.add(ai.listUnits().get(i));
		}
		count = 0;
		Unit currUnit;
		Scanner input = new Scanner(System.in);
		int command;
		
		while(player1.hasWon() == false && ai.hasWon() == false) {
			while(myList.isEmpty() == false) {
				if(player1.listUnits().contains(myList.get(count % myList.size()))) {
					//players turn
					int stop = player1.listUnits().size() + count;
					for(int i = count; i < stop; i++) {
						currUnit = (Unit) myList.get(i % myList.size());
						System.out.print("It's your turn! Enter 1 to choose to MOVE or 2 to choose ATTACK:");
						command = input.nextInt();
						if(command == 1) {
							isValid = true;
							System.out.println("Your Unit can move " + currUnit.moveMultiplier + " squares. Use 'W', 'A', 'S', 'D' to move");
							while(Unit.currMove < currUnit.moveMultiplier) {
								//exits automatically
								System.out.print("");
							}
							Unit.currMove = 0;
							isValid = false;
						} else {
							//attack
							isValid = true;
							System.out.println("Who do you want to attack? Highlight them with cursor & press 'q'");
							while(Unit.currMove < currUnit.moveMultiplier){
								//exits automatically
								System.out.print("");
							}
							Unit.currMove = 0;
							isValid = false;
						}
						count++;
					}
				} else {
					//ai's turn
					int stop = ai.listUnits().size() + count;
					for(int i = count; i < stop; i++) {
						currUnit = (Unit) myList.get(i % myList.size());
						Random rand = new Random();
						while(Unit.currMove < currUnit.moveMultiplier) {
							//generate random moves
							int randNum = rand.nextInt((3 - 0) + 1) + 0;
							boolean succeeded = true;
							switch(randNum) {
							case 0:
								succeeded = currUnit.move(Direction.UP, map);
								if(succeeded == false) {
									Unit.currMove++;
								}
								break;
							case 1:
								currUnit.move(Direction.LEFT, map);
								if(succeeded == false) {
									Unit.currMove++;
								}
								break;
							case 2:
								currUnit.move(Direction.RIGHT, map);
								if(succeeded == false) {
									Unit.currMove++;
								}
								break;
							case 3:
								currUnit.move(Direction.DOWN, map);
								if(succeeded == false) {
									Unit.currMove++;
								}
								break;
							default:
								currUnit.move(Direction.UP, map);
								if(succeeded == false) {
									Unit.currMove++;
								}
							}
						} //end of while
						Unit.currMove = 0;
						count++;
					} //end of for
				} //end of if/else
				
			}//end of second inner while
		}//end of outer while
		
	
	}
	
	public Unit getCurrUnit() {
		return myList.get(count % myList.size()); 
	}
	
	
	
	
	private class keyListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent k) {
			
			keySet.add(k.getKeyChar());
			
			currUnit = getCurrUnit();
			if(k.getKeyChar() == 'w') {
				curs.move(Direction.UP, map);
				map.repaint();
			} else if(k.getKeyChar() == 'a') {
				curs.move(Direction.LEFT, map);
				map.repaint();
			} else if(k.getKeyChar() == 's') {
				curs.move(Direction.DOWN, map);
				map.repaint();
			} else  if(k.getKeyChar() == 'd'){
				curs.move(Direction.RIGHT, map);
				map.repaint();
			} else if (k.getKeyChar() == 'q' && isValid) {
				int currRow = currUnit.getRow(map);
				int currCol = currUnit.getCol(map);
				int row = curs.getRow();
				int col = curs.getCol();
				Direction d;
				if(currRow < row) {
					d = Direction.DOWN;
				} else if (currRow > row) {
					d = Direction.UP;
				} else if(currCol < col){
					d = Direction.RIGHT;
				} else if(currCol > col) {
					d = Direction.LEFT;
				} else {
					d = Direction.DOWN;
				}
				if(!map.array[row][col].isOccupied()) {
					currUnit.move(d, map);
					map.repaint();
					return;
				} else {
					Unit attacked = map.array[row][col].getOccupant();
					boolean didAttack = currUnit.attack(d, map);
					map.repaint();
					if(didAttack) {
						System.out.println("Completed attack on enemy! Did " + currUnit.attack + " damage");
						if(map.array[row][col].getOccupant() == null) {
							//if occupant of square just attacked is null, then unit was killed
							System.out.println("KILLED ENEMY: " + attacked.name);
							myList.remove((Unit) attacked); //remove unit from list
						}
					}
					Unit.currMove = currUnit.moveMultiplier;
				}
				
				
			} else {
				//do nothing
			}
		}
		
		@Override
		public void keyReleased(KeyEvent arg0) {keySet.remove(arg0.getKeyChar());}
	}

}
