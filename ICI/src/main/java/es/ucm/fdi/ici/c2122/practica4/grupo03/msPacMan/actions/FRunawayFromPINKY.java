package es.ucm.fdi.ici.c2122.practica4.grupo03.msPacMan.actions;

import es.ucm.fdi.ici.c2122.practica4.grupo03.msPacMan.MsPacmanAction;
import es.ucm.fdi.ici.c2122.practica4.grupo03.utils.FuzzyMemory;
import pacman.game.Constants.DM;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class FRunawayFromPINKY extends MsPacmanAction {

	public FRunawayFromPINKY(FuzzyMemory memory) {
		super(memory);
	}

	@Override
	public String getActionId() {
		return "RunawayFromPINKY";
	}

	@Override
	public MOVE calcularMovimiento(Game game, FuzzyMemory mem) {
		int dist = (int) mem.getVar("PINKYNode");

		if(dist == 0 || dist == -1) {
			return MOVE.NEUTRAL;
		}
		
		int pnode = game.getPacmanCurrentNodeIndex();
		MOVE lm   = game.getPacmanLastMoveMade();
		
		return game.getNextMoveAwayFromTarget(pnode, dist, lm, DM.PATH);
	}

}
