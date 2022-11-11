package es.ucm.fdi.ici.c2122.practica2.grupo03.mspacman.actions;

import es.ucm.fdi.ici.Action;
import pacman.game.Constants.DM;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class GoToClosestPill implements Action {

	public GoToClosestPill() {
	}
	
	@Override
	public MOVE execute(Game game) {

		int pacman= game.getPacmanCurrentNodeIndex();
		int [] pills= game.getActivePillsIndices();
		int p= game.getClosestNodeIndexFromNodeIndex(pacman, pills, DM.PATH); 
		return game.getMoveToMakeToReachDirectNeighbour(pacman, p);
	}

	@Override
	public String getActionId() {
		return "";
	}

}
