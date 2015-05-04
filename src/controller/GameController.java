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
	public static boolean isAttacking, isMoving;
	static long startTime, elapsedMinutes;
	static JComboBox<String> box1, box2, box3, box4, box5, box6, box7, box8, box9, box10, mapBox;
	static JButton createButton;
	public static boolean gameIsSetup;
	static JFrame firstFrame;
	/** 
	 * 	This is the constructor
	 */
	public GameController(Player player1, Player ai, MapOne m){
		
		firstFrame.setVisible(false);
		isValid = false;
		isAttacking = false;
		isMoving = false;
		keySet = new TreeSet<Character>();
		mainPanel = new JPanel();
		
		GameView view = new GameView(m);
		
		map = m;
		curs = new Cursor();
		curs.setPosition(0, 0, map);

		this.player1 = player1;
		this.ai = ai;
		
		//SPAWN ALL UNITS
		for(int i = 0; i < player1.listUnits().size(); i++) {
			player1.units.get(i).setPosition(6, i + 2, map);
		}
		map.repaint();
		for(int i = 0; i < ai.listUnits().size(); i++) {
			ai.units.get(i).setPosition(0, i + 2, map);
		}
		map.repaint();
		
		//SPAWN ALL ITEMS
		Item health = new HealthPotion();
		Item speed = new SpeedShoes();
		Item strength = new StrengthPotion();
		health.setPosition(3, 1, m);
		speed.setPosition(3, 2, m);
		strength.setPosition(3, 3, m);
		
		myList = new ArrayList<Unit>(); // array list to hold all units (only living units)
		for(int i = 0; i < player1.listUnits().size(); i++) {
			myList.add(player1.listUnits().get(i));
		}
		for(int i = 0; i < ai.listUnits().size(); i++) {
			myList.add(ai.listUnits().get(i));
		}
		
		
		mainPanel.add(view);
		this.addKeyListener(new keyListener());
		this.add(mainPanel);
		this.setVisible(true);
		this.pack();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);//kill the application when the window is closed
		
		Thread myThread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				runGame(); //run method in seperate thread
			}
			
		});
		
		myThread.start(); //runs the game by starting the thread that runs it
		
		
		
	}
	
	public void runGame() {
		
		map.repaint();
		startTime = System.currentTimeMillis();
		elapsedMinutes = 0L;
		count = 0;
		Unit currUnit;
		while(player1.hasWon() == false && ai.hasWon() == false) {
			while(checkIfGameIsOver() == false) {
				if(player1.listUnits().contains(myList.get(count % myList.size()))) {
					//players turn
					int stop = player1.listUnits().size() + count;
					for(int i = count; i < stop; i++) {
						currUnit = (Unit) myList.get(i % myList.size());
						currUnit.isTurn = true;
						map.repaint();
						JOptionPane.showMessageDialog(null, "Your turn! Move to an empty tile, attack an enemy in an\n adjacent tile, press 'i' to use item, or\n press 'n' to do nothing.");
						isValid = true;
						isMoving = false;
						while(Unit.currMove < currUnit.moveMultiplier) {
							//exits automatically
							System.out.print("");
						}
						Unit.currMove = 0;
						isValid = false;
						count++;
						currUnit.isTurn = false;
						map.repaint();
					}
				
				} else {
					//ai's turn
					int stop = ai.listUnits().size() + count;
					for(int i = count; i < stop; i++) {
						currUnit = (Unit) myList.get(i % myList.size());
						//generate move for ai's currUnit
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
						
						
					}
					
				}
			} //end of second outer while
		} //end of outer while
		
		
		if(player1.hasWon()) {
			JOptionPane.showMessageDialog(null, "Congratulations! You beat the AI's team!");
		} else if(elapsedMinutes >= 5){
			JOptionPane.showMessageDialog(null, "YOU LOST. My grandma can move faster than you....");
		} else {
			JOptionPane.showMessageDialog(null, "YOU LOST. Come back next time with a better team and a better strategy....");
		}
	} //end of runGame()
	
	/**
	 * The main method
	 * @param args
	 * Command-line arguments
	 */
	public static void main(String[] args){
		
		player1 = new Player();
		ai = new Player();
		firstFrame = new JFrame("Barbarians");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		firstFrame.add(mainPanel);
		JPanel playerPanel = new JPanel();
		playerPanel.setLayout(new GridLayout(1, 6));
		JPanel aiPanel = new JPanel();
		aiPanel.setLayout(new GridLayout(1, 6));
		
		JLabel playerLabel = new JLabel("Pick your units:");
		String[] unitsPossible = { "", "Barbarian", "Horse", "Mage", "Spearman", "Archer" };
		box1 = new JComboBox<String>(unitsPossible);
		box2 = new JComboBox<String>(unitsPossible);
		box3 = new JComboBox<String>(unitsPossible);
		box4 = new JComboBox<String>(unitsPossible);
		box5 = new JComboBox<String>(unitsPossible);
		playerPanel.add(playerLabel);
		playerPanel.add(box1);
		playerPanel.add(box2);
		playerPanel.add(box3);
		playerPanel.add(box4);
		playerPanel.add(box5);
		
		JLabel aiLabel = new JLabel("Pick the AI's units:");
		box6 = new JComboBox<String>(unitsPossible);
		box7 = new JComboBox<String>(unitsPossible);
		box8 = new JComboBox<String>(unitsPossible);
		box9 = new JComboBox<String>(unitsPossible);
		box10 = new JComboBox<String>(unitsPossible);
		aiPanel.add(aiLabel);
		aiPanel.add(box6);
		aiPanel.add(box7);
		aiPanel.add(box8);
		aiPanel.add(box9);
		aiPanel.add(box10);
		
		JPanel mapPanel = new JPanel();
		mapPanel.setLayout(new BoxLayout(mapPanel, BoxLayout.Y_AXIS));
		JLabel mapLabel = new JLabel("Pick the map:");
		String[] maps = { "Map 1", "Map 2" };
		mapBox = new JComboBox<String>(maps);
		createButton = new JButton("SETUP GAME");
		createButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(box1.getSelectedIndex() == 0) {
					// don't create anything
				} else if(box1.getSelectedIndex() == 1) {
					Unit barb = new Barbarian("barb1", player1);
				} else if(box1.getSelectedIndex() == 2) {
					Unit horse = new Horse("horse1", player1);
				} else if(box1.getSelectedIndex() == 3) {
					Unit mage = new Mage("mage1", player1);
				} else if(box1.getSelectedIndex() == 4){
					Unit spear = new Spearman("spear1", player1);
				} else {
					Unit arch = new Archer("arch1", player1);
				}
				if(box2.getSelectedIndex() == 0) {
					// don't create anything
				} else if(box2.getSelectedIndex() == 1) {
					Unit barb1 = new Barbarian("barb11", player1);
				} else if(box2.getSelectedIndex() == 2) {
					Unit horse1 = new Horse("horse11", player1);
				} else if(box2.getSelectedIndex() == 3) {
					Unit mage1 = new Mage("mage11", player1);
				} else if(box2.getSelectedIndex() == 4){
					Unit spear1 = new Spearman("spear11", player1);
				} else {
					Unit arch1 = new Archer("arch11", player1);
				}
				if(box3.getSelectedIndex() == 0) {
					// don't create anything
				} else if(box3.getSelectedIndex() == 1) {
					Unit barb2 = new Barbarian("barb12", player1);
				} else if(box3.getSelectedIndex() == 2) {
					Unit horse2 = new Horse("horse12", player1);
				} else if(box3.getSelectedIndex() == 3) {
					Unit mage2 = new Mage("mage12", player1);
				} else if(box3.getSelectedIndex() == 4){
					Unit spear2 = new Spearman("spear12", player1);
				} else {
					Unit arch2 = new Archer("arch12", player1);
				}
				if(box4.getSelectedIndex() == 0) {
					// don't create anything
				} else if(box4.getSelectedIndex() == 1) {
					Unit barb3 = new Barbarian("barb13", player1);
				} else if(box4.getSelectedIndex() == 2) {
					Unit horse3 = new Horse("horse13", player1);
				} else if(box4.getSelectedIndex() == 3) {
					Unit mage3 = new Mage("mage13", player1);
				} else if(box4.getSelectedIndex() == 4){
					Unit spear3 = new Spearman("spear13", player1);
				} else {
					Unit arch3 = new Archer("arch13", player1);
				}
				if(box5.getSelectedIndex() == 0) {
					// don't create anything
				} else if(box5.getSelectedIndex() == 1) {
					Unit barb4 = new Barbarian("barb14", player1);
				} else if(box5.getSelectedIndex() == 2) {
					Unit horse4 = new Horse("horse14", player1);
				} else if(box5.getSelectedIndex() == 3) {
					Unit mage4 = new Mage("mage14", player1);
				} else if(box5.getSelectedIndex() == 4){
					Unit spear4 = new Spearman("spear14", player1);
				} else {
					Unit arch4 = new Archer("arch14", player1);
				}
				
				//ai's units
				if(box6.getSelectedIndex() == 0) {
					// don't create anything
				} else if(box6.getSelectedIndex() == 1) {
					Unit barb5 = new Barbarian("barb15", ai);
				} else if(box6.getSelectedIndex() == 2) {
					Unit horse5 = new Horse("horse15", ai);
				} else if(box6.getSelectedIndex() == 3) {
					Unit mage5 = new Mage("mage15", ai);
				} else if(box6.getSelectedIndex() == 4){
					Unit spear5 = new Spearman("spear15", ai);
				} else {
					Unit arch5 = new Archer("arch15", ai);
				}
				if(box7.getSelectedIndex() == 0) {
					// don't create anything
				} else if(box7.getSelectedIndex() == 1) {
					Unit barb51 = new Barbarian("barb151", ai);
				} else if(box7.getSelectedIndex() == 2) {
					Unit horse51 = new Horse("horse151", ai);
				} else if(box7.getSelectedIndex() == 3) {
					Unit mage51 = new Mage("mage151", ai);
				} else if(box7.getSelectedIndex() == 4){
					Unit spear51 = new Spearman("spear151", ai);
				} else {
					Unit arch51 = new Archer("arch151", ai);
				}
				if(box8.getSelectedIndex() == 0) {
					// don't create anything
				} else if(box8.getSelectedIndex() == 1) {
					Unit barb52 = new Barbarian("barb152", ai);
				} else if(box8.getSelectedIndex() == 2) {
					Unit horse52 = new Horse("horse152", ai);
				} else if(box8.getSelectedIndex() == 3) {
					Unit mage25 = new Mage("mage152", ai);
				} else if(box8.getSelectedIndex() == 4){
					Unit spear52 = new Spearman("spear152", ai);
				} else {
					Unit arch52 = new Archer("arch152", ai);
				}
				if(box9.getSelectedIndex() == 0) {
					// don't create anything
				} else if(box9.getSelectedIndex() == 1) {
					Unit barb53 = new Barbarian("barb153", ai);
				} else if(box9.getSelectedIndex() == 2) {
					Unit horse53 = new Horse("horse153", ai);
				} else if(box9.getSelectedIndex() == 3) {
					Unit mage53 = new Mage("mage153", ai);
				} else if(box9.getSelectedIndex() == 4){
					Unit spear53 = new Spearman("spear153", ai);
				} else {
					Unit arch53 = new Archer("arch153", ai);
				}
				if(box10.getSelectedIndex() == 0) {
					// don't create anything
				} else if(box10.getSelectedIndex() == 1) {
					Unit barb54 = new Barbarian("barb154", ai);
				} else if(box10.getSelectedIndex() == 2) {
					Unit horse54 = new Horse("horse154", ai);
				} else if(box10.getSelectedIndex() == 3) {
					Unit mage54 = new Mage("mage154", ai);
				} else if(box10.getSelectedIndex() == 4){
					Unit spear54 = new Spearman("spear154", ai);
				} else {
					Unit arch54 = new Archer("arch154", ai);
				}
				//set teams
				for(int i = 0; i < player1.listUnits().size(); i++) {
					player1.listUnits().get(i).setTeam(Team.USER);
				}
				for(int i = 0; i < ai.listUnits().size(); i++) {
					ai.listUnits().get(i).setTeam(Team.AI);
				}
				if(mapBox.getSelectedIndex() == 0) {
					map = new MapOne();
				} else {
					map = new MapTwo();
				}
				gameIsSetup = true;
				
				new GameController(player1, ai, map);
			}
			
		});
		mapPanel.add(mapLabel);
		mapPanel.add(mapBox);
		mapPanel.add(createButton);
		
		mainPanel.add(playerPanel, BorderLayout.NORTH);
		mainPanel.add(aiPanel, BorderLayout.SOUTH);
		mainPanel.add(mapPanel, BorderLayout.EAST);
		firstFrame.setVisible(true);
		firstFrame.pack();
		
	
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
			} else if(k.getKeyChar() == 'n' && isValid) {
				//do nothing
				Unit.currMove = currUnit.moveMultiplier;
			} else if (k.getKeyChar() == 'i' && isValid) {
				//use item
				if(player1.listItems().isEmpty() == true) {
					JOptionPane.showMessageDialog(null, "You have no items! Try something else:");
					return;
				} else {
					String command = JOptionPane.showInputDialog(null, "Select an item to use! Enter 1 for health potion,\n 2 for strength potion, or 3\n for speed shoes: ");
					if(command.equals("1")) {
						if(player1.listItems().contains(new HealthPotion()) == false) {
							JOptionPane.showMessageDialog(null, "You don't have any health potions, try something else: ");
							return;
						}
						currUnit = getCurrUnit();
						currUnit.useItem(new HealthPotion());
						Unit.currMove = currUnit.moveMultiplier;
					} else if(command.equals("2")) {
						if(player1.listItems().contains(new StrengthPotion()) == false) {
							JOptionPane.showMessageDialog(null, "You don't have any strength potions, try something else: ");
							return;
						}
						currUnit = getCurrUnit();
						currUnit.useItem(new StrengthPotion());
						Unit.currMove = currUnit.moveMultiplier;
					} else {
						if(player1.listItems().contains(new SpeedShoes()) == false) {
							JOptionPane.showMessageDialog(null, "You don't have any speed shoes, try something else: ");
							return;
						}
						currUnit = getCurrUnit();
						currUnit.useItem(new SpeedShoes());
						Unit.currMove = currUnit.moveMultiplier;
					}
				}
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
					isAttacking = false;
					isMoving = true;
					return;
				} else {
					if(isMoving == true) {
						//cant move there
						JOptionPane.showMessageDialog(GameController.this, "CAN'T MOVE THERE");
					} else {
						Unit attacked = map.array[row][col].getOccupant();
						boolean didAttack = currUnit.attack(d, map);
						if(didAttack) {
							JOptionPane.showMessageDialog(null, "Completed attack on enemy! Did " + currUnit.attack + " damage");
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
						} else {
							JOptionPane.showMessageDialog(null, "INVALID ATTACK, RETRY: ");
							return;
						}
						Unit.currMove = currUnit.moveMultiplier;
					}
				}
				
			} else {
				//do nothing
				
			}
		}
		
		@Override
		public void keyReleased(KeyEvent arg0) {keySet.remove(arg0.getKeyChar());}
	}

}
