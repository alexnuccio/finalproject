package model;

import java.util.Random;

import javax.swing.JOptionPane;

/**
 * MediumStrategy
 * 
 * The strategy in which the AI units will attack.
 * 
 * @author Alex Nuccio, Eric Myre, Angel Cornejo
 */
public class MediumStrategy extends Strategy {

	private Player ai;
	private int currRow;
	private int currCol;
	private int reference;
	private boolean attacked = false;
	
	
	/**
	 * MediumStrategy
	 * 
	 * Constructor.
	 * 
	 * @param computer
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public MediumStrategy(Player computer) {
		super(computer);
	}
	
	/**
	 * aiMove
	 * 
	 * Conducts the actions that make up an AI controlled unit's turn.
	 * Tries to attack in every direction, moves randomly if no attack was made.
	 * 
	 * @param currUnit	the unit who's turn it is
	 * 
	 * @param map	the map 
	 * 
	 * @author Alex Nuccio, Eric Myre, Angel Cornejo
	 */
	public void aiMove(Unit currUnit, MapOne map) {
		
		if(currUnit.getTeam() == Team.USER) {
			return;
		}
		
		if(Unit.currMove > 0) {
			Unit.currMove = 100;
			return;
		}
		//try to attack in every direction
		attacked = currUnit.attack(Direction.UP, map);
		if(!attacked) {
			attacked = currUnit.attack(Direction.DOWN,  map);
		} else {
			JOptionPane.showMessageDialog(null, "Enemy attacked a unit!");
			//Unit.currMove = 100;
			return;
		}
		if(!attacked) {
			attacked = currUnit.attack(Direction.LEFT, map);
		} else {
			JOptionPane.showMessageDialog(null, "Enemy attacked a unit!");
			//Unit.currMove = 100;
			return;
		}
		if(!attacked) {
			attacked = currUnit.attack(Direction.RIGHT, map);
		} else {
			JOptionPane.showMessageDialog(null, "Enemy attacked a unit!");
			//Unit.currMove = 100;
			return;
		}
		if(attacked) {
			JOptionPane.showMessageDialog(null, "Enemy attacked a unit!");
			//Unit.currMove = 100;
			return;
		}
		//if you successfully attacked, this unit's turn is over 
		//if there were no adjacent units to attack, move in a random direction
		
		Random rand = new Random();
		while (Unit.currMove < currUnit.moveMultiplier) {
			// generate random moves
			int randNum = rand.nextInt((3 - 0) + 1) + 0;
			boolean succeeded = true;
			switch (randNum) {
			case 0:
				succeeded = currUnit.move(Direction.UP, map);
				if (succeeded == false) {
					Unit.currMove++;
				}
				break;
			case 1:
				succeeded = currUnit.move(Direction.LEFT, map);
				if (succeeded == false) {
					Unit.currMove++;
				}
				break;
			case 2:
				succeeded = currUnit.move(Direction.RIGHT, map);
				if (succeeded == false) {
					Unit.currMove++;
				}
				break;
			case 3:
				succeeded = currUnit.move(Direction.DOWN, map);
				if (succeeded == false) {
					Unit.currMove++;
				}
				break;
			default:
				succeeded = currUnit.move(Direction.UP, map);
				if (succeeded == false) {
					Unit.currMove++;
				}
			}
			map.repaint();
		} // end of while
		Unit.currMove = 0;
	}
}
