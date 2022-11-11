package es.ucm.fdi.ici.c2122.practica2.grupo03.mspacman.transitions;

import es.ucm.fdi.ici.Input;
import es.ucm.fdi.ici.fsm.Transition;
import es.ucm.fdi.ici.c2122.practica2.grupo03.mspacman.MsPacManInput;

public class CloseGhost implements Transition  {
	int ghost_limit=10;
	public CloseGhost() {
		super();
	}
	

	@Override
	public boolean evaluate(Input in) {
		MsPacManInput input = (MsPacManInput)in;
	
		return ( input.getDistanceFromClosestGhost()< ghost_limit && input.getEatingTimeFromClosestGhost()<0) ||
			   ( input.getDistanceFromSecondGhost()<ghost_limit && input.getEatingTimeFromSecondGhost()<0) ||
			   ( input.getDistanceFromThirdGhost()<ghost_limit && input.getEatingTimeFromThirdGhost()<0);
		
	}



	@Override
	public String toString() {
		return "Ghost Is close";
	}

	
	
}
