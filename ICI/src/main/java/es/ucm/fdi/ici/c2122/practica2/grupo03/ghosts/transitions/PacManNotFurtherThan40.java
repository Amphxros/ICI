package es.ucm.fdi.ici.c2122.practica2.grupo03.ghosts.transitions;

import es.ucm.fdi.ici.Input;
import es.ucm.fdi.ici.fsm.Transition;
import es.ucm.fdi.ici.c2122.practica2.grupo03.ghosts.GhostsInput;
import pacman.game.Constants.GHOST;

public class PacManNotFurtherThan40 implements Transition  {

	public static double thresold = 30;  /// Lo que cambia entre clases
	
	GHOST ghost;
	public PacManNotFurtherThan40(GHOST ghost) {
		super();
		this.ghost = ghost;
	}



	@Override
	public boolean evaluate(Input in) {
		GhostsInput input = (GhostsInput)in;
		PacManFurtherThan30 further = new PacManFurtherThan30(ghost);
		return !further.evaluate(input);
	}



	@Override
	public String toString() {
		return "Pacman not further away than " + thresold;
	}

	
	
}
