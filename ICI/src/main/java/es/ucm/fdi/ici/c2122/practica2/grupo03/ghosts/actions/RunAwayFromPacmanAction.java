package es.ucm.fdi.ici.c2122.practica2.grupo03.ghosts.actions;

import es.ucm.fdi.ici.Action;
import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class RunAwayFromPacmanAction implements Action {

    GHOST ghost;
	public RunAwayFromPacmanAction(GHOST ghost) {
		this.ghost = ghost;
	}

	@Override
	public MOVE execute(Game game) {
		int gn = game.getGhostCurrentNodeIndex(ghost);
		int pacmannode = game.getPacmanCurrentNodeIndex();
		return game.getNextMoveAwayFromTarget(gn, pacmannode, 
				game.getGhostLastMoveMade(ghost), DM.PATH);
	}

	@Override
	public String getActionId() {
		return ghost+ "runsAway from Pacman";
	}
}
