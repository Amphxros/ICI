package es.ucm.fdi.ici.c2122.practica2.grupo03.ghosts.transitions;

import es.ucm.fdi.ici.Input;
import es.ucm.fdi.ici.fsm.Transition;
import es.ucm.fdi.ici.c2122.practica2.grupo03.ghosts.GhostsInput;
import pacman.game.Constants.GHOST;

public class PacManNotFurtherThan20 implements Transition  {

	public static double thresold = 20;  /// Lo que cambia entre clases
	
	GHOST ghost;
	public PacManNotFurtherThan20(GHOST ghost) {
		super();
		this.ghost = ghost;
	}



	@Override
	public boolean evaluate(Input in) {
		GhostsInput input = (GhostsInput)in;
		PacManFurtherThan20 further = new PacManFurtherThan20(ghost);
		return !further.evaluate(input);
	}



	@Override
	public String toString() {
		return "Pacman not further away than " + thresold;
	}

	
	
}
