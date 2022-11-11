package es.ucm.fdi.ici.c2122.practica4.grupo03;

import es.ucm.fdi.ici.Action;
import es.ucm.fdi.ici.c2122.practica4.grupo03.utils.FuzzyMemory;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public abstract class FuzzyAction implements Action {

	FuzzyMemory mem;
	
	public FuzzyAction(FuzzyMemory memory) {
		super();
		mem = memory;
	}
	
	@Override
	public abstract String getActionId();

	@Override
	public MOVE execute(Game game) {
		return calcularMovimiento(game, mem);
	}
	
	public abstract MOVE calcularMovimiento(Game game, FuzzyMemory mem);
	
}
