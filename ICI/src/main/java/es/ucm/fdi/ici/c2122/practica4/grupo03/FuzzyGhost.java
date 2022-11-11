package es.ucm.fdi.ici.c2122.practica4.grupo03;

import java.io.File;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;

import es.ucm.fdi.ici.Action;
import es.ucm.fdi.ici.c2122.practica4.grupo03.ghost.FuzzyGhostAction;
import es.ucm.fdi.ici.c2122.practica4.grupo03.ghost.GhostInput;
import es.ucm.fdi.ici.c2122.practica4.grupo03.ghosts.actions.FChasePacman;
import es.ucm.fdi.ici.c2122.practica4.grupo03.ghosts.actions.FGoToClosestPPill;
import es.ucm.fdi.ici.c2122.practica4.grupo03.ghosts.actions.FGoToPacManClosestPPill;
import es.ucm.fdi.ici.c2122.practica4.grupo03.ghosts.actions.FRandomMove;
import es.ucm.fdi.ici.c2122.practica4.grupo03.ghosts.actions.FRunAwayFromPacman;
import es.ucm.fdi.ici.c2122.practica4.grupo03.ghosts.actions.FRunFromNearbyGhost;
import es.ucm.fdi.ici.c2122.practica4.grupo03.ghosts.actions.FRunToQuadrant;
import es.ucm.fdi.ici.c2122.practica4.grupo03.ghosts.actions.FStayAtDistance;
import es.ucm.fdi.ici.c2122.practica4.grupo03.utils.FuncionCalcularVariable;
import es.ucm.fdi.ici.c2122.practica4.grupo03.utils.FuncionesConfianza;
import es.ucm.fdi.ici.c2122.practica4.grupo03.utils.FuzzyMemory;
import es.ucm.fdi.ici.c2122.practica4.grupo03.utils.Pair;
import es.ucm.fdi.ici.c2122.practica4.grupo03.utils.VarFunctions;
import es.ucm.fdi.ici.fuzzy.FuzzyEngine;
import es.ucm.fdi.ici.fuzzy.observers.ConsoleFuzzyEngineObserver;
import pacman.controllers.GhostController;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class FuzzyGhost extends GhostController {
	
	private static final String RULES_PATH = "target\\classes\\es"+File.separator+"ucm"+File.separator+"fdi"+File.separator+"ici"+File.separator+"c2122"+File.separator+"practica4"+File.separator+"grupo03"+File.separator+"ghost"+File.separator;
	
	private static final String[] variables = {
			"PacmanDistance",
			"PacmanToPillDistance",
			"PacmanToClosestPPillDistance",
			"EdibleTime",
			"Edible",
			"DistanceToClosestPPill",
			"NearestGhostDistance",
	//-------------------------------------
			"PacmanNode",
			"ClosestPPillNode",
			"PacmanClosestPPillNode"
	};
	
	private static final FuncionesConfianza[] funcionesConfianza = {
		// PacManDistance
			FuncionesConfianza.CONFIANZA_GAUSS,
		// PacManToPillDistance
			FuncionesConfianza.CONFIANZA_GAUSS,
		// PacmanToClosestPPillDistance
			FuncionesConfianza.CONFIANZA_GAUSS,
		// EdibleTime
			FuncionesConfianza.FULLCONFIDENCE,
		// Edible
			FuncionesConfianza.FULLCONFIDENCE,
		// DistanceToClosestPPill
			FuncionesConfianza.CONFIANZA_GAUSS,
		// NearestGhostDistance
			FuncionesConfianza.CONFIANZA_GAUSS,
	//------------------------------------------------
		// PacmanNode
			FuncionesConfianza.CONFIANZA_GAUSS,
		// ClosestPPillNode
			FuncionesConfianza.CONFIANZA_GAUSS,
		// PacmanClosestPPillNode
			FuncionesConfianza.CONFIANZA_GAUSS
	};
	
	private static final FuncionCalcularVariable[] funcionesValor = {
		// PacManDistance
			FuncionCalcularVariable.PLUSONE,
		// PacManToPill
			FuncionCalcularVariable.PLUSONE,
		// PacManToClosestPPillDistance
			FuncionCalcularVariable.MINUSONE,
		// EdibleTime
			FuncionCalcularVariable.PREVVALUE,
		// Edible
			FuncionCalcularVariable.PREVVALUE,
		// DistanceToClosestPPill
			FuncionCalcularVariable.PREVVALUE,
		// NearestGhostDistance
			FuncionCalcularVariable.PREVVALUE,
	//------------------------------------------------
		// PacmanNode
			FuncionCalcularVariable.PREVVALUE,
		// ClosestPPillNode
			FuncionCalcularVariable.PREVVALUE,
		// PacmanClosestPPillNode
			FuncionCalcularVariable.PREVVALUE
			
	};
	
	EnumMap<GHOST, FuzzyMemory> fuzzyGhostMemory;
	EnumMap<GHOST, FuzzyEngine> fuzzyGhost;
	EnumMap<GHOST, Action[]> fuzzyActions;
	
	
	public FuzzyGhost() {
		
		fuzzyGhostMemory = new EnumMap<GHOST,FuzzyMemory>(GHOST.class);
		fuzzyGhost = new EnumMap<GHOST,FuzzyEngine>(GHOST.class);
		fuzzyActions = new EnumMap<GHOST, Action[]>(GHOST.class);
		
		for(GHOST g : GHOST.values()) {
			List<Pair<String,VarFunctions>> lista = new ArrayList<Pair<String,VarFunctions>>();
			int i = 0;
			for(String s : variables) {
				System.out.println(i);
				System.out.println(funcionesValor[i].toString());
				lista.add(new Pair<String,VarFunctions>(s, new VarFunctions(funcionesConfianza[i].getFunction(), funcionesValor[i].getFuncion())));
				i++;
			}
			
			FuzzyMemory fm = new FuzzyMemory(lista);
						
			fuzzyGhostMemory.put(g, fm);
			
			fuzzyActions.put(g, generarAcciones(g));
			
			String s = g.toString().toLowerCase();
			
			FuzzyEngine fe = new FuzzyEngine(
					g.toString(), 
					RULES_PATH + g.toString().toLowerCase() + ".fcl",
					"Fuzzy" + s.substring(0, 1).toUpperCase() + s.substring(1),
					new MaxActionSelector(fuzzyActions.get(g))
			);
			
			fuzzyGhost.put(g, fe);
		}
		
	}
	
	private FuzzyGhostAction[] generarAcciones(GHOST g) {
		// TODO Auto-generated method stub
		FuzzyGhostAction[] fa = new FuzzyGhostAction[8];
		FuzzyMemory mem = fuzzyGhostMemory.get(g);
		
		fa[0] = new FChasePacman(mem,g);
		fa[1] = new FGoToClosestPPill(mem, g);
		fa[2] = new FGoToPacManClosestPPill(mem,g);
		fa[3] = new FRunAwayFromPacman(mem,g);
		fa[4] = new FRunFromNearbyGhost(mem,g);
		fa[5] = new FRunToQuadrant(mem,g);
		fa[6] = new FStayAtDistance(mem,g);
		fa[7] = new FRandomMove(mem,g);
		
		return fa;
	}

	@Override
	public EnumMap<GHOST, MOVE> getMove(Game game, long timeDue) {
		// TODO Auto-generated method stub
		EnumMap<GHOST, MOVE> mapa = new EnumMap<GHOST,MOVE>(GHOST.class);
		
		GhostInput gi = new GhostInput(game);
		
		for(GHOST g : GHOST.values()) {
			fuzzyGhostMemory.get(g).update(gi.getVarsOfGhost(g));
			HashMap<String,Double> m = fuzzyGhostMemory.get(g).getMem();
			FuzzyEngine eng = fuzzyGhost.get(g);
			mapa.put(g,eng.run(m, game));
		}
		
		return mapa;
	}

}
