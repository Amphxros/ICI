package es.ucm.fdi.ici.c2122.practica2.grupo03.ghosts.transitions;

import es.ucm.fdi.ici.Input;
import es.ucm.fdi.ici.fsm.Transition;
import es.ucm.fdi.ici.c2122.practica2.grupo03.ghosts.GhostsInput;
import pacman.game.Constants.GHOST;

public class GhostsNotEdibleTransition implements Transition  {

	GHOST ghost;
	public GhostsNotEdibleTransition(GHOST ghost) {
		super();
		this.ghost = ghost;
	}



	@Override
	public boolean evaluate(Input in) {
		GhostsInput input = (GhostsInput)in;
		GhostsEdibleTransition edible = new GhostsEdibleTransition(ghost);
		return !edible.evaluate(input);
	}



	@Override
	public String toString() {
		return "Ghost is not edible";
	}

	
	
}
