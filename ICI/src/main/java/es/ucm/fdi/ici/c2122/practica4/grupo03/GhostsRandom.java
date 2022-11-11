package es.ucm.fdi.ici.c2122.practica4.grupo03;


import java.util.EnumMap;
import java.util.Random;

import pacman.controllers.GhostController;
import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

/*
 * The Class RandomGhosts.
 */
public final class GhostsRandom extends GhostController {
    
	private EnumMap<GHOST, MOVE> moves = new EnumMap<GHOST, MOVE>(GHOST.class);
    private MOVE[] allMoves = MOVE.values();
    private Random rnd = new Random();
    //private Memory()......
    
    @Override
    public EnumMap<GHOST, MOVE> getMove(Game game, long timeDue) {
        moves.clear();
        for (GHOST ghostType : GHOST.values()) {
            if (game.doesGhostRequireAction(ghostType)) {
            	System.out.print(game.getNextMoveAwayFromTarget(game.getPacmanCurrentNodeIndex(),492, DM.EUCLID));
        
                moves.put(ghostType, allMoves[rnd.nextInt(allMoves.length)]);
            }
        }
        return moves;
    }
}