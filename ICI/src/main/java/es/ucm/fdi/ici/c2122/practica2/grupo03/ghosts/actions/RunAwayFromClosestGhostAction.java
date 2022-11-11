package es.ucm.fdi.ici.c2122.practica2.grupo03.ghosts.actions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import es.ucm.fdi.ici.Action;
import es.ucm.fdi.ici.c2122.practica2.grupo03.utils.Pair;
import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class RunAwayFromClosestGhostAction implements Action {

    GHOST ghost;
	public RunAwayFromClosestGhostAction(GHOST ghost) {
		this.ghost = ghost;
	}

	@Override
	public MOVE execute(Game game) {
		
		List<Pair<GHOST,Integer>> gn = new ArrayList<Pair<GHOST,Integer>>();
		for(GHOST gt : GHOST.values()) {
			if(gt != ghost) {
				gn.add(new Pair<GHOST,Integer>(gt, game.getGhostCurrentNodeIndex(gt)));				
			}
		}
		
		Set<Pair<GHOST,Double>> set = new TreeSet<Pair<GHOST,Double>>(
			new Comparator<Pair<GHOST,Double>>(){

				@Override
				public int compare(Pair<GHOST, Double> o1, Pair<GHOST, Double> o2) {
					// TODO Auto-generated method stub
					if(o1.getSecond() < o2.getSecond()) { return -1; }
					else if (o1.getSecond() == o2.getSecond()) { return 0; }
					else { return 1; }
				}
				
				
			});
		
		int ghostnode = game.getGhostCurrentNodeIndex(ghost);
		
		for(Pair<GHOST,Integer> p : gn) {
			set.add(new Pair<GHOST,Double>(p.getFirst(), game.getDistance(
					ghostnode, game.getGhostCurrentNodeIndex(p.getFirst()), 
					game.getGhostLastMoveMade(p.getFirst()), DM.PATH)));
		}
		
		return game.getApproximateNextMoveAwayFromTarget(
				ghostnode, game.getGhostCurrentNodeIndex(gn.iterator().next().getFirst()), 
				game.getGhostLastMoveMade(gn.iterator().next().getFirst()), DM.PATH);
	}

	@Override
	public String getActionId() {
		return ghost+ "runsAway from closest ghost";
	}
}
