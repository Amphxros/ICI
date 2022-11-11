package es.ucm.fdi.ici.c2122.practica2.grupo03.mspacman.actions;

import es.ucm.fdi.ici.Action;
import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;


public class goToTheFarthestPill implements Action {

	@Override
	public String getActionId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MOVE execute(Game game) {
		// TODO Auto-generated method stub
		int [] pills= game.getActivePillsIndices();
		int pacman= game.getPacmanCurrentNodeIndex();
		int target= game.getFarthestNodeIndexFromNodeIndex(pacman, pills, DM.PATH);
		
		return game.getMoveToMakeToReachDirectNeighbour(pacman, target);
	}

}
