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
	static Player player1;
	static Player ai;
	JPanel mainPanel;
	private TreeSet<Character> keySet;
	static Unit currUnit;
	static ArrayList<Unit> myList;
	static int count;
	Cursor curs;
	public static boolean isValid;
	public static boolean isAttacking;
	/** 
	 * 	This is the constructor
	 */
	public GameController(){
		
		isValid = false;
		isAttacking = false;
		keySet = new TreeSet<Character>();
		mainPanel = new JPanel();
		
		GameView view = new GameView();
		
		map = view.getMap();
		curs = new Cursor();
		curs.setPosition(0, 0, map);

		mainPanel.add(view);
		this.addKeyListener(new keyListener());
		this.add(mainPanel);
		setVisible(true);
		this.pack();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);//kill the application when the window is closed
	}
	/**
	 * The main method
	 * @param args
	 * Command-line arguments
	 */
	public static void main(String[] args){
		new GameController();
		isValid = false;
		isAttacking = false;
		
		Scanner input = new Scanner(System.in);
		player1 = new Player();
		ai = new Player();
		int numUnits, typeUnits;
		
		//CREATE PLAYER UNITS
		System.out.print("Enter the number of Units you want for your team: ");
		numUnits = input.nextInt();
		System.out.println();
		System.out.print("What type of Units? Enter 1 for Barbarians only, 2 for Horses only, or 3 for both: ");
		typeUnits = input.nextInt();
		switch(typeUnits) {
		case 1:
			//create only barbarians
			for(int i = 0; i < numUnits; i++) {
				Unit barb = new Barbarian("barb" + i, player1);
			}
			break;
		case 2:
			//create only horses
			for(int i = 0; i < numUnits; i++) {
				Unit horse = new Horse("horse" + i, player1);
			}
			break;
		default:
			//create both
			for(int i = 0; i < numUnits; i++) {
				if(i % 2 == 0) {
					Unit barb1 = new Barbarian("barb1" + i, player1);
				} else {
					Unit horse1 = new Horse("horse1" + i, player1);
				}
			}
		}
		
		//CREATE AI UNITS
		System.out.print("Enter the number of Units you want for the AI's team: ");
		numUnits = input.nextInt();
		System.out.println();
		System.out.print("What type of Units? Enter 1 for Barbarians only, 2 for Horses only, or 3 for both: ");
		typeUnits = input.nextInt();
		switch(typeUnits) {
		case 1:
			//create only barbarians
			for(int i = 0; i < numUnits; i++) {
				Unit barbb = new Barbarian("barb1" + i, ai);
			}
			break;
		case 2:
			//create only horses
			for(int i = 0; i < numUnits; i++) {
				Unit horsee = new Horse("horse1" + i, ai);
			}
			break;
		default:
			//create both
			for(int i = 0; i < numUnits; i++) {
				if(i % 2 == 0) {
					Unit barb2 = new Barbarian("barb1" + i, ai);
				} else {
					Unit horse2 = new Horse("horse1" + i, ai);
				}
			}
			break;
		}
	
		//SPAWN ALL UNITS
		for(int i = 0; i < player1.listUnits().size(); i++) {
			player1.units.get(i).setPosition(7, i + 2, map);
		}
		map.repaint();
		for(int i = 0; i < ai.listUnits().size(); i++) {
			ai.units.get(i).setPosition(0, i + 2, map);
		}
		map.repaint();
		
		myList = new ArrayList<Unit>(); // array list to hold all units (only living units)
		for(int i = 0; i < player1.listUnits().size(); i++) {
			myList.add(player1.listUnits().get(i));
		}
		for(int i = 0; i < ai.listUnits().size(); i++) {
			myList.add(ai.listUnits().get(i));
		}
		
		//RUN GAME
		count = 0;
		Unit currUnit;
		int command;
		while(player1.hasWon() == false && ai.hasWon() == false) {
			while(checkIfGameIsOver() == false) {
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
							isAttacking = true;
							System.out.println("Who do you want to attack? Highlight them with cursor & press 'q'");
							while(Unit.currMove < currUnit.moveMultiplier){
								//exits automatically
								System.out.print("");
							}
							Unit.currMove = 0;
							isAttacking = false;
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
		
		if(player1.hasWon()) {
			System.out.println("Congratulations! You beat the AI's team!");
		} else {
			System.out.println("Come back next time with a better team and a better strategy....");
		}
	
		
		input.close(); //close Scanner
		//System.exit(0); //decide if we want window to close or stay open when game is over
	}
	
	public Unit getCurrUnit() {
		return myList.get(count % myList.size()); 
	}
	
	private static boolean checkIfGameIsOver() {
		if(player1.listUnits().isEmpty()) {
			ai.won = true;
			return true;
		} else if(ai.listUnits().isEmpty()) {
			player1.won = true;
			return true;
		} else {
			return false;
		}
	}
	
	
	
	
	private class keyListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent k) {
			
			keySet.add(k.getKeyChar());
			int row, col;
			Direction d;
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
				row = curs.getRow();
				col = curs.getCol();
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
				}
				if(isAttacking) {
					Unit attacked = map.array[row][col].getOccupant();
					boolean didAttack = currUnit.attack(d, map);
					map.repaint();
					if(didAttack) {
						System.out.println("Completed attack on enemy! Did " + currUnit.attack + " damage");
						if(map.array[row][col].getOccupant() == null) {
							//if occupant of square just attacked is null, then unit was killed
							System.out.println("KILLED ENEMY: " + attacked.name);
							myList.remove((Unit) attacked); //remove unit from list
							//check whos team unit was on
							if(player1.listUnits().contains((Unit) attacked)) {
								//killed unit was on player's team
								player1.units.remove((Unit) attacked);
							} else {
								ai.units.remove((Unit) attacked);
							}
						}
					}
					Unit.currMove = currUnit.moveMultiplier;
				}
				Unit.currMove++;
				
			} else {
				//do nothing
				
			}
		}
		
		@Override
		public void keyReleased(KeyEvent arg0) {keySet.remove(arg0.getKeyChar());}
	}

}
