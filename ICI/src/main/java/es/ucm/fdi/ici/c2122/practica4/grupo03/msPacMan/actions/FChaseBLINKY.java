package es.ucm.fdi.ici.c2122.practica4.grupo03.msPacMan.actions;

import es.ucm.fdi.ici.c2122.practica4.grupo03.msPacMan.MsPacmanAction;
import es.ucm.fdi.ici.c2122.practica4.grupo03.utils.FuzzyMemory;
import pacman.game.Constants.DM;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class FChaseBLINKY extends MsPacmanAction {

	public FChaseBLINKY(FuzzyMemory memory) {
		super(memory);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getActionId() {
		return "ChaseBLINKY";
	}

	@Override
	public MOVE calcularMovimiento(Game game, FuzzyMemory mem) {
		int dist = (int) mem.getVar("BLINKYNode");
		
		if(dist == 0 || dist == -1) {
			return MOVE.NEUTRAL;
		}

		int pnode = game.getPacmanCurrentNodeIndex();
		MOVE lm   = game.getPacmanLastMoveMade();
		
		return game.getNextMoveTowardsTarget(pnode, dist, lm, DM.PATH);
	}

}
