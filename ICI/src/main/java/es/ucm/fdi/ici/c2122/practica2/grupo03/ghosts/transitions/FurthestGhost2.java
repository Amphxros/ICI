package es.ucm.fdi.ici.c2122.practica2.grupo03.ghosts.transitions;

import es.ucm.fdi.ici.Input;
import es.ucm.fdi.ici.fsm.Transition;
import es.ucm.fdi.ici.c2122.practica2.grupo03.ghosts.GhostsInput;
import pacman.game.Constants.GHOST;

public class FurthestGhost2 implements Transition  {
	
	GHOST ghost;
	public FurthestGhost2(GHOST ghost) {
		super();
		this.ghost = ghost;
	}



	@Override
	public boolean evaluate(Input in) {
		GhostsInput input = (GhostsInput) in;
		return ghost != input.getthirdclosestGhost() && ghost != input.getsecondclosestGhost() && ghost != input.getclosestGhost();
	}



	@Override
	public String toString() {
		return "Im not Pacman´s closest ghost 2";
	}

	
	
}
