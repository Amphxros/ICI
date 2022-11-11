package es.ucm.fdi.ici.c2122.practica4.grupo03.ghosts.actions;

import es.ucm.fdi.ici.c2122.practica4.grupo03.ghost.FuzzyGhostAction;
import es.ucm.fdi.ici.c2122.practica4.grupo03.utils.FuzzyMemory;
import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class FChasePacman extends FuzzyGhostAction {

	public FChasePacman(FuzzyMemory memory, GHOST ghost) {
		super(memory, ghost);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getActionId() {
		// TODO Auto-generated method stub
		return "ChasePacman";
	}

	@Override
	public MOVE calcularMovimiento(Game game, FuzzyMemory mem) {
		// TODO Auto-generated method stub
		if(!game.doesGhostRequireAction(getGhost())) {
			return MOVE.NEUTRAL;
		}
		int ghostNode = game.getGhostCurrentNodeIndex(getGhost());
		int pacmannode = (int) mem.getVar("PacmanNode");
		
		return game.getNextMoveTowardsTarget(ghostNode, pacmannode, 
				game.getGhostLastMoveMade(getGhost()), DM.PATH);
	}

}
