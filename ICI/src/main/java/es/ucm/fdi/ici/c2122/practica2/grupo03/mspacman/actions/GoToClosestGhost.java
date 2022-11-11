package es.ucm.fdi.ici.c2122.practica2.grupo03.mspacman.actions;

import es.ucm.fdi.ici.Action;
import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class GoToClosestGhost implements Action {


	private GHOST closestGhost;
	private GHOST secondGhost;
	private GHOST thirdGhost;
	private GHOST farthestGhost;
	
	public GoToClosestGhost() {
	}
	
	@Override
	public MOVE execute(Game game) {
		int pacmanNode= game.getPacmanCurrentNodeIndex();
		
		double dstClosest=Double.MAX_VALUE;
		double dstSecond=Double.MAX_VALUE;
		double dstThird=Double.MAX_VALUE;
		double dstFarthest=Double.MAX_VALUE;
		
		for(GHOST g: GHOST.values()) {
			double dst= game.getDistance(pacmanNode, game.getGhostCurrentNodeIndex(g), DM.PATH);
			if(dstClosest>dst) {
				dstFarthest=dstThird;
				farthestGhost=thirdGhost;
				dstThird=dstSecond;
				thirdGhost=secondGhost;
				dstSecond=dstClosest;
				secondGhost=closestGhost;
				dstClosest=dst;
				closestGhost=g;
			}
			else if(dst<dstSecond) {
				dstFarthest=dstThird;
				thirdGhost=secondGhost;
				dstThird=dstSecond;
				secondGhost=g;
				dstSecond=dst;
			}
			else if(dst<dstThird) {
				dstFarthest=dstThird;
				farthestGhost=thirdGhost;
				dstThird=dst;
				thirdGhost=g;
			}
			else {
				dstFarthest=dst;
				farthestGhost=g;
			}
			
		}
		
		if(game.isGhostEdible(closestGhost)) {
			return game.getNextMoveTowardsTarget(pacmanNode, game.getGhostCurrentNodeIndex(closestGhost), DM.PATH);
		}
		else if(game.isGhostEdible(secondGhost)) {
			return game.getNextMoveTowardsTarget(pacmanNode, game.getGhostCurrentNodeIndex(secondGhost), DM.PATH);
		}
		
		else {
			return game.getNextMoveAwayFromTarget(pacmanNode, game.getGhostCurrentNodeIndex(closestGhost),DM.PATH);
			
		}
		
	}

	@Override
	public String getActionId() {
		return "Chase ghost";
	}

}
