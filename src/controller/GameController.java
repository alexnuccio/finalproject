package controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeSet;

import javax.swing.*;


import model.*;
import model.Cursor;
import view.*;

/**
 * The controller
 * 
 * @author Alex Nuccio, Eric Myre, Angel Cornejo
 * 
 */
public class GameController extends JFrame implements Serializable {

	static MapOne map;
	static Player player1;
	static Player ai;
	static JPanel mainPanel;
	private static TreeSet<Character> keySet;
	static Unit currUnit;
	public static ArrayList<Unit> myList;
	static int count;
	static Cursor curs;
	public static boolean isValid;
	public static boolean isAttacking, isMoving;
	static long startTime, elapsedMinutes;
	static JComboBox<String> box1, box2, box3, box4, box5, box6, box7, box8,
			box9, box10, mapBox;
	static JButton createButton;
	public static boolean gameIsSetup;
	static JFrame firstFrame;

	private static final long serialVersionUID = 1L;
	public static Model model;

	/**
	 * This is the constructor
	 */
	public GameController(Player player1, Player ai, MapOne m,
			final int gameMode) {

		if (firstFrame != null) {
			firstFrame.setVisible(false);
		}
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

		mainPanel.add(view);
		this.addKeyListener(new keyListener());
		this.add(mainPanel);

		// add menu bar
		JMenuBar menu = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenu stats = new JMenu("Stats");
		JMenuItem displayStats = new JMenuItem("Display Stats");
		displayStats.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//CREATE JFRAME TO DISPLAY STATS
				JFrame statsFrame = new JFrame("Stats");
				JPanel statsPanel = new JPanel();
				statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
				statsPanel.setPreferredSize(new Dimension(450, 200));
				statsFrame.add(statsPanel);
				Unit current = getCurrUnit();
				ArrayList<Unit> temp = myList;
				JLabel label = new JLabel();
				label.setText("Units organized by turn: ");
				statsPanel.add(label);
				for(int i = 0; i < temp.size(); i++) {
					JLabel unit1 = new JLabel();
					unit1.setText("Unit: " + temp.get(i).name + " -- HP: " + temp.get(i).hitpoints + "/" + temp.get(i).maxhp + " -- Attack: " + temp.get(i).attack);
					statsPanel.add(unit1);
					if(temp.get(i).getTeam() == Team.AI) {
						unit1.setText(unit1.getText() + " -- Team: AI");
					} else {
						unit1.setText(unit1.getText() + " -- Team: USER");
					}
				}
				
				statsFrame.setVisible(true);
				statsFrame.pack();
			}
		});
		JMenu inst = new JMenu("Instructions");
		JMenuItem game1 = new JMenuItem("Main Game");
		game1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame("Instructions");
				JPanel panel = new JPanel();
				panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
				panel.setPreferredSize(new Dimension(500, 500));
				frame.add(panel);
				JLabel label = new JLabel();
				label.setText("Instructions:");
				panel.add(label);
				JTextArea label1 = new JTextArea();
				label1.setText("Win Conditions: Kill all the enemies.\nLose Conditions: Take longer than 5 minutes, or have all your units killed.\nInstructions: Move your units around the map, collecting items, laying traps, and attacking enemies.");
				label1.setEditable(false);
				label1.setLineWrap(true);
				panel.add(label1);
				frame.setVisible(true);
				frame.pack();
			}
		});
		JMenuItem game2 = new JMenuItem("Item Collector");
		game2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame("Instructions");
				JPanel panel = new JPanel();
				panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
				panel.setPreferredSize(new Dimension(500, 500));
				frame.add(panel);
				JLabel label = new JLabel();
				label.setText("Instructions:");
				panel.add(label);
				JTextArea label1 = new JTextArea();
				label1.setText("Win Conditions: Collect more items than the AI, or kill all enemy units.\nLose Conditions: The AI picks up more items than your team.\nInstructions: Move your units around the map trying to pick up as many items as you possibly can. You can also attack the enemy if you choose.");
				label1.setEditable(false);
				label1.setLineWrap(true);
				panel.add(label1);
				frame.setVisible(true);
				frame.pack();
			}
		});
		JMenuItem game3 = new JMenuItem("Survival");
		game3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame("Instructions");
				JPanel panel = new JPanel();
				panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
				panel.setPreferredSize(new Dimension(500, 500));
				frame.add(panel);
				JLabel label = new JLabel();
				label.setText("Instructions:");
				panel.add(label);
				JTextArea label1 = new JTextArea();
				label1.setText("Win Conditions: Survive for 10 rounds, or kill all enemy units.\nLose Conditions: Your unit dies before the 10th round.\nInstructions: SURVIVE. Use your health potions to restore health if too low.");
				label1.setEditable(false);
				label1.setLineWrap(true);
				panel.add(label1);
				frame.setVisible(true);
				frame.pack();
			}
		});
		JMenuItem save = new JMenuItem("Save");
		JMenuItem exit = new JMenuItem("Exit");
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		file.add(save);
		file.add(exit);
		stats.add(displayStats);
		inst.add(game1);
		inst.add(game2);
		inst.add(game3);
		menu.add(file);
		menu.add(stats);
		menu.add(inst);
		this.setJMenuBar(menu);
		this.setVisible(true);
		this.pack();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);// kill the application
														// when the window is
														// closed

		// DESIGN PATTERN
		Thread myThread = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (gameMode == 1) {
					runGame(); // run method in seperate thread
				} else if (gameMode == 2) {
					runItemCollector();
				} else {
					runSurvival();
				}
			}
		});

		myThread.start(); // runs the game by starting the thread that runs it

	}

	public void runGame() {
		Strategy strat = new MediumStrategy(ai);
		// SPAWN ALL UNITS
		for (int i = 0; i < player1.listUnits().size(); i++) {
			player1.units.get(i).setPosition(6, i + 2, map);
		}
		map.repaint();
		for (int i = 0; i < ai.listUnits().size(); i++) {
			ai.units.get(i).setPosition(0, i + 2, map);
		}
		map.repaint();

		myList = new ArrayList<Unit>(); // array list to hold all units (only
										// living units)
		for (int i = 0; i < player1.listUnits().size(); i++) {
			myList.add(player1.listUnits().get(i));
		}
		for (int i = 0; i < ai.listUnits().size(); i++) {
			myList.add(ai.listUnits().get(i));
		}

		// SPAWN ALL ITEMS
		Item health = new HealthPotion();
		Item speed = new SpeedShoes();
		Item strength = new StrengthPotion();
		health.setPosition(3, 1, map);
		speed.setPosition(3, 2, map);
		strength.setPosition(3, 3, map);
		map.repaint();

		startTime = System.currentTimeMillis();
		elapsedMinutes = 0L;
		count = 0;
		Unit currUnit;
		while (player1.hasWon() == false && ai.hasWon() == false) {
			while (checkIfGameIsOver() == false) {
				if (player1.listUnits().contains(
						myList.get(count % myList.size()))) {
					// players turn
					int stop = player1.listUnits().size() + count;
					for (int i = count; i < stop; i++) {
						currUnit = (Unit) myList.get(i % myList.size());
						currUnit.isTurn = true;
						map.repaint();
						JOptionPane
								.showMessageDialog(
										null,
										"Your turn! Move to an empty tile, attack an enemy in an\n adjacent tile, press 'i' to use item, or\n press 'n' to do nothing.");
						isValid = true;
						isMoving = false;
						while (Unit.currMove < currUnit.moveMultiplier) {
							// exits automatically
							System.out.print("");
						}
						Unit.currMove = 0;
						isValid = false;
						count++;
						currUnit.isTurn = false;
						map.repaint();
					}

				} else {
					// ai's turn
					int stop = ai.listUnits().size() + count;
					for (int i = count; i < stop; i++) {
						currUnit = (Unit) myList.get(i % myList.size());
						strat.aiMove(currUnit, map);
						map.repaint();
					}
					Unit.currMove = 0;
					count++;
				}
			} // end of second outer while
		} // end of outer while

		if (player1.hasWon()) {
			JOptionPane.showMessageDialog(null,
					"Congratulations! You beat the AI's team!");
		} else if (elapsedMinutes >= 5) {
			JOptionPane.showMessageDialog(null,
					"YOU LOST. My grandma can move faster than you....");
		} else {
			JOptionPane
					.showMessageDialog(null,
							"YOU LOST. Come back next time with a better team and a better strategy....");
		}
	} // end of runGame()

	public static void runItemCollector() {
		Strategy strat = new WeakStrategy(ai);
		// spawn items on every tile
		for (int i = 1; i < map.getNumRow(); i++) {
			for (int j = 0; j < map.getNumCol(); j++) {
				if (i == map.getNumRow() - 1) {
					// don't spawn items on this row
					continue;
				} else {
					map.array[i][j].setItem(new HealthPotion());
				}
			}
		}

		player1 = new Player();
		ai = new Player();
		Unit yourHorse = new Horse("user horse", player1);
		Unit aiHorse = new Horse("ai's horse", ai);
		yourHorse.setPosition(6, 4, map);
		aiHorse.setPosition(0, 4, map);
		// set teams
		yourHorse.setTeam(Team.USER);
		aiHorse.setTeam(Team.AI);

		myList = new ArrayList<Unit>(); // array list to hold all units (only
										// living units)
		for (int i = 0; i < player1.listUnits().size(); i++) {
			myList.add(player1.listUnits().get(i));
		}
		for (int i = 0; i < ai.listUnits().size(); i++) {
			myList.add(ai.listUnits().get(i));
		}

		count = 0;
		Unit currUnit;
		while (checkIfGameIsOver2() == false) {
			if (player1.listUnits().contains(myList.get(count % myList.size()))) {
				// players turn
				int stop = player1.listUnits().size() + count;
				for (int i = count; i < stop; i++) {
					currUnit = (Unit) myList.get(i % myList.size());
					currUnit.isTurn = true;
					map.repaint();
					JOptionPane
							.showMessageDialog(null,
									"Your turn! Move around and collect the most items!");
					isValid = true;
					isMoving = false;
					while (Unit.currMove < currUnit.moveMultiplier) {
						// exits automatically
						System.out.print("");
					}
					Unit.currMove = 0;
					isValid = false;
					count++;
					currUnit.isTurn = false;
					map.repaint();
				}
			} else {
				// ai's turn
				int stop = ai.listUnits().size() + count;
				for (int i = count; i < stop; i++) {
					currUnit = (Unit) myList.get(i % myList.size());
					// generate move for ai's currUnit
					
					strat.aiMove(currUnit, map);
					map.repaint();
				}
				Unit.currMove = 0;
				count++;
			}
		}

		if (player1.listItems().size() > ai.listItems().size()) {
			JOptionPane
					.showMessageDialog(null,
							"CONGRATS! YOU WIN!\n You picked up more items than the AI!");
		} else if (player1.listItems().size() < ai.listItems().size()) {
			JOptionPane
					.showMessageDialog(
							null,
							"YOU LOSE! Come on, your horse can move faster than that!\n The AI picked up more items that you!");
		} else {
			JOptionPane.showMessageDialog(null, "YOU TIED THE AI!");
		}
	}

	public static void runSurvival() {
		Strategy strat = new MediumStrategy(ai);
		player1 = new Player();
		ai = new Player();
		// create players unit
		Unit mage = new Mage("mage", player1);
		mage.setTeam(Team.USER);
		mage.attack = mage.attack + 20; // make unit extra powerful
		mage.setPosition(6, 4, map);
		// create ai units
		Unit barb = new Barbarian("barb", ai);
		Unit barb1 = new Barbarian("barb1", ai);
		Unit barb2 = new Barbarian("barb2", ai);
		Unit barb3 = new Barbarian("barb3", ai);
		for (int i = 0; i < ai.listUnits().size(); i++) {
			ai.listUnits().get(i).setTeam(Team.AI);
			ai.listUnits().get(i).setPosition(0, i + 2, map);
		}
		// give user health potions (3)
		player1.addItem(new HealthPotion());
		player1.addItem(new HealthPotion());
		player1.addItem(new HealthPotion());

		myList = new ArrayList<Unit>(); // array list to hold all units (only
		// living units)
		for (int i = 0; i < player1.listUnits().size(); i++) {
			myList.add(player1.listUnits().get(i));
		}
		for (int i = 0; i < ai.listUnits().size(); i++) {
			myList.add(ai.listUnits().get(i));
		}
		map.repaint();
		
		int turns = 0;
		while(checkIfGameIsOver3(turns) == false) {
			if (player1.listUnits().contains(myList.get(count % myList.size()))) {
				//players turn
				int stop = player1.listUnits().size() + count;
				for (int i = count; i < stop; i++) {
					currUnit = (Unit) myList.get(i % myList.size());
					currUnit.isTurn = true;
					map.repaint();
					JOptionPane.showMessageDialog(null, "Your turn!\n Survive for " + (10 - turns) + " more rounds, or kill all enemies to win!");
					isValid = true;
					isMoving = false;
					while (Unit.currMove < currUnit.moveMultiplier) {
						// exits automatically
						System.out.print("");
					}
					Unit.currMove = 0;
					isValid = false;
					count++;
					currUnit.isTurn = false;
					map.repaint();
					
				}
				turns++;
				
			} else {
				//ai's turn
				int stop = ai.listUnits().size() + count;
				for (int i = count; i < stop; i++) {
					currUnit = (Unit) myList.get(i % myList.size());
					strat.aiMove(currUnit, map);
					map.repaint();
				}
				Unit.currMove = 0;
				count++;
					
			}	
		}
		
		if(player1.hasWon() == true) {
			JOptionPane.showMessageDialog(null, "CONGRATS! You survived!");
		} else {
			JOptionPane.showMessageDialog(null, "YOU LOSE! Come on, the name of\n the game is SURVIVAL, so, next time, SURVIVE!");
		}
	}

	/**
	 * The main method
	 * 
	 * @param args
	 *            Command-line arguments
	 */
	public static void main(String[] args) {

		boolean noGameFlag = false;

		String loadFileName = JOptionPane
				.showInputDialog("Enter the name of the file to load a saved game from. "
						+ "If you would like to start a new game, leave this blank.");

		if (loadFileName != null && !loadFileName.equals("")) {
			ObjectInputStream loadStream = null;

			try {
				loadStream = new ObjectInputStream(new FileInputStream(
						loadFileName));
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null,
						"There is no file by that name. Starting new game.");
				noGameFlag = true;
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Bad file.");
				noGameFlag = true;
			}

			GameController loadedController = null;

			try {
				if (loadStream != null)
					loadedController = (GameController) loadStream.readObject();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null,
						"The class's ID has changed.");
				noGameFlag = true;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (ClassCastException cce) {
				JOptionPane
						.showMessageDialog(null,
								"That file does not contain a saved Barbarian game. Starting new game.");
				noGameFlag = true;
			}

			if (loadStream != null) {
				try {
					loadStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (loadedController != null) {
				model = loadedController.model;
				new GameController(loadedController.player1,
						loadedController.ai, loadedController.map, 1);

			}
		} else {
			noGameFlag = true;
		}

		// goes here if setting up new game

		String command = JOptionPane
				.showInputDialog(
						null,
						"Pick whick game mode to play!\n Enter 1 for the main game.\n Enter 2 for Item Collection.\n Enter 3 for Survival.");
		if (command.equals("2")) {
			map = new MapOne();
			new GameController(null, null, map, 2);
			return;
		} else if (command.equals("3")) {
			map = new MapOne();
			new GameController(null, null, map, 3);
			return;
		}
		// IF IT GETS HERE, THE USER IS PLAYING DEFAULT GAME

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
		String[] unitsPossible = { "", "Barbarian", "Horse", "Mage",
				"Spearman", "Archer" };
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
		String[] maps = { "Map 1", "Map 2" , "Map 3"};
		mapBox = new JComboBox<String>(maps);
		createButton = new JButton("SETUP GAME");
		createButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (box1.getSelectedIndex() == 0) {
					// don't create anything
				} else if (box1.getSelectedIndex() == 1) {
					Unit barb = new Barbarian("barb1", player1);
				} else if (box1.getSelectedIndex() == 2) {
					Unit horse = new Horse("horse1", player1);
				} else if (box1.getSelectedIndex() == 3) {
					Unit mage = new Mage("mage1", player1);
				} else if (box1.getSelectedIndex() == 4) {
					Unit spear = new Spearman("spear1", player1);
				} else {
					Unit arch = new Archer("arch1", player1);
				}
				if (box2.getSelectedIndex() == 0) {
					// don't create anything
				} else if (box2.getSelectedIndex() == 1) {
					Unit barb1 = new Barbarian("barb11", player1);
				} else if (box2.getSelectedIndex() == 2) {
					Unit horse1 = new Horse("horse11", player1);
				} else if (box2.getSelectedIndex() == 3) {
					Unit mage1 = new Mage("mage11", player1);
				} else if (box2.getSelectedIndex() == 4) {
					Unit spear1 = new Spearman("spear11", player1);
				} else {
					Unit arch1 = new Archer("arch11", player1);
				}
				if (box3.getSelectedIndex() == 0) {
					// don't create anything
				} else if (box3.getSelectedIndex() == 1) {
					Unit barb2 = new Barbarian("barb12", player1);
				} else if (box3.getSelectedIndex() == 2) {
					Unit horse2 = new Horse("horse12", player1);
				} else if (box3.getSelectedIndex() == 3) {
					Unit mage2 = new Mage("mage12", player1);
				} else if (box3.getSelectedIndex() == 4) {
					Unit spear2 = new Spearman("spear12", player1);
				} else {
					Unit arch2 = new Archer("arch12", player1);
				}
				if (box4.getSelectedIndex() == 0) {
					// don't create anything
				} else if (box4.getSelectedIndex() == 1) {
					Unit barb3 = new Barbarian("barb13", player1);
				} else if (box4.getSelectedIndex() == 2) {
					Unit horse3 = new Horse("horse13", player1);
				} else if (box4.getSelectedIndex() == 3) {
					Unit mage3 = new Mage("mage13", player1);
				} else if (box4.getSelectedIndex() == 4) {
					Unit spear3 = new Spearman("spear13", player1);
				} else {
					Unit arch3 = new Archer("arch13", player1);
				}
				if (box5.getSelectedIndex() == 0) {
					// don't create anything
				} else if (box5.getSelectedIndex() == 1) {
					Unit barb4 = new Barbarian("barb14", player1);
				} else if (box5.getSelectedIndex() == 2) {
					Unit horse4 = new Horse("horse14", player1);
				} else if (box5.getSelectedIndex() == 3) {
					Unit mage4 = new Mage("mage14", player1);
				} else if (box5.getSelectedIndex() == 4) {
					Unit spear4 = new Spearman("spear14", player1);
				} else {
					Unit arch4 = new Archer("arch14", player1);
				}

				// ai's units
				if (box6.getSelectedIndex() == 0) {
					// don't create anything
				} else if (box6.getSelectedIndex() == 1) {
					Unit barb5 = new Barbarian("barb15", ai);
				} else if (box6.getSelectedIndex() == 2) {
					Unit horse5 = new Horse("horse15", ai);
				} else if (box6.getSelectedIndex() == 3) {
					Unit mage5 = new Mage("mage15", ai);
				} else if (box6.getSelectedIndex() == 4) {
					Unit spear5 = new Spearman("spear15", ai);
				} else {
					Unit arch5 = new Archer("arch15", ai);
				}
				if (box7.getSelectedIndex() == 0) {
					// don't create anything
				} else if (box7.getSelectedIndex() == 1) {
					Unit barb51 = new Barbarian("barb151", ai);
				} else if (box7.getSelectedIndex() == 2) {
					Unit horse51 = new Horse("horse151", ai);
				} else if (box7.getSelectedIndex() == 3) {
					Unit mage51 = new Mage("mage151", ai);
				} else if (box7.getSelectedIndex() == 4) {
					Unit spear51 = new Spearman("spear151", ai);
				} else {
					Unit arch51 = new Archer("arch151", ai);
				}
				if (box8.getSelectedIndex() == 0) {
					// don't create anything
				} else if (box8.getSelectedIndex() == 1) {
					Unit barb52 = new Barbarian("barb152", ai);
				} else if (box8.getSelectedIndex() == 2) {
					Unit horse52 = new Horse("horse152", ai);
				} else if (box8.getSelectedIndex() == 3) {
					Unit mage25 = new Mage("mage152", ai);
				} else if (box8.getSelectedIndex() == 4) {
					Unit spear52 = new Spearman("spear152", ai);
				} else {
					Unit arch52 = new Archer("arch152", ai);
				}
				if (box9.getSelectedIndex() == 0) {
					// don't create anything
				} else if (box9.getSelectedIndex() == 1) {
					Unit barb53 = new Barbarian("barb153", ai);
				} else if (box9.getSelectedIndex() == 2) {
					Unit horse53 = new Horse("horse153", ai);
				} else if (box9.getSelectedIndex() == 3) {
					Unit mage53 = new Mage("mage153", ai);
				} else if (box9.getSelectedIndex() == 4) {
					Unit spear53 = new Spearman("spear153", ai);
				} else {
					Unit arch53 = new Archer("arch153", ai);
				}
				if (box10.getSelectedIndex() == 0) {
					// don't create anything
				} else if (box10.getSelectedIndex() == 1) {
					Unit barb54 = new Barbarian("barb154", ai);
				} else if (box10.getSelectedIndex() == 2) {
					Unit horse54 = new Horse("horse154", ai);
				} else if (box10.getSelectedIndex() == 3) {
					Unit mage54 = new Mage("mage154", ai);
				} else if (box10.getSelectedIndex() == 4) {
					Unit spear54 = new Spearman("spear154", ai);
				} else {
					Unit arch54 = new Archer("arch154", ai);
				}
				// set teams
				for (int i = 0; i < player1.listUnits().size(); i++) {
					player1.listUnits().get(i).setTeam(Team.USER);
				}
				for (int i = 0; i < ai.listUnits().size(); i++) {
					ai.listUnits().get(i).setTeam(Team.AI);
				}
				if (mapBox.getSelectedIndex() == 0) {
					map = new MapOne();
				} else if (mapBox.getSelectedIndex() == 1){
					map = new MapTwo();
				} else {
					map = new MapThree();
				}
				gameIsSetup = true;

				// call model
				new GameController(player1, ai, map, 1);
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
		if (player1.listUnits().isEmpty()) {
			ai.won = true;
			return true;
		} else if (ai.listUnits().isEmpty()) {
			player1.won = true;
			return true;
		} else if (elapsedMinutes >= 5) {
			ai.won = true;
			return true;
		} else {
			return false;
		}
	}

	private static boolean checkIfGameIsOver2() {
		// checks if tile's still have items on them
		// returns false if there are still items, returns true if not
		for (int i = 0; i < map.getNumRow(); i++) {
			for (int j = 0; j < map.getNumCol(); j++) {
				if (map.array[i][j].hasItem == true) {
					return false;
				}
			}
		}
		return true;
	}
	
	private static boolean checkIfGameIsOver3(int turn) {
		//checks if turn is greater than 10.
		//if user has had 10 or more turns, game is over and user has won
		if(turn >= 10) {
			player1.won = true;
			return true;
		}
		if(player1.listUnits().isEmpty()) {
			player1.won = false;
			return true;
		} else if(ai.listUnits().isEmpty()) {
			player1.won = true;
			return true;
		}
		return false;
	}

	private class keyListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent k) {

			keySet.add(k.getKeyChar());
			int row, col;
			Direction d;
			currUnit = getCurrUnit();
			if (k.getKeyChar() == 'w') {
				curs.move(Direction.UP, map);
			} else if (k.getKeyChar() == 'a') {
				curs.move(Direction.LEFT, map);
			} else if (k.getKeyChar() == 's') {
				curs.move(Direction.DOWN, map);
			} else if (k.getKeyChar() == 'd') {
				curs.move(Direction.RIGHT, map);
			} else if (k.getKeyChar() == 'n' && isValid) {
				// do nothing
				Unit.currMove = currUnit.moveMultiplier;
			} else if (k.getKeyChar() == 'i' && isValid) {
				// use item
				if (player1.listItems().isEmpty() == true) {
					JOptionPane.showMessageDialog(null,
							"You have no items! Try something else:");
					return;
				} else {
					String command = JOptionPane
							.showInputDialog(
									null,
									"Select an item to use! Enter 1 for health potion,\n 2 for strength potion, or 3\n for speed shoes: ");
					if (command.equals("1")) {
						if (player1.listItems().contains(new HealthPotion()) == false) {
							JOptionPane
									.showMessageDialog(null,
											"You don't have any health potions, try something else: ");
							return;
						}
						currUnit = getCurrUnit();
						currUnit.useItem(new HealthPotion());
						Unit.currMove = currUnit.moveMultiplier;
					} else if (command.equals("2")) {
						if (player1.listItems().contains(new StrengthPotion()) == false) {
							JOptionPane
									.showMessageDialog(null,
											"You don't have any strength potions, try something else: ");
							return;
						}
						currUnit = getCurrUnit();
						currUnit.useItem(new StrengthPotion());
						Unit.currMove = currUnit.moveMultiplier;
					} else {
						if (player1.listItems().contains(new SpeedShoes()) == false) {
							JOptionPane
									.showMessageDialog(null,
											"You don't have any speed shoes, try something else: ");
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
				if (currRow < row) {
					d = Direction.DOWN;
				} else if (currRow > row) {
					d = Direction.UP;
				} else if (currCol < col) {
					d = Direction.RIGHT;
				} else if (currCol > col) {
					d = Direction.LEFT;
				} else {
					d = Direction.DOWN;
				}
				if (!map.array[row][col].isOccupied()) {
					currUnit.move(d, map);
					isAttacking = false;
					isMoving = true;
					return;
				} else {
					if (isMoving == true) {
						// cant move there
						JOptionPane.showMessageDialog(GameController.this,
								"CAN'T MOVE THERE");
					} else {
						Unit attacked = map.array[row][col].getOccupant();
						
						boolean didAttack = currUnit.attack(d, map);
						if (didAttack) {
							
							if (map.array[row][col].getOccupant() == null) {
								// if occupant of square just attacked is null,
								// then unit was killed
								JOptionPane.showMessageDialog(null, "KILLED ENEMY: "
										+ attacked.name);

								myList.remove((Unit) attacked); // remove unit
																// from list
								// check whos team unit was on
								if (player1.listUnits().contains(
										(Unit) attacked)) {
									// killed unit was on player's team
									player1.units.remove((Unit) attacked);
								} else {
									ai.units.remove((Unit) attacked);
								}
							} else {
								JOptionPane.showMessageDialog(null,
										"Completed attack on enemy! Did "
												+ currUnit.attack + " damage");
							}
						} else {
							JOptionPane.showMessageDialog(null,
									"INVALID ATTACK, RETRY: ");
							return;
						}
						Unit.currMove = currUnit.moveMultiplier;
					}
				}

			} else {
				// do nothing

			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			keySet.remove(arg0.getKeyChar());
		}
	}

	public void save() {
		ObjectOutputStream saveStream = null;
		String fileName = "";

		while (fileName.equals("")) {
			fileName = JOptionPane
					.showInputDialog("Enter the name of the file you'd like to save to.");
			if (fileName == null) {
				return;
			}
		}

		// Create the stream and file to save to.
		try {
			saveStream = new ObjectOutputStream(new FileOutputStream(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Write the model to the file.
		try {
			saveStream.writeObject(this);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Make sure to close the stream!!!1!
		try {
			saveStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
