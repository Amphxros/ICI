package es.ucm.fdi.ici.c2122.practica2.grupo03.mspacman.transitions;

import es.ucm.fdi.ici.Input;
import es.ucm.fdi.ici.fsm.Transition;
import es.ucm.fdi.ici.c2122.practica2.grupo03.mspacman.MsPacManInput;


public class PowerPillClose implements Transition{
	float closeness=300;
	public PowerPillClose() {
		
	}
	@Override
	public boolean evaluate(Input in) {
		MsPacManInput input = (MsPacManInput)in;
		if(input.getFarthestPPill()!=-1) {
		return input.getClosestPPillDistance()<closeness;
		}
		else {
			return false;
		}
	}

}