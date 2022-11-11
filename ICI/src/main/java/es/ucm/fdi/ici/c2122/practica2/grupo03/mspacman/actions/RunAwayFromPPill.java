package es.ucm.fdi.ici.c2122.practica2.grupo03.mspacman.actions;

import es.ucm.fdi.ici.Action;
import pacman.game.Constants.DM;
import pacman.game.Constants.MOVE;
import pacman.game.Constants.GHOST;
import pacman.game.Game;

public class RunAwayFromPPill implements Action {

	float closeness=20;
	public RunAwayFromPPill() {

	}
	
	@Override
	public MOVE execute(Game game) {
		int pacmanNode= game.getPacmanCurrentNodeIndex();
		int [] ppill= game.getActivePowerPillsIndices();	
		int target= game.getClosestNodeIndexFromNodeIndex(pacmanNode, ppill, DM.EUCLID);
		
		GHOST [] ghosts= GHOST.values();
		int ind=-1;
		//si el fantasma sigue más cerca que el pacman de esa pill saldremos huyendo
		GHOST closestGhost= null;
		double distancefromPill=Double.MAX_VALUE;
		for(GHOST g: ghosts) {
			if(game.getGhostLairTime(g)>0) {
				double d= game.getDistance(game.getGhostCurrentNodeIndex(g), target, DM.PATH);
				if(distancefromPill>d) {
					distancefromPill=d;
					closestGhost=g;
					ind++;
				}
			}
		}
		
		if( ind!=-1 && !game.isGhostEdible(closestGhost) && distancefromPill<closeness )
			return game.getNextMoveAwayFromTarget(pacmanNode, target,DM.PATH);
		else {
			return game.getMoveToMakeToReachDirectNeighbour(pacmanNode, target);
		}
	}

	@Override
	public String getActionId() {
		return "";
	}

}
