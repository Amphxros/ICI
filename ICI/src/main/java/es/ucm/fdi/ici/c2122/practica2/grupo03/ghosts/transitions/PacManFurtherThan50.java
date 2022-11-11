package es.ucm.fdi.ici.c2122.practica2.grupo03.ghosts.transitions;

import es.ucm.fdi.ici.Input;
import es.ucm.fdi.ici.fsm.Transition;
import es.ucm.fdi.ici.c2122.practica2.grupo03.ghosts.GhostsInput;
import pacman.game.Constants.GHOST;

public class PacManFurtherThan50 implements Transition  {

	public static double thresold = 50;  /// Lo que cambia entre clases
	
	GHOST ghost;
	public PacManFurtherThan50(GHOST ghost) {
		super();
		this.ghost = ghost;
	}



	@Override
	public boolean evaluate(Input in) {
		GhostsInput input = (GhostsInput)in;
		switch(ghost) {
			case BLINKY:
				return input.getBLINKYdistance() > thresold;
			case INKY:
				return input.getINKYdistance() > thresold;
			case PINKY:
				return input.getPINKYdistance() > thresold;
			case SUE:
				return input.getSUEdistance() > thresold;
			default:
				return false;
		}
	}



	@Override
	public String toString() {
		return "Pacman further away than " + thresold;
	}

	
	
}
