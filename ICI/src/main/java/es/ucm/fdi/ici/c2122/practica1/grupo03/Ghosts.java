package es.ucm.fdi.ici.c2122.practica1.grupo03;

import java.util.EnumMap;

import pacman.controllers.GhostController;
import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public final class Ghosts extends GhostController {

	private EnumMap<GHOST, MOVE> moves = new EnumMap<GHOST, MOVE>(GHOST.class);

	@Override
	public EnumMap<GHOST, MOVE> getMove(Game game, long timeDue) {

		moves.clear();
		for (GHOST ghostType : GHOST.values()) {
			
			if (game.doesGhostRequireAction(ghostType)) {
				int pacman = game.getPacmanCurrentNodeIndex();
				
				int nearestPpill = game.getClosestNodeIndexFromNodeIndex(pacman, game.getActivePowerPillsIndices(), DM.PATH);
				
				if (isEdible(game, ghostType) || (nearestPpill != -1 && game.getDistance(pacman, nearestPpill, DM.PATH) < 15)) {
					huir(game, ghostType);
				}
				else{
					if (ghostType.equals(GHOST.BLINKY)){
						BLINKY(game, ghostType);
					}
					else{
						SUE(game, ghostType);
					}
				}
			}
			
		}
		return moves;
	}
	
	
	
	
	
	private void huir(Game game, GHOST ghostType) {
		// para huir de pacman (ya que se usa mucho)
		
		moves.put(ghostType, game.getApproximateNextMoveAwayFromTarget(game.getGhostCurrentNodeIndex(ghostType), game.getPacmanCurrentNodeIndex(), game.getGhostLastMoveMade(ghostType), DM.PATH));
	}
	

	private void BLINKY(Game game, GHOST ghostType) {
		// Blinky va a ir a la pill mas cercana al Pacman 
		// Si es powerpill , va a mirar si esta mas cerca que el Pacman de la powerpill. Si esta mas cerca que el pacman de la powerpill
		//    ira a por la powerpill. Si no, huira
		
		int pacman = game.getPacmanCurrentNodeIndex();
		int nearestPill
		= game.getClosestNodeIndexFromNodeIndex(pacman, game.getActivePillsIndices(), DM.PATH);
		int[] powerPills = game.getPowerPillIndices();
		
		// Mirar si la pill mas cercana es PowerPill o no
		boolean isPowerPill = false;
		int i = 0;
		while(i < powerPills.length && !isPowerPill) {
			isPowerPill = nearestPill == powerPills[i];
			i++;
		}
		
		int ghost = game.getGhostCurrentNodeIndex(ghostType);
		
		// Si la pill mas cercana al pacman es una PowerPill
		// calcula la distancia que separa al Pacman y a Blinky de la powerpill correspondiente
		if(isPowerPill) {
			int distGhost = game.getShortestPathDistance(ghost, nearestPill, game.getGhostLastMoveMade(ghostType));
			int distPacman = game.getShortestPathDistance(pacman, nearestPill, game.getPacmanLastMoveMade());
			// Si Blinky esta mas cerca que el pacman, va a ir a taparle la powerPill
			if(distGhost < distPacman) {
				moves.put(ghostType, game.getNextMoveTowardsTarget(ghost, nearestPill, DM.PATH));
			}
			// Si el pacman esta mas cerca, Blinky huye para que no se lo coman
			else {
				huir(game, ghostType);
			}
		}
		// Si la pill mas cercana al pacman no es una powerPill
		// Blinky va a ir a taparla
		else{
			moves.put(ghostType, game.getNextMoveTowardsTarget(ghost, nearestPill, game.getGhostLastMoveMade(ghostType), DM.PATH));
		}
		
	}
	
	
	private void SUE(Game game, GHOST ghostType){
		// Va a donde supone que pacman se dirije para intentar cortarle el camino intentando predecir el camino que este tomará
		
		int pacman = game.getPacmanCurrentNodeIndex(); // La posicion actual de MsPacman
		MOVE GHOSTlastmove = game.getGhostLastMoveMade(ghostType); //El ultimo movimineto de este fantasma
		MOVE PACMANlastmove = game.getPacmanLastMoveMade(); //El ultimo movimineto de MsPacman
				
		int limit = 25; // limite en el que supongo que pacman huira de un fantasma
		int PpillLimit = 62; // limite en el que supongo pacman ira a comer una pill
		int distance = 15; // se dirijira a la casilla a la que se dirije pacman en *distance* movimientos
				
		GHOST nearestGhost = getnearestChasingGhost(game, limit); // Fantasmas a menos de limit distancia de Mspacman 
				
		if(nearestGhost != null) { // Si algun fantasma lo persigue supongo que MsPacman intentara huir y lo interceptará
			
			// intenta adivinar el siguiente movimiento de pacman
			MOVE nextmove = game.getApproximateNextMoveAwayFromTarget(pacman, game.getGhostCurrentNodeIndex(nearestGhost), PACMANlastmove, DM.MANHATTAN);
			// El fantasma se dirijirá hacia pacman intentando ir hasta la posición en la que Mspacman estará si huye de otro fantasma que la persigue
			int[] path = game.getShortestPath(pacman, game.getGhostCurrentNodeIndex(ghostType), nextmove);
			// Si esta mas cerca del fantasma que el limite de 25 propuesto ponemos el maximo camino para evitar index out of bound
			if(path.length <= distance) {
				distance = path.length - 1;
			}
			moves.put(ghostType, game.getApproximateNextMoveTowardsTarget(game.getGhostCurrentNodeIndex(ghostType), path[distance], GHOSTlastmove, DM.MANHATTAN));
		}
		else { // Si no persiguen a pacman supongo que se dirije a una PowerPill o por una Pill
			
			int closestPpill = game.getClosestNodeIndexFromNodeIndex(pacman, game.getActivePowerPillsIndices(), DM.MANHATTAN);
			int closestPill = game.getClosestNodeIndexFromNodeIndex(pacman, game.getActivePillsIndices(), DM.MANHATTAN);
					
			// si hay almenos una PowerPill y no esta muy lejos de ésta (PpillLimit) supongo que se dirijirá a dicha PowerPill
			if(game.getNumberOfActivePowerPills() > 0 && game.getShortestPathDistance(pacman, closestPpill, PACMANlastmove) < PpillLimit) {
				int[] path = game.getShortestPath(pacman, closestPpill, PACMANlastmove);
				if(path.length <= distance) {
					distance = path.length - 1;
				}
				moves.put(ghostType, game.getApproximateNextMoveTowardsTarget(game.getGhostCurrentNodeIndex(ghostType), path[distance], GHOSTlastmove, DM.MANHATTAN));
			}
			else { //Si no hay PowerPills Pacman irá a la Pill
				int[] path = game.getShortestPath(pacman, closestPill, PACMANlastmove);
				if(path.length <= distance) {
					distance = path.length - 1;
				}
				moves.put(ghostType, game.getApproximateNextMoveTowardsTarget(game.getGhostCurrentNodeIndex(ghostType), path[distance], GHOSTlastmove, DM.MANHATTAN));
			}
		}
	}
	
	
	
	
	

	public GHOST getnearestChasingGhost(Game game, int limit) {
		
		GHOST nearest = null;
		double minDistance = limit;
		
		for (GHOST g : GHOST.values()) {
			int ghost = game.getGhostCurrentNodeIndex(g);
			double distance = game.getDistance(game.getPacmanCurrentNodeIndex(), ghost, DM.MANHATTAN);
			
			if (game.getGhostLairTime(g) <= 0 && !isEdible(game, g) && distance <= minDistance) {
				minDistance = distance;
				nearest = g;
			}
		}
		
		return nearest;
		
	}
	
	
	
	
	

	public boolean isEdible(Game game, GHOST ghostType){
		return game.getGhostEdibleTime(ghostType) > 0;
	}
}