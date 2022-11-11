package es.ucm.fdi.ici.c2122.practica4.grupo03.msPacMan.actions;

import es.ucm.fdi.ici.c2122.practica4.grupo03.msPacMan.MsPacmanAction;
import es.ucm.fdi.ici.c2122.practica4.grupo03.utils.FuzzyMemory;
import pacman.game.Constants.DM;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class FRunawayFromINKY extends MsPacmanAction {

	public FRunawayFromINKY(FuzzyMemory memory) {
		super(memory);
	}

	@Override
	public String getActionId() {
		return "RunawayFromINKY";
	}

	@Override
	public MOVE calcularMovimiento(Game game, FuzzyMemory mem) {
		int dist = (int) mem.getVar("INKYNode");

		if(dist == 0 || dist == -1) {
			return MOVE.NEUTRAL;
		}
		
		int pnode = game.getPacmanCurrentNodeIndex();
		MOVE lm   = game.getPacmanLastMoveMade();
		
		return game.getNextMoveAwayFromTarget(pnode, dist, lm, DM.PATH);
	}

}
