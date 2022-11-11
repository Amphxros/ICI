package es.ucm.fdi.ici.c2122.practica2.grupo03.ghosts;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import es.ucm.fdi.ici.Input;
import es.ucm.fdi.ici.c2122.practica2.grupo03.utils.Pair;
import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Game;

public class GhostsInput extends Input {

	private boolean BLINKYedible;
	private boolean INKYedible;
	private boolean PINKYedible;
	private boolean SUEedible;
	
	private double BLINKYdistance;
	private double INKYdistance;
	private double PINKYdistance;
	private double SUEdistance;

	private double timeBLINKYedible;
	private double timeINKYedible;
	private double timePINKYedible;
	private double timeSUEedible;
	
	private double minDistancePacmanGhost;
	private GHOST closestGhost;
	private GHOST secondclosestGhost;
	private GHOST thirdclosestGhost;
	
	private double minPacmanDistancePPill;
	
	public GhostsInput(Game game) {
		super(game);
	}

	@Override
	public void parseInput() {
		this.BLINKYedible = game.isGhostEdible(GHOST.BLINKY);
		this.INKYedible = game.isGhostEdible(GHOST.INKY);
		this.PINKYedible = game.isGhostEdible(GHOST.PINKY);
		this.SUEedible = game.isGhostEdible(GHOST.SUE);
		
		this.timeBLINKYedible = game.getGhostEdibleTime(GHOST.BLINKY);
		this.timeINKYedible = game.getGhostEdibleTime(GHOST.INKY);
		this.timePINKYedible = game.getGhostEdibleTime(GHOST.PINKY);
		this.timeSUEedible = game.getGhostEdibleTime(GHOST.SUE);
	
		int pacman = game.getPacmanCurrentNodeIndex();
		
		List<Pair<GHOST,Double>> arDist = new ArrayList<Pair<GHOST,Double>>();
		this.minDistancePacmanGhost = Double.MAX_VALUE;
		this.closestGhost = null;
		for(GHOST g : GHOST.values()) {
			int ghost = game.getGhostCurrentNodeIndex(g);
			double distanceG = game.getDistance(pacman, ghost, DM.PATH);
			
			switch(g) {
			case BLINKY:
				this.BLINKYdistance = distanceG;
			case INKY:
				this.INKYdistance = distanceG;
			case PINKY:
				this.PINKYdistance = distanceG;
			case SUE:
				this.SUEdistance = distanceG;
			}
			
			if(game.getGhostEdibleTime(g) > 0) {
				distanceG = Double.MAX_VALUE;
			}
			
			arDist.add(new Pair<GHOST,Double>(g, distanceG));
		}
		
		
	
		arDist.sort(new Comparator<Pair<GHOST, Double>>(){
			@Override
			public int compare(Pair<GHOST, Double> o1, Pair<GHOST, Double> o2) {
				// TODO Auto-generated method stub
				return o1.getSecond().compareTo(o2.getSecond());
			}	
		});
		
		this.closestGhost = arDist.get(0).getFirst();
		this.secondclosestGhost = arDist.get(1).getFirst();
		this.thirdclosestGhost = arDist.get(2).getFirst();
		
		this.minPacmanDistancePPill = Double.MAX_VALUE;
		for(int ppill: game.getPowerPillIndices()) {
			double distanceP = game.getDistance(pacman, ppill, DM.PATH);
			this.minPacmanDistancePPill = Math.min(distanceP, this.minPacmanDistancePPill);
		}
		
		
	}

	public double getBLINKYdistance() {
		return BLINKYdistance;
	}

	public double getINKYdistance() {
		return INKYdistance;
	}

	public double getPINKYdistance() {
		return PINKYdistance;
	}

	public double getSUEdistance() {
		return SUEdistance;
	}

	public boolean isBLINKYedible() {
		return BLINKYedible;
	}

	public boolean isINKYedible() {
		return INKYedible;
	}

	public boolean isPINKYedible() {
		return PINKYedible;
	}

	public boolean isSUEedible() {
		return SUEedible;
	}
	
	public double getminDistancePacmanGhost() {
		return minDistancePacmanGhost;
	}
	
	public GHOST getclosestGhost() {
		return closestGhost;
	}

	public GHOST getsecondclosestGhost() {
		return secondclosestGhost;
	}

	public GHOST getthirdclosestGhost() {
		return thirdclosestGhost;
	}
	
	public double getMinPacmanDistancePPill() {
		return minPacmanDistancePPill;
	}
	
	public double getTimeBLINKYedible() {
		return timeBLINKYedible;
	}

	public double getTimeINKYedible() {
		return timeINKYedible;
	}

	public double getTimePINKYedible() {
		return timePINKYedible;
	}

	public double getTimeSUEedible() {
		return timeSUEedible;
	}
	
}
