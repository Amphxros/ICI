package es.ucm.fdi.ici.c2122.practica4.grupo03.msPacMan.actions;

import es.ucm.fdi.ici.c2122.practica4.grupo03.msPacMan.MsPacmanAction;
import es.ucm.fdi.ici.c2122.practica4.grupo03.utils.FuzzyMemory;
import pacman.game.Constants.DM;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class FGoToPill extends MsPacmanAction {

	public FGoToPill(FuzzyMemory memory) {
		super(memory);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getActionId() {
		return "GoToPill";
	}

	@Override
	public MOVE calcularMovimiento(Game game, FuzzyMemory mem) {
		int node = (int) mem.getVar("PacmanToPillNode");

		if(node == -1) {
			return MOVE.NEUTRAL;
		}
		
		int pnode = game.getPacmanCurrentNodeIndex();
		MOVE lm   = game.getPacmanLastMoveMade();
		
		return game.getNextMoveTowardsTarget(pnode, node, lm, DM.PATH);
	}

}
