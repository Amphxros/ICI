package es.ucm.fdi.ici.c2122.practica2.grupo03.ghosts.transitions;

import es.ucm.fdi.ici.Input;
import es.ucm.fdi.ici.c2122.practica2.grupo03.ghosts.GhostsInput;
import es.ucm.fdi.ici.fsm.Transition;
import pacman.game.Constants.GHOST;

public class PacManNoTimetoEatMe1 implements Transition {
	
	private GHOST ghost;
	private int limit = 55;

	public PacManNoTimetoEatMe1(GHOST ghost) {
		super();
		this.ghost = ghost;
	}


	@Override
	public boolean evaluate(Input in) {
		GhostsInput input = (GhostsInput)in;
		switch(ghost) {
			case BLINKY:
				return input.getTimeBLINKYedible() < input.getBLINKYdistance() + limit;
			case INKY:
				return input.getTimeINKYedible() < input.getINKYdistance() + limit;
			case PINKY:
				return input.getTimePINKYedible() < input.getPINKYdistance() + limit;
			case SUE:
				return input.getTimeSUEedible() < input.getSUEdistance() + limit;
			default:
				return false;
		}
	}


	@Override
	public String toString() {
		return "Pacman is too far to eat me 1";
	}

	
	
}
