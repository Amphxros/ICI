package es.ucm.fdi.ici.c2122.practica4.grupo03.ghost;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Vector;

import es.ucm.fdi.ici.Input;
import es.ucm.fdi.ici.c2122.practica4.grupo03.utils.Func;
import es.ucm.fdi.ici.c2122.practica4.grupo03.utils.Pair;
import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class GhostInput extends Input {
	
	private static final String[] variables = {
			"PacmanDistance",
			"PacmanToPillDistance",
			"PacmanToClosestPPillDistance",
			"EdibleTime",
			"Edible",
			"DistanceToClosestPPill",
			"NearestGhostDistance",
			"PacmanNode",
			"ClosestPPillNode",
			"PacmanClosestPPillNode"
	};
	
	private EnumMap<GHOST, List<Pair<String,Double>>> varPorGhost;
	
	private List<Func<Game>> generarListaMetodosCalculo(GHOST g) {
		// TODO Auto-generated method stub
		List<Func<Game>> listaFunciones = new ArrayList<Func<Game>>();
		//PacmanDistance
		listaFunciones.add(new Func<Game>() {

			@Override
			public double calcular(Game arg, Vector<Double> args) {
				// TODO Auto-generated method stub
				if(arg.isNodeObservable(arg.getPacmanCurrentNodeIndex())) {
					int ghostNode = arg.getGhostCurrentNodeIndex(g);
					int pmnode = arg.getPacmanCurrentNodeIndex();
					MOVE lm = arg.getGhostLastMoveMade(g);
					return arg.getDistance(ghostNode, pmnode, lm, DM.PATH);
				}
				else {
					return -1;
				}
			}
			
		});
		
		//PacmanToPillDistance
		listaFunciones.add(new Func<Game>() {

			@Override
			public double calcular(Game arg, Vector<Double> args) {
				// TODO Auto-generated method stub
				if(arg.isNodeObservable(arg.getPacmanCurrentNodeIndex())) {
					int pmnode = arg.getPacmanCurrentNodeIndex();
					int[] pillnodes = arg.getActivePillsIndices();
					if(pillnodes.length != 0) {
						int nearestNode = arg.getClosestNodeIndexFromNodeIndex(pmnode, pillnodes, DM.PATH);
						return arg.getDistance(pmnode, nearestNode, arg.getPacmanLastMoveMade(), DM.PATH);
					}
					else {
						return Double.MAX_VALUE;
					}
				}
				else {
					return -1;
				}
			}
			
		});
		
		//PacmanToClosestPPillDistance
		listaFunciones.add(new Func<Game>() {

			@Override
			public double calcular(Game arg, Vector<Double> args) {
				// TODO Auto-generated method stub
				if(arg.isNodeObservable(arg.getPacmanCurrentNodeIndex())) {
					int pmnode = arg.getPacmanCurrentNodeIndex();
					int[] pillnodes = arg.getActivePowerPillsIndices();
					if(pillnodes.length != 0) {
						int nearestNode = arg.getClosestNodeIndexFromNodeIndex(pmnode, pillnodes, DM.PATH);
						return arg.getDistance(pmnode, nearestNode, arg.getPacmanLastMoveMade(), DM.PATH);
					}
					else {
						return Double.MAX_VALUE;
					}
				}
				else {
					return -1;
				}
			}
			
		});
		
		//EdibleTime
		listaFunciones.add(new Func<Game>() {

			@Override
			public double calcular(Game arg, Vector<Double> args) {
				// TODO Auto-generated method stub
				return arg.getGhostEdibleTime(g);
			}
			
		});
		
		//Edible
		listaFunciones.add(new Func<Game>() {

			@Override
			public double calcular(Game arg, Vector<Double> args) {
				// TODO Auto-generated method stub
				double ret = 
					(arg.getGhostEdibleTime(g) > 0)? 1 : 0;
				
				return ret;
			}
			
		});
		
		//DistanceToClosestPPill
		listaFunciones.add(new Func<Game>() {

			@Override
			public double calcular(Game arg, Vector<Double> args) {
				// TODO Auto-generated method stub
				int gnode = arg.getGhostCurrentNodeIndex(g);
				int[] pillnodes = arg.getActivePowerPillsIndices();
				if(pillnodes.length != 0) {
					int nearestNode = arg.getClosestNodeIndexFromNodeIndex(gnode, pillnodes, DM.PATH);
					if(arg.getPacmanLastMoveMade() != null) {
						return arg.getDistance(gnode, nearestNode, arg.getPacmanLastMoveMade(), DM.PATH);
					}
					else {
						return Double.MAX_VALUE;
					}
				}
				else {
					return Double.MAX_VALUE;
				}
				
			}
			
		});
		
		//NearestGhostDistance
		listaFunciones.add(new Func<Game>() {

			@Override
			public double calcular(Game arg, Vector<Double> args) {
				// TODO Auto-generated method stub
				int[] ghostNodes = new int[3];
				int i = 0;
				for(GHOST ghost : GHOST.values()) {
					if(!ghost.equals(g)) {
						ghostNodes[i] = arg.getGhostCurrentNodeIndex(ghost);
					}
				}
				
				int gn = arg.getGhostCurrentNodeIndex(g);
				
				return arg.getClosestNodeIndexFromNodeIndex(gn, ghostNodes, DM.PATH);
			}
			
		});
		
		//PacmanNode
		listaFunciones.add(new Func<Game>() {

			@Override
			public double calcular(Game arg, Vector<Double> args) {
				// TODO Auto-generated method stub
				if(arg.isNodeObservable(arg.getPacmanCurrentNodeIndex())) {
					return arg.getPacmanCurrentNodeIndex();
				}
				else {
					return -1;
				}
			}
			
		});
		
		//ClosestPPillNode
		listaFunciones.add(new Func<Game>() {

			@Override
			public double calcular(Game arg, Vector<Double> args) {
				// TODO Auto-generated method stub
				int[] ppills = arg.getActivePowerPillsIndices();
				int gnode = arg.getGhostCurrentNodeIndex(g);
				if(ppills.length == 0) {
					return -1;
				}
				else {
					return arg.getClosestNodeIndexFromNodeIndex(gnode, ppills, DM.PATH);
				}
			}
			
		});
		
		//PacmanClosestPPillNode
		listaFunciones.add(new Func<Game>() {

			@Override
			public double calcular(Game arg, Vector<Double> args) {
				// TODO Auto-generated method stub
				if(arg.isNodeObservable(arg.getPacmanCurrentNodeIndex())) {
					int[] ppills = arg.getActivePowerPillsIndices();
					if(ppills.length != 0) {
						return ppills[0];
					}
					else {
						return -1;
					}
				}
				else {
					return -1;
				}
			}
			
		});
		
		return listaFunciones;
	}

	public GhostInput(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void parseInput() {
		// TODO Auto-generated method stub
		varPorGhost = new EnumMap<GHOST, List<Pair<String,Double>>>(GHOST.class);
		for(GHOST g : GHOST.values()) {
			List<Pair<String,Double>> l = new ArrayList<Pair<String,Double>>();
			List<Func<Game>> lf = generarListaMetodosCalculo(g);
			int i = 0;
			for(String s : variables) {
				l.add(new Pair<String,Double>(s,lf.get(i).calcular(getGame(), null)));
				i++;
			}
			varPorGhost.put(g, l);
		}
	}
	
	public List<Pair<String,Double>> getVarsOfGhost(GHOST g){
		return varPorGhost.get(g);
	}

}
