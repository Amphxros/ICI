package es.ucm.fdi.ici.c2122.practica2.grupo03.ghosts.actions;

import es.ucm.fdi.ici.Action;
import es.ucm.fdi.ici.c2122.practica3.grupo03.utils.Busquedas;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class CutPathIn20MovementsAction implements Action {

	private static int distance = 20; 
	
    GHOST ghost;
	public CutPathIn20MovementsAction(GHOST ghost) {
		this.ghost = ghost;
	}

	@Override
	public MOVE execute(Game game) {
		if(game.doesGhostRequireAction(ghost)) {
			return Busquedas.moveToCutPacManPathInNMovements(game, ghost, distance);
		}
		else {
			return MOVE.NEUTRAL;
		} 
	}

	@Override
	public String getActionId() {
		return ghost+ "trying to cut pacmans path in" + distance + "movements";
	}
}
