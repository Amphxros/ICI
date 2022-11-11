package es.ucm.fdi.ici.c2122.practica4.grupo03.ghosts.actions;

import es.ucm.fdi.ici.c2122.practica4.grupo03.ghost.FuzzyGhostAction;
import es.ucm.fdi.ici.c2122.practica4.grupo03.utils.FuzzyMemory;
import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class FRunToQuadrant extends FuzzyGhostAction {

	private int[] quadNodes = {
		950, 1010, 220, 160	
	};
	
	public FRunToQuadrant(FuzzyMemory memory, GHOST ghost) {
		super(memory, ghost);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String getActionId() {
		// TODO Auto-generated method stub
		return "RunToQuadrant";
	}

	@Override
	public MOVE calcularMovimiento(Game game, FuzzyMemory mem) {
		// TODO Auto-generated method stub
		if(!game.doesGhostRequireAction(getGhost())) {
			return MOVE.NEUTRAL;
		}
		int ghostNode = game.getGhostCurrentNodeIndex(getGhost());
		MOVE lm = game.getGhostLastMoveMade(getGhost());
		
		return game.getNextMoveTowardsTarget(ghostNode, quadNodes[getGhost().ordinal()], lm, DM.PATH);
	}

}
