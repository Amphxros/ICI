package es.ucm.fdi.ici.c2122.practica2.grupo03.mspacman.transitions;

import es.ucm.fdi.ici.Input;
import es.ucm.fdi.ici.fsm.Transition;
import es.ucm.fdi.ici.c2122.practica2.grupo03.mspacman.MsPacManInput;


public class NoCloseGhost implements Transition{

	@Override
	public boolean evaluate(Input in) {
		MsPacManInput input = (MsPacManInput)in;
		CloseGhost c= new CloseGhost();
		return !c.evaluate(input);
	}
	public String toString() {
		return "Ghost Is Not close";
	}

}
