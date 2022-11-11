package es.ucm.fdi.ici.c2122.practica1.grupo03;

import java.awt.Color;
import java.util.Random;

import pacman.controllers.PacmanController;
import pacman.game.Constants;
import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;
import pacman.game.GameView;

public final class MsPacMan extends PacmanController {

	private Random rnd = new Random();
	private MOVE[] allMoves = MOVE.values();
	int limit=20;

	@Override
	public MOVE getMove(Game game, long timeDue) {
	
		int pacman= game.getPacmanCurrentNodeIndex();
		int numPowerPills= game.getNumberOfActivePowerPills();
		
		int nearestAttackingGhost=getNearestAttackingGhost(game);
		int nearestEdibleGhost= getNearestEdibleGhost(game);
		int nearestPill= getNearestPowerPill(game,numPowerPills);
		
		//si no hay ninguna powerpill intentamos buscar a los fantasmas más cercanos y huir de ellos
		if(numPowerPills <= 0) {
			
			if(game.getShortestPathDistance(pacman, nearestAttackingGhost)<limit) { //si hay fantasmas cerca, huimos
				return game.getApproximateNextMoveAwayFromTarget(pacman, nearestAttackingGhost, game.getPacmanLastMoveMade(),DM.PATH);
			}
			else { //si no
				//miramos si hay alguno comestible cerca
				if(nearestEdibleGhost !=-1 && game.getShortestPathDistance(pacman, nearestEdibleGhost,game.getPacmanLastMoveMade())<limit*4) {
					return game.getMoveToMakeToReachDirectNeighbour(pacman, nearestEdibleGhost);
				}
				//si no vamos a por la pill más cercana
				else {
					int closestpill= getNearestPill(game);
					return game.getApproximateNextMoveTowardsTarget(pacman, closestpill, game.getPacmanLastMoveMade(), DM.MANHATTAN);
				}
			}
			
		}
		//si hay alguna intentamos ir hacia la más cercana 
		else {
			//aunque con prioridad de huir de los fantasmas
			if(game.getShortestPathDistance(pacman, nearestAttackingGhost)<limit) { //si hay fantasmas cerca, huimos
				return game.getApproximateNextMoveAwayFromTarget(pacman, nearestAttackingGhost, game.getPacmanLastMoveMade(),DM.PATH);
			}
			
			//si hay un fantasma mas cerca de la power pill que el propio pacman tambien huye
			else if(game.getShortestPathDistance(pacman, nearestPill)> game.getShortestPathDistance(pacman, nearestAttackingGhost) && game.getShortestPathDistance(pacman, nearestAttackingGhost)<4*limit) {
				return game.getApproximateNextMoveAwayFromTarget(pacman, nearestAttackingGhost, game.getPacmanLastMoveMade(),DM.PATH);
			}
			else {
				//si hay fantasmas comestibles
				if(nearestEdibleGhost !=-1 && (areAllGhostsEdible(game) || game.getShortestPathDistance(nearestEdibleGhost, pacman, game.getPacmanLastMoveMade())<game.getShortestPathDistance(pacman, nearestAttackingGhost, game.getPacmanLastMoveMade()))) {
					return game.getMoveToMakeToReachDirectNeighbour(pacman, nearestEdibleGhost);
				}
				else { //si no intentamos ir a la powerpill mas cercana
					if(game.getShortestPathDistance(pacman, nearestPill, game.getPacmanLastMoveMade())< game.getShortestPathDistance(nearestAttackingGhost, nearestPill, game.getPacmanLastMoveMade())) {
						return game.getMoveToMakeToReachDirectNeighbour(pacman, nearestPill);
					}
					
				}
			}
		}
		return null;
		
	}
	
	

	

	// devuelve el fantasma no comestible mas cercano
	private int getNearestAttackingGhost(Game game) {
		int pacman= game.getPacmanCurrentNodeIndex();
		int nearestGhost=-1;
		
		int [] attackers= new int[GHOST.values().length]; 
		int i=0;
		for(GHOST g: GHOST.values()) {
			
			if(!game.isGhostEdible(g)) {
				attackers[i]= game.getGhostCurrentNodeIndex(g);
				i++;
			}
		}
		if(i==0) 
			return -1;
		else
			return game.getClosestNodeIndexFromNodeIndex(pacman, attackers, DM.PATH);
	}

	
	//devuelve el indice del fantasma comestible mas cercano
	private int getNearestEdibleGhost(Game game) {
		int pacman= game.getPacmanCurrentNodeIndex();
		int [] edibles= new int[GHOST.values().length]; 
		int i=0;
		
		for(GHOST g: GHOST.values()) {
			
			if(game.isGhostEdible(g)) {
				edibles[i]= game.getGhostCurrentNodeIndex(g);
				i++;
			}
		}
		if(i==0) 
			return -1;
		return game.getClosestNodeIndexFromNodeIndex(pacman, edibles, DM.PATH);
	}
	
	//determina si se pueden comer todos los fantasmas
	private boolean areAllGhostsEdible(Game game) {
		int i=0;
		for(GHOST g: GHOST.values()) {
			if(game.isGhostEdible(g))
				i++;
		}
		
		return i==GHOST.values().length;
	}
	
	//devuelve la pill mas cercana -1 en caso de no haber
	private int getNearestPill(Game game) {
		int pacman = game.getPacmanCurrentNodeIndex();
		int[] actPills= game.getActivePillsIndices();
		return game.getClosestNodeIndexFromNodeIndex(pacman, actPills, DM.PATH);
	}
	
	//devuelve el indice de la powerpill más cercana al pacman
		private int getNearestPowerPill(Game game, int n) {
			int pacman= game.getPacmanCurrentNodeIndex();
			int nearestPill = game.getClosestNodeIndexFromNodeIndex(pacman, game.getPowerPillIndices(), DM.PATH);
			return nearestPill;
		}
		
}
