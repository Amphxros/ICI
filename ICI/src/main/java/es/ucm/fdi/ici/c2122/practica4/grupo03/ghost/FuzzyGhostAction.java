package es.ucm.fdi.ici.c2122.practica4.grupo03.ghost;

import es.ucm.fdi.ici.c2122.practica4.grupo03.FuzzyAction;
import es.ucm.fdi.ici.c2122.practica4.grupo03.utils.FuzzyMemory;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public abstract class FuzzyGhostAction extends FuzzyAction {
	
	GHOST g;
	
	public FuzzyGhostAction(FuzzyMemory memory, GHOST ghost) {
		super(memory);
		g = ghost;
	}
	
	@Override
	public abstract String getActionId();

	@Override
	public abstract MOVE calcularMovimiento(Game game, FuzzyMemory mem);
	
	public GHOST getGhost() { return g; }
	
}
