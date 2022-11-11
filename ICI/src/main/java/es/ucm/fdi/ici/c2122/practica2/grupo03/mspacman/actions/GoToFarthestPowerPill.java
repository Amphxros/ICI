package es.ucm.fdi.ici.c2122.practica2.grupo03.mspacman.actions;

import es.ucm.fdi.ici.Action;
import pacman.game.Constants.DM;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class GoToFarthestPowerPill implements Action{

	public GoToFarthestPowerPill() {
	}
	
	@Override
	public MOVE execute(Game game) {
		int pacman= game.getPacmanCurrentNodeIndex();
		int [] pills= game.getActivePillsIndices();
		int p= game.getFarthestNodeIndexFromNodeIndex(pacman, pills, DM.PATH); 
		return game.getMoveToMakeToReachDirectNeighbour(pacman, p);
	}
	public String getActionId() {
		// TODO Auto-generated method stub
		return "";
	}

}
