package model;

import java.util.ArrayList;
import java.util.Random;

public abstract class Strategy {

	private Random ran;
	private Player computer;
	
	public Strategy (Player ai) {
		computer = ai;
	}
	
	public abstract void aiMove(Unit currUnit, MapOne map); 
}
