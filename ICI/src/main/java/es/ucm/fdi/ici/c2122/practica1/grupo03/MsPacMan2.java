package es.ucm.fdi.ici.c2122.practica1.grupo03;

import java.util.Random;

import pacman.controllers.PacmanController;
import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class MsPacMan2 extends PacmanController {
	
	private Random rnd = new Random();
	private MOVE[] allMoves = MOVE.values();
	private GHOST[] allGhosts = GHOST.values();
	private int[] ghostNodes = new int[GHOST.values().length];
	private boolean[] ghostInLimit = new boolean[GHOST.values().length];
	private double[] distanceToPacMan = new double[GHOST.values().length];
	private boolean[] isEdibleGhost = new boolean[GHOST.values().length];
	private boolean[] isInJailGhost = new boolean[GHOST.values().length];
	private int limit = 30;
	
	@Override
	public MOVE getMove(Game game, long timeDue) {
		// La idea de este pacman es:
		//         Comer pills normalmente
		//         Si hay fantasmas en el limite, pero el pacman esta mas cerca de una power pill -> ir a por ella
		//            Al comer una power pill:
		//               Si hay fantasmas comestibles dentro del limite -> intentar comerse al fantasma mas cercano
		//               Si hay fantasmas comestibles en el limite, pero hay otro no comestible cerca -> ir a por el fantasma
		//                      que mas lejos esté del que no es comestible
		//               Si no hay fantasmas comestibles en el limite -> ir a la pill mas cercana
		//         Si hay fantasmas en el limite, pero el pacman esta mas lejos de una power pill -> huir
		
		// Sacar nodo del pacman
		int pacManNode = game.getPacmanCurrentNodeIndex();
		
		// Sacar pill mas cercana
		int[] activePillsIndices = game.getActivePillsIndices();
		int closestPillIndex = game.getClosestNodeIndexFromNodeIndex(pacManNode, activePillsIndices, DM.PATH);
		
		// Sacar powerPill mas cercana
		int[] activePowerPillIndices = game.getActivePowerPillsIndices();
		int closestPowerPillIndex = game.getClosestNodeIndexFromNodeIndex(pacManNode, activePowerPillIndices, DM.PATH);
		
		// Sacar posiciones de todos los fantasmas y ver si alguno esta en el limite
		boolean enLimite = false;
		boolean anyGhostEdible = false;
		boolean anyGhostEdibleInLimit = false;
		boolean anyGhostNotEdibleInLimit = false;
		GHOST closestToPacMan = GHOST.BLINKY;
		double closestGhostToPacMan = Integer.MAX_VALUE;
		
		for(GHOST g : allGhosts) {
			ghostNodes[g.ordinal()] = game.getGhostCurrentNodeIndex(g);
			distanceToPacMan[g.ordinal()] = game.getDistance(ghostNodes[g.ordinal()], pacManNode , game.getGhostLastMoveMade(g), DM.PATH);
			ghostInLimit[g.ordinal()] = distanceToPacMan[g.ordinal()] <= limit;
			isEdibleGhost[g.ordinal()] = game.isGhostEdible(g);
			isInJailGhost[g.ordinal()] = game.getGhostLairTime(g) > 0;
			enLimite = enLimite || ( ghostInLimit[g.ordinal()] && !isInJailGhost[g.ordinal()]);
			anyGhostEdible = anyGhostEdible || isEdibleGhost[g.ordinal()];
			anyGhostNotEdibleInLimit = (!isEdibleGhost[g.ordinal()]) && (distanceToPacMan[g.ordinal()] <= limit);
			anyGhostEdibleInLimit = anyGhostEdible || (isEdibleGhost[g.ordinal()] && (distanceToPacMan[g.ordinal()] <= limit));
			if(game.getDistance(ghostNodes[g.ordinal()], pacManNode, game.getGhostLastMoveMade(g), DM.PATH) < closestGhostToPacMan) {
				closestToPacMan = g;
				closestGhostToPacMan = game.getDistance(ghostNodes[g.ordinal()], pacManNode, game.getGhostLastMoveMade(g), DM.PATH);
			}
		}
		
		if(!enLimite) {
			return game.getNextMoveTowardsTarget(pacManNode, closestPillIndex, game.getPacmanLastMoveMade(), DM.PATH);
		}
		else {
			if(anyGhostEdibleInLimit) {
				if(anyGhostNotEdibleInLimit) {
					double largestDistance = 0;
					GHOST furthestGhost = GHOST.BLINKY;
					for(GHOST g : GHOST.values()) {
						if(isEdibleGhost[g.ordinal()] && distanceToPacMan[g.ordinal()] > largestDistance) {
							largestDistance = distanceToPacMan[g.ordinal()];
							furthestGhost = g;
						}
					}
					
					return game.getNextMoveTowardsTarget(pacManNode, game.getGhostCurrentNodeIndex(furthestGhost), game.getPacmanLastMoveMade(),
							DM.PATH);
				}
				else {
					double closestDistance = Integer.MAX_VALUE;
					GHOST nearestGhost = GHOST.BLINKY;
					for(GHOST g : GHOST.values()) {
						if(isEdibleGhost[g.ordinal()] && ghostInLimit[g.ordinal()] && distanceToPacMan[g.ordinal()] < closestDistance) {
							closestDistance = Math.min(closestDistance, distanceToPacMan[g.ordinal()]);
							nearestGhost = g;
						}
					}
					return game.getNextMoveTowardsTarget(pacManNode, game.getGhostCurrentNodeIndex(nearestGhost), game.getPacmanLastMoveMade(),
							DM.PATH);
				}
			}
			else {
				double closestGhostToClosestPowerPill = game.getDistance(game.getGhostCurrentNodeIndex(closestToPacMan), closestPillIndex,
						game.getGhostLastMoveMade(closestToPacMan), DM.PATH);
				double pacManToClosestPowerPill = game.getDistance(pacManNode, closestPillIndex, game.getPacmanLastMoveMade(), DM.PATH);
				
				if(pacManToClosestPowerPill < closestGhostToClosestPowerPill) {
					return game.getNextMoveTowardsTarget(pacManNode, closestPowerPillIndex, game.getPacmanLastMoveMade(), DM.PATH);
				}
				else {
					return game.getNextMoveAwayFromTarget(pacManNode, game.getGhostCurrentNodeIndex(closestToPacMan), game.getPacmanLastMoveMade(),
							DM.MANHATTAN);
				}
			}
			
		}
		
		
		
	}

}
