package es.ucm.fdi.ici.c2122.practica2.grupo03.mspacman.actions;

import es.ucm.fdi.ici.Action;
import pacman.game.Constants.DM;
import pacman.game.Constants.MOVE;
import pacman.game.Game;
public class GoToClosestPowerPill implements Action {

	public GoToClosestPowerPill() {
	}
	
	@Override
	public MOVE execute(Game game) {

		int pacman= game.getPacmanCurrentNodeIndex();
		int [] ppill= game.getActivePowerPillsIndices();
		int target= game.getClosestNodeIndexFromNodeIndex(pacman, ppill, DM.PATH);
		
		if(target!=-1) {
			return game.getMoveToMakeToReachDirectNeighbour(pacman, target);
		}
		else {
			return MOVE.NEUTRAL; // no deberia llegar a este estado
		}
	}

	@Override
	public String getActionId() {
		return "";
	}

}
