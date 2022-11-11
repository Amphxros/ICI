package es.ucm.fdi.ici.c2122.practica2.grupo03.mspacman;

import java.util.Collection;

import es.ucm.fdi.ici.Input;
import es.ucm.fdi.ici.rules.RulesInput;
import pacman.game.Game;
import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;

public class MsPacManInput extends Input {

	private boolean BLINKYedible;
	private boolean INKYedible;
	private boolean PINKYedible;
	private boolean SUEedible;
	
	private double closestGhostdistance;
	private double secondGhostdistance;
	private double thirdGhostdistance;
	private double farthestGhostdistance;
	
	
	private int closestPowerPill;
	private int secondPowerPill;
	private int thirdPowerPill;
	private int farthestPowerPill;

	private double closestPowerPillDistance;
	private double secondPowerPillDistance;
	private double thirdPowerPillDistance;
	private double farthestPowerPillDistance;
	
	
	private GHOST closestGhost;
	private GHOST secondGhost;
	private GHOST thirdGhost;
	private GHOST farthestGhost;
	
	private int closestGhostNode;
	private int secondGhostNode;
	private int thirdGhostNode;
	private int farthestGhostNode;
	
	public MsPacManInput(Game game) {
		super(game);
		
	}

	@Override
	public void parseInput() {
		getGhostInfo();
		getPowerPillsInfo();

	}
	
	private void getGhostInfo() {
		
		this.BLINKYedible = game.isGhostEdible(GHOST.BLINKY);
		this.INKYedible = game.isGhostEdible(GHOST.INKY);
		this.PINKYedible = game.isGhostEdible(GHOST.PINKY);
		this.SUEedible = game.isGhostEdible(GHOST.SUE);
		
		
		double dstClosest=Double.MAX_VALUE;
		double dstSecond=Double.MAX_VALUE;
		double dstThird=Double.MAX_VALUE;
		double dstFarthest=Double.MAX_VALUE;
		
		int pacmanNode= game.getPacmanCurrentNodeIndex();
		for(GHOST g: GHOST.values()) {
			double dst= game.getDistance(pacmanNode, game.getGhostCurrentNodeIndex(g), DM.PATH);
			if(dstClosest>dst) {
				dstFarthest=dstThird;
				farthestGhost=thirdGhost;
				dstThird=dstSecond;
				thirdGhost=secondGhost;
				dstSecond=dstClosest;
				secondGhost=closestGhost;
				dstClosest=dst;
				closestGhost=g;
			}
			else if(dst<dstSecond) {
				dstFarthest=dstThird;
				thirdGhost=secondGhost;
				dstThird=dstSecond;
				secondGhost=g;
				dstSecond=dst;
			}
			else if(dst<dstThird) {
				dstFarthest=dstThird;
				farthestGhost=thirdGhost;
				dstThird=dst;
				thirdGhost=g;
			}
			else {
				dstFarthest=dst;
				farthestGhost=g;
			}
			
		}
		this.closestGhostdistance=dstClosest;
		this.secondGhostdistance=dstSecond;
		this.thirdGhostdistance=dstThird;
		this.farthestGhostdistance=dstFarthest;

		this.closestGhostNode=game.getGhostCurrentNodeIndex(closestGhost);
		this.secondGhostNode=game.getGhostCurrentNodeIndex(secondGhost);
		this.thirdGhostNode=game.getGhostCurrentNodeIndex(thirdGhost);
		//this.farthestGhostNode=game.getGhostCurrentNodeIndex(farthestGhost);
				
	}
	
	private void getPowerPillsInfo() {
		int [] powerPills= game.getActivePowerPillsIndices();
		if(powerPills.length>0) {
		int pacmanNode= game.getPacmanCurrentNodeIndex();
		closestPowerPill= game.getClosestNodeIndexFromNodeIndex(pacmanNode, powerPills, DM.PATH);
		farthestPowerPill=game.getFarthestNodeIndexFromNodeIndex(pacmanNode, powerPills, DM.PATH);
		int third= -1;
		int second= -1;
		
		if(powerPills.length>2) {
		for(int i=0;i<powerPills.length; i++) {
			if(powerPills[i]!=closestPowerPill && powerPills[i]!=farthestPowerPill) {
				if(second==-1) {
					second=powerPills[i];
				}
				else
					third=powerPills[i];
			}
		}
		
		if(game.getDistance(pacmanNode, second, DM.PATH) > game.getDistance(pacmanNode, third, DM.PATH)) {
			int aux= second;
			second=third;
			third=aux;
		}
		
		secondPowerPill=second;
		thirdPowerPill=third;
		this.secondPowerPillDistance = game.getDistance(pacmanNode, secondPowerPill,DM.PATH );
		this.thirdPowerPillDistance = game.getDistance(pacmanNode, thirdPowerPill,DM.PATH );
		}
		this.closestPowerPillDistance = game.getDistance(pacmanNode, closestPowerPill,DM.PATH );
		this.farthestPowerPillDistance = game.getDistance(pacmanNode, farthestPowerPill,DM.PATH );
		}
	}
	
	//		PPILLS INFO
	public int getClosestPPill() {return closestPowerPill; }
	public int getSecondPPill() {return secondPowerPill;}
	public int getThirdPPill() {return thirdPowerPill;}
	public int getFarthestPPill() {return farthestPowerPill; }
	
	public boolean thereareNoPPills() {
		return  closestPowerPill ==-1 && 
				secondPowerPill ==-1  && 
				thirdPowerPill ==-1   && 
				farthestPowerPill==-1;
	}
	
	public double getClosestPPillDistance() {return closestPowerPillDistance;}
	public double getSecondPPillDistance() {return secondPowerPillDistance;}
	public double getThirdPPillDistance() {return thirdPowerPillDistance;}
	public double getFarthestPPillDistance() {return farthestPowerPillDistance;}

	
	///		GHOSTS INFO
	
	public boolean areAllGhostsEdible() {
		return BLINKYedible && SUEedible && INKYedible && PINKYedible;
	}
	
	
	public double getDistanceFromClosestGhost() {return closestGhostdistance;}
	public double getDistanceFromSecondGhost() {return secondGhostdistance;}
	public double getDistanceFromThirdGhost() {return thirdGhostdistance;}
	public double getDistanceFromFarthestGhost() {return farthestGhostdistance;}
	
	public boolean isClosestGhostEdible() {return game.isGhostEdible(closestGhost);}
	public boolean isSecondGhostEdible() {return game.isGhostEdible(secondGhost);}
	public boolean isThirdGhostEdible() {return game.isGhostEdible(thirdGhost);}
	
	public double getEatingTimeFromClosestGhost() {return game.getGhostEdibleTime(closestGhost);}
	public double getEatingTimeFromSecondGhost() {return game.getGhostEdibleTime(secondGhost);}
	public double getEatingTimeFromThirdGhost() {return game.getGhostEdibleTime(thirdGhost);}
	public double getEatingTimeFromFarthestGhost() {return game.getGhostEdibleTime(farthestGhost);}
	
	public boolean getBLINKYEdible() {return BLINKYedible;}
	public boolean getINKYEdible() {return INKYedible;}
	public boolean getPINKYEdible() {return PINKYedible;}	
	public boolean getSUEEdible() {return SUEedible;}
	
	public boolean isClosestClostInJail() {return game.getGhostLairTime(closestGhost)<=0;}
	public boolean isSecondClostInJail() {return game.getGhostLairTime(secondGhost)<=0;}
	public boolean isThirdClostInJail() {return game.getGhostLairTime(thirdGhost)<=0;}
	public boolean isFarthestClostInJail() {return game.getGhostLairTime(farthestGhost)<=0;}
	
	public GHOST getClosestGhost() {return closestGhost;}
	public GHOST getSecondGhost() {return secondGhost;}
	public GHOST getThirdGhost() {return thirdGhost;}
	public GHOST getFarthestGhost() {return farthestGhost;}
	
	public int getClosestGhostNode() {return closestGhostNode;}
	public int getSecondGhostNode() {return secondGhostNode;}
	public int getThirdGhostNode() {return thirdGhostNode;}
	public int getFarthestGhostNode() {return farthestGhostNode;}


	
}
