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
	static long startTime, elapsedMinutes;
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
		System.out.print("What type of Units? Enter 1 for Barbarians only, 2 for Horses only, 3 for Spearman, or 4 for all: ");
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
		case 3:
			//create only spearman
			for(int i = 0; i < numUnits; i++) {
				Unit spear = new Spearman("spear" + i, player1);
			}
		default:
			//create all 3
			for(int i = 0; i < numUnits; i++) {
				if(i % 3 == 0) {
					Unit barb1 = new Barbarian("barb1" + i, player1);
				} else if(i % 3 == 1){
					Unit horse1 = new Horse("horse1" + i, player1);
				} else {
					Unit spear = new Spearman("spear1" + i, player1);
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
				Unit barbb = new Barbarian("barb10" + i, ai);
				barbb.setTeam(Team.AI);
			}
			break;
		case 2:
			//create only horses
			for(int i = 0; i < numUnits; i++) {
				Unit horsee = new Horse("horse10" + i, ai);
				horsee.setTeam(Team.AI);
			}
			break;
		default:
			//create both
			for(int i = 0; i < numUnits; i++) {
				if(i % 2 == 0) {
					Unit barb2 = new Barbarian("barb1000" + i, ai);
					barb2.setTeam(Team.AI);
				} else {
					Unit horse2 = new Horse("horse1000" + i, ai);
					horse2.setTeam(Team.AI);
				}
			}
			break;
		}
	
		//SPAWN ALL UNITS
		for(int i = 0; i < player1.listUnits().size(); i++) {
			player1.units.get(i).setPosition(6, i + 2, map);
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
		startTime = System.currentTimeMillis();
		elapsedMinutes = 0L;
		count = 0;
		Unit currUnit;
		int command;
		System.out.println();
		System.out.println("-------------------GAME IS STARTING-------------------");
		System.out.println("Beat the AI's team by killing all the opposing units. Take too long, YOU LOSE!");
		System.out.println();
		while(player1.hasWon() == false && ai.hasWon() == false) {
			while(checkIfGameIsOver() == false) {
				if(player1.listUnits().contains(myList.get(count % myList.size()))) {
					//players turn
					System.out.println("------------- Elapsed time in minutes: " + elapsedMinutes + ". Hurry up! " + (5 - elapsedMinutes) + " minutes left! -------------");
					int stop = player1.listUnits().size() + count;
					for(int i = count; i < stop; i++) {
						currUnit = (Unit) myList.get(i % myList.size());
						currUnit.isTurn = true;
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
							System.out.println("Who do you want to attack? Highlight them with the cursor & press 'ENTER'");
							while(Unit.currMove < currUnit.moveMultiplier){
								//exits automatically
								System.out.print("");
							}
							Unit.currMove = 0;
							isAttacking = false;
							isValid = false;
						}
						count++;
						currUnit.isTurn = false;
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
								succeeded = currUnit.move(Direction.LEFT, map);
								if(succeeded == false) {
									Unit.currMove++;
								}
								break;
							case 2:
								succeeded = currUnit.move(Direction.RIGHT, map);
								if(succeeded == false) {
									Unit.currMove++;
								}
								break;
							case 3:
								succeeded = currUnit.move(Direction.DOWN, map);
								if(succeeded == false) {
									Unit.currMove++;
								}
								break;
							default:
								succeeded = currUnit.move(Direction.UP, map);
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
		} else if(elapsedMinutes >= 5){
			System.out.println("YOU LOST. My grandma can move faster than you....");
		} else {
			System.out.println("YOU LOST. Come back next time with a better team and a better strategy....");
		}
	
		
		input.close(); //close Scanner
		//System.exit(0); //decide if we want window to close or stay open when game is over
	}
	
	public static Unit getCurrUnit() {
		return myList.get(count % myList.size()); 
	}
	
	private static boolean checkIfGameIsOver() {
		elapsedMinutes = (System.currentTimeMillis() - startTime);
		elapsedMinutes = elapsedMinutes / 1000;
		elapsedMinutes = elapsedMinutes / 60;
		if(player1.listUnits().isEmpty()) {
			ai.won = true;
			return true;
		} else if(ai.listUnits().isEmpty()) {
			player1.won = true;
			return true;
		} else if(elapsedMinutes >= 5) {
			ai.won = true;
			return true;
		}else {
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
			} else if(k.getKeyChar() == 'a') {
				curs.move(Direction.LEFT, map);
			} else if(k.getKeyChar() == 's') {
				curs.move(Direction.DOWN, map);
			} else  if(k.getKeyChar() == 'd'){
				curs.move(Direction.RIGHT, map);
			} else if (k.getKeyChar() == '\n' && isValid) {
				currUnit = getCurrUnit();
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
					return;
				}
				if(isAttacking) {
					Unit attacked = map.array[row][col].getOccupant();
					boolean didAttack = currUnit.attack(d, map);
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
