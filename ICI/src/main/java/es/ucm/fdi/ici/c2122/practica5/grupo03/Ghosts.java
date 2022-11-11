package es.ucm.fdi.ici.c2122.practica5.grupo03;

import java.util.EnumMap;
import java.util.Vector;

import es.ucm.fdi.gaia.jcolibri.exception.ExecutionException;
import es.ucm.fdi.ici.c2122.practica5.grupo03.ghosts.GhostsCBRengine;
import es.ucm.fdi.ici.c2122.practica5.grupo03.ghosts.GhostsInput;
import es.ucm.fdi.ici.c2122.practica5.grupo03.ghosts.GhostsStorageManager;
import pacman.controllers.GhostController;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class Ghosts extends GhostController {

	GhostsCBRengine cbrEngine;
	GhostsStorageManager storageManagerGeneric;
	GhostsStorageManager storageManagerSpecific;
	
	static final double[] vecG = {0.028739458957207042, 0.040642939389918066, 0.04296555978206876, 0.05096560849232698, 0.051630982699451156, 0.05182661593090057, 0.0560021254223232, 0.05910050363206944, 0.06050115091428108, 0.08034750694811042, 0.08220904631029165, 0.08715522490764444};
		
	public Ghosts()
	{	
		Vector<Double> pesos = new Vector<Double>(12);
        
        for(Double d: vecG) {
        	pesos.add(d);
        }
		
		this.storageManagerGeneric = new GhostsStorageManager(100);
		this.storageManagerSpecific = new GhostsStorageManager(100);
		cbrEngine = new GhostsCBRengine(storageManagerGeneric, storageManagerSpecific, pesos);
	}
	
	@Override
	public void preCompute(String opponent) {
		cbrEngine.setOpponent(opponent); // bases de casos especializadas 
		try {
			cbrEngine.configure();
			cbrEngine.preCycle();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void postCompute() {
		try {
			cbrEngine.postCycle();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public EnumMap<GHOST, MOVE> getMove(Game game, long timeDue) {
		
		//This implementation only computes a new action when Ghosts is in a junction. 
		//This is relevant for the case storage policy
		
		EnumMap<GHOST, MOVE> moves = new EnumMap<GHOST, MOVE>(GHOST.class);
		
		for(GHOST g: GHOST.values()) {
			if(!game.isJunction(game.getPacmanCurrentNodeIndex()))
				moves.put(g, MOVE.NEUTRAL);
			
			try {
				GhostsInput input = new GhostsInput(game);
				input.parseInput();
				storageManagerGeneric.setGame(game);
				storageManagerSpecific.setGame(game);
				cbrEngine.cycle(input.getQuery());
				MOVE move = cbrEngine.getSolution();
				moves.put(g, move);
				continue;
			} catch (Exception e) {
				e.printStackTrace();
			}
			moves.put(g, MOVE.NEUTRAL);
		}
		
		return moves;
		
	}

}
