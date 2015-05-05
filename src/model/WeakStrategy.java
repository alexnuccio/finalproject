package model;

import java.util.ArrayList;
import java.util.Random;

public class WeakStrategy extends Strategy {

	private Player ai;
	private Unit currUnit;

	public WeakStrategy(Player computer) {
		super(computer);
	}

	public void aiMove(Unit currUnit, MapOne map) {
		if(currUnit.getTeam() == Team.USER) {
			return;
		}
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
