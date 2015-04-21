package controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.TreeSet;

import javax.swing.*;

import model.*;
import view.*;
/**
 * The controller
 * @author Alex Nuccio, Eric Myre, Angel Cornejo
 *
 */
public class GameController extends JFrame{
	
	JButton upButton, downButton, leftButton, rightButton;
	Unit barb;
	MapOne map;
	JPanel mainPanel;
	private TreeSet<Character> keySet;
	/** 
	 * 	This is the constructor
	 */
	public GameController(){
		keySet = new TreeSet<Character>();
		mainPanel = new JPanel();
		
		GameView view = new GameView();
		
		map = view.getMap();
		
		/*
		JPanel buttonPanel = new JPanel();
		upButton = new JButton("Up");
		downButton = new JButton("Down");
		leftButton = new JButton("Left");
		rightButton = new JButton("Right");
		buttonPanel.add(upButton);
		buttonPanel.add(leftButton);
		buttonPanel.add(downButton);
		buttonPanel.add(rightButton);
		
		upButton.addActionListener(new MyActionListener());
		downButton.addActionListener(new MyActionListener());
		leftButton.addActionListener(new MyActionListener());
		rightButton.addActionListener(new MyActionListener());
		
		*/
		mainPanel.add(view);
		Player player1 = new Player();
		barb = new Barbarian("Barb", player1);
		barb.setPosition(4, 4, map);
		map.repaint();
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
				
				keySet.add(k.getKeyChar());
				
				if(k.getKeyChar() == 'w') {
					barb.move(Direction.UP, map);
					map.repaint();
				} else if(k.getKeyChar() == 'a') {
					barb.move(Direction.LEFT, map);
					map.repaint();
				} else if(k.getKeyChar() == 's') {
					barb.move(Direction.DOWN, map);
					map.repaint();
				} else  if(k.getKeyChar() == 'd'){
					barb.move(Direction.RIGHT, map);
					map.repaint();
				} else {
					//do nothing
				}
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {keySet.remove(arg0.getKeyChar());}
		});
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
		
		
		
		
	}
	

	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == upButton) {
			barb.move(Direction.UP, map);
			map.repaint();
		} else if(e.getSource() == downButton) {
			barb.move(Direction.DOWN, map);
			map.repaint();
		} else if(e.getSource() == leftButton) {
			barb.move(Direction.LEFT, map);
			map.repaint();
		} else {
			barb.move(Direction.RIGHT, map);
			map.repaint();
		}
		
	}
}
