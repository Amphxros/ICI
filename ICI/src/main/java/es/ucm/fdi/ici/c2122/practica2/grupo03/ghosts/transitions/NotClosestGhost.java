package es.ucm.fdi.ici.c2122.practica2.grupo03.ghosts.transitions;

import es.ucm.fdi.ici.Input;
import es.ucm.fdi.ici.fsm.Transition;
import es.ucm.fdi.ici.c2122.practica2.grupo03.ghosts.GhostsInput;
import pacman.game.Constants.GHOST;

public class NotClosestGhost implements Transition  {
	
	GHOST ghost;
	public NotClosestGhost(GHOST ghost) {
		super();
		this.ghost = ghost;
	}



	@Override
	public boolean evaluate(Input in) {
		GhostsInput input = (GhostsInput) in;
		return ghost != input.getclosestGhost();
	}



	@Override
	public String toString() {
		return "Im not Pacman´s closest ghost";
	}

	
	
}
