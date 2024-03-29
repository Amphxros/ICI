package es.ucm.fdi.ici.c2122.practica2.grupo03.mspacman.actions;

import es.ucm.fdi.ici.Action;
import pacman.game.Game;
import pacman.game.Constants.DM;
import pacman.game.Constants.MOVE;

public class GoToThirdPowerPill implements Action{
	public GoToThirdPowerPill() {
	}
	
	@Override
	public MOVE execute(Game game) {
		int [] powerPills= game.getActivePowerPillsIndices();
		int pacmanNode= game.getPacmanCurrentNodeIndex();
		int closestPowerPill= game.getClosestNodeIndexFromNodeIndex(pacmanNode, powerPills, DM.PATH);
		int farthestPowerPill=game.getFarthestNodeIndexFromNodeIndex(pacmanNode, powerPills, DM.PATH);
		int third= (int)Double.MAX_VALUE;
		int second= (int)Double.MAX_VALUE;
		
		for(int i=0;i<powerPills.length; i++) {
			if(powerPills[i]!=closestPowerPill && powerPills[i]!=farthestPowerPill) {
				if(second==(int)Double.MAX_VALUE) {
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
		int secondPowerPill=second;
		int thirdPowerPill=third;
		
		if(thirdPowerPill!=-1)
			return game.getNextMoveTowardsTarget(pacmanNode, thirdPowerPill, DM.PATH);
		else
			return MOVE.NEUTRAL;
	}

	@Override
	public String getActionId() {
		// TODO Auto-generated method stub
		return null;
	}
}
