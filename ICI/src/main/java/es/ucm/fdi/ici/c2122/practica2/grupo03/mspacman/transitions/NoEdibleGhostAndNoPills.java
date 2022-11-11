package es.ucm.fdi.ici.c2122.practica2.grupo03.mspacman.transitions;

import es.ucm.fdi.ici.Input;
import es.ucm.fdi.ici.fsm.Transition;
import es.ucm.fdi.ici.c2122.practica2.grupo03.mspacman.MsPacManInput;

public class NoEdibleGhostAndNoPills implements Transition{

	@Override
	public boolean evaluate(Input in) {
		MsPacManInput input = (MsPacManInput)in;
		NoEdibleGhost edible= new NoEdibleGhost();
		PowerPills p= new PowerPills();
		return edible.evaluate(input) && !p.evaluate(input);
	}

}
