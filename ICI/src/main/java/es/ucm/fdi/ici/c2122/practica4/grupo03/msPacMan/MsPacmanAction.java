package es.ucm.fdi.ici.c2122.practica4.grupo03.msPacMan;

import es.ucm.fdi.ici.c2122.practica4.grupo03.FuzzyAction;
import es.ucm.fdi.ici.c2122.practica4.grupo03.utils.FuzzyMemory;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class MsPacmanAction extends FuzzyAction {

	public MsPacmanAction(FuzzyMemory memory) {
		super(memory);
		
	}
	

	public String getActionId() {
		return null;
	}

	public MOVE calcularMovimiento(Game game, FuzzyMemory mem) {
		return null;
	}

}
