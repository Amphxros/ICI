package es.ucm.fdi.ici.c2122.practica4.grupo03.ghosts.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import es.ucm.fdi.ici.c2122.practica4.grupo03.ghost.FuzzyGhostAction;
import es.ucm.fdi.ici.c2122.practica4.grupo03.utils.FuzzyMemory;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class FRandomMove extends FuzzyGhostAction {

	public FRandomMove(FuzzyMemory memory, GHOST ghost) {
		super(memory, ghost);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getActionId() {
		// TODO Auto-generated method stub
		return "RandomMove";
	}

	@Override
	public MOVE calcularMovimiento(Game game, FuzzyMemory mem) {
		// TODO Auto-generated method stub
		if(!game.doesGhostRequireAction(getGhost())) {
			return MOVE.NEUTRAL;
		}
		List<MOVE> listm = new ArrayList<MOVE>();
		MOVE lastm = game.getGhostLastMoveMade(getGhost());
		for(MOVE m : MOVE.values()) {
			if(!lastm.equals(m)) {
				listm.add(m);				
			}
		}
		
		int r = new Random().nextInt(listm.size());
		
		return listm.get(r);
		
	}

}
