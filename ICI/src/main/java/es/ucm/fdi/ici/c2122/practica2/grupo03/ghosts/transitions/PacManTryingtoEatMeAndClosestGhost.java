package es.ucm.fdi.ici.c2122.practica2.grupo03.ghosts.transitions;

import es.ucm.fdi.ici.Input;
import es.ucm.fdi.ici.fsm.Transition;
import es.ucm.fdi.ici.c2122.practica2.grupo03.ghosts.GhostsInput;
import pacman.game.Constants.GHOST;

public class PacManTryingtoEatMeAndClosestGhost implements Transition  {

	public static double thresold = 20;
	
	GHOST ghost;
	public PacManTryingtoEatMeAndClosestGhost(GHOST ghost) {
		super();
		this.ghost = ghost;
	}



	@Override
	public boolean evaluate(Input in) {
		GhostsInput input = (GhostsInput) in;
		return ghost == input.getclosestGhost() && input.getminDistancePacmanGhost() < thresold;
	}



	@Override
	public String toString() {
		return "Pacman trying to eat closest Ghost and i´m the closest";
	}

	
	
}
