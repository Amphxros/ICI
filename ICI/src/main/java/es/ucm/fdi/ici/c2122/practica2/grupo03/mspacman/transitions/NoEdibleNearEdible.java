package es.ucm.fdi.ici.c2122.practica2.grupo03.mspacman.transitions;

import es.ucm.fdi.ici.Input;
import es.ucm.fdi.ici.fsm.Transition;
import es.ucm.fdi.ici.c2122.practica2.grupo03.mspacman.MsPacManInput;


public class NoEdibleNearEdible implements Transition {

	@Override
	public boolean evaluate(Input in) {
		// TODO Auto-generated method stub
		MsPacManInput input = (MsPacManInput)in;
		return !input.isClosestGhostEdible() && input.isSecondGhostEdible() && (input.getDistanceFromSecondGhost() - input.getDistanceFromClosestGhost() <20);				
	}

}
