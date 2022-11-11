package es.ucm.fdi.ici.c2122.practica2.grupo03.mspacman.transitions;

import es.ucm.fdi.ici.Input;
import es.ucm.fdi.ici.fsm.Transition;
import es.ucm.fdi.ici.c2122.practica2.grupo03.mspacman.MsPacManInput;


public class PowerPills implements Transition  {

	@Override
	public boolean evaluate(Input in) {
		MsPacManInput input = (MsPacManInput)in;
		
		return !input.thereareNoPPills(); // there is at least a PPill
	}

	@Override
	public String toString() {
		return "There are powerPills";
	}
}
