package es.ucm.fdi.ici.c2122.practica4.grupo03.ghosts.actions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import es.ucm.fdi.ici.c2122.practica4.grupo03.ghost.FuzzyGhostAction;
import es.ucm.fdi.ici.c2122.practica4.grupo03.utils.FuzzyMemory;
import es.ucm.fdi.ici.c2122.practica4.grupo03.utils.Pair;
import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class FRunFromNearbyGhost extends FuzzyGhostAction {

	public FRunFromNearbyGhost(FuzzyMemory memory, GHOST ghost) {
		super(memory, ghost);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getActionId() {
		// TODO Auto-generated method stub
		return "RunFromNearbyGhost";
	}

	@Override
	public MOVE calcularMovimiento(Game game, FuzzyMemory mem) {
		// TODO Auto-generated method stub
		
		if(!game.doesGhostRequireAction(getGhost())) {
			return MOVE.NEUTRAL;
		}
		GHOST g = this.getGhost();
		GHOST[] f = GHOST.values();
		List<GHOST> lg = new ArrayList<GHOST>();
		for(GHOST ghost : f) {if(!ghost.equals(g)) {lg.add(ghost);}}
		
		int thisGhostNode = game.getGhostCurrentNodeIndex(g);
		MOVE lastMove = game.getGhostLastMoveMade(g);
		
		List<Double> ld = lg.stream().map( fant -> game.getDistance(
				thisGhostNode, 
				game.getGhostCurrentNodeIndex(fant), 
				lastMove,
				DM.PATH)).collect(Collectors.toList());
		
		List<Pair<GHOST,Double>> lpairs = new ArrayList<Pair<GHOST,Double>>();
		int i = 0;
		for(GHOST fantasma: lg) {
			lpairs.add(new Pair<GHOST,Double>(fantasma, ld.get(i)));
			i++;
		}
		
		lpairs.sort(new Comparator<Pair<GHOST,Double>>(){

			@Override
			public int compare(Pair<GHOST, Double> o1, Pair<GHOST, Double> o2) {
				// TODO Auto-generated method stub
				return Double.compare(o1.getSecond(), o2.getSecond());
			}
			
		});
		
		
		return game.getNextMoveAwayFromTarget(
				thisGhostNode, 
				game.getGhostCurrentNodeIndex(lpairs.get(0).getFirst()),
				lastMove,
				DM.PATH);
	}

}
