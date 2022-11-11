package es.ucm.fdi.ici.c2122.practica4.grupo03.ghosts.actions;

import java.util.Comparator;
import java.util.TreeSet;

import es.ucm.fdi.ici.c2122.practica4.grupo03.ghost.FuzzyGhostAction;
import es.ucm.fdi.ici.c2122.practica4.grupo03.utils.FuzzyMemory;
import es.ucm.fdi.ici.c2122.practica4.grupo03.utils.Pair;
import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class FStayAtDistance extends FuzzyGhostAction {

	private MOVE[] movs = { MOVE.UP, MOVE.DOWN, MOVE.LEFT, MOVE.RIGHT};
	private MOVE[] cont = { MOVE.DOWN, MOVE.UP, MOVE.RIGHT, MOVE.LEFT};
	private MOVE[] opo1 = { MOVE.LEFT, MOVE.LEFT, MOVE.UP, MOVE.UP};
	private MOVE[] opo2 = { MOVE.RIGHT, MOVE.RIGHT, MOVE.DOWN, MOVE.DOWN};
	
	public FStayAtDistance(FuzzyMemory memory, GHOST ghost) {
		super(memory, ghost);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getActionId() {
		// TODO Auto-generated method stub
		return "StayAtDistance";
	}

	@Override
	public MOVE calcularMovimiento(Game game, FuzzyMemory mem) {
		// TODO Auto-generated method stub
		if(!game.doesGhostRequireAction(getGhost())) {
			return MOVE.NEUTRAL;
		}
		
		int pmnode = (int) mem.getVar("PacmanNode");
		double actDist = mem.getVar("PacmanDistance");
		int gnode = game.getGhostCurrentNodeIndex(getGhost());
		MOVE lm = game.getGhostLastMoveMade(getGhost());
		
		int[] neigh = game.getNeighbouringNodes(gnode, lm);
		TreeSet<Pair<Integer,Double>> ts = new TreeSet<Pair<Integer,Double>>(new Comparator<Pair<Integer,Double>>(){

			@Override
			public int compare(Pair<Integer, Double> arg0, Pair<Integer, Double> arg1) {
				// TODO Auto-generated method stub
				return Double.compare(arg0.getSecond(), arg1.getSecond());
			}
			
			
		});
		
		for(int v: neigh) {
			ts.add(new Pair<Integer,Double>(v, Math.abs(
					game.getDistance(gnode, pmnode, lm, DM.PATH)) - actDist));
		}
		
		Pair<Integer,Double> p = ts.iterator().next();
		
		return game.getMoveToMakeToReachDirectNeighbour(gnode, p.getFirst());
	}

}
