package es.ucm.fdi.ici.c2122.practica4.grupo03;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import es.ucm.fdi.ici.c2122.practica4.grupo03.msPacMan.MsPacmanInput;
import es.ucm.fdi.ici.c2122.practica4.grupo03.msPacMan.actions.FChaseBLINKY;
import es.ucm.fdi.ici.c2122.practica4.grupo03.msPacMan.actions.FChaseINKY;
import es.ucm.fdi.ici.c2122.practica4.grupo03.msPacMan.actions.FChasePINKY;
import es.ucm.fdi.ici.c2122.practica4.grupo03.msPacMan.actions.FChaseSUE;
import es.ucm.fdi.ici.c2122.practica4.grupo03.msPacMan.actions.FGoToPPill;
import es.ucm.fdi.ici.c2122.practica4.grupo03.msPacMan.actions.FGoToPill;
import es.ucm.fdi.ici.c2122.practica4.grupo03.msPacMan.actions.FRunawayFromBLINKY;
import es.ucm.fdi.ici.c2122.practica4.grupo03.msPacMan.actions.FRunawayFromINKY;
import es.ucm.fdi.ici.c2122.practica4.grupo03.msPacMan.actions.FRunawayFromPINKY;
import es.ucm.fdi.ici.c2122.practica4.grupo03.msPacMan.actions.FRunawayFromSUE;
import es.ucm.fdi.ici.c2122.practica4.grupo03.utils.FuncionCalcularVariable;
import es.ucm.fdi.ici.c2122.practica4.grupo03.utils.FuncionesConfianza;
import es.ucm.fdi.ici.c2122.practica4.grupo03.utils.FuzzyMemory;
import es.ucm.fdi.ici.c2122.practica4.grupo03.utils.Pair;
import es.ucm.fdi.ici.c2122.practica4.grupo03.utils.VarFunctions;
import es.ucm.fdi.ici.fuzzy.FuzzyEngine;
import es.ucm.fdi.ici.fuzzy.observers.ConsoleFuzzyEngineObserver;
import pacman.controllers.PacmanController;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public final class MsPacman extends PacmanController {
	private static final String RULES_PATH = "target\\classes\\es"+File.separator+"ucm"+File.separator+"fdi"+File.separator+"ici"+File.separator+"c2122"+File.separator+"practica4"+File.separator+"grupo03"+File.separator+"msPacMan"+File.separator;
	
	private static final String[] variables= {

			"BLINKYdistance",
			"PINKYdistance",
			"INKYdistance",
			"SUEdistance",			

			"BLINKYedible",
			"PINKYedible",
			"INKYedible",
			"SUEedible",
			
			"BLINKYedibletime",
			"PINKYedibletime",
			"INKYedibletime",
			"SUEedibletime",

			"BLINKYjail",
			"PINKYjail",
			"INKYjail",
			"SUEjail",
			
			"BLINKYjailtime",
			"PINKYjailtime",
			"INKYjailtime",
			"SUEjailtime",
			
			"MsPacmandirection",
			
			"BLINKYdirection",
			"PINKYdirection",
			"INKYdirection",
			"SUEdirection",
			
			"BLINKYposition",
			"PINKYposition",
			"INKYposition",
			"SUEposition",
			
			"PacmanToPillNode",
			"PacmanToClosestPPillNode",
			
			"BLINKYNode",
			"PINKYNode",
			"INKYNode",
			"SUENode"
			
	};
	
	private static final FuncionesConfianza[] funciones = {
		
		
		// distanceConfidence
		FuncionesConfianza.CONFIANZA_GAUSS,
		FuncionesConfianza.CONFIANZA_GAUSS,
		FuncionesConfianza.CONFIANZA_GAUSS,
		FuncionesConfianza.CONFIANZA_GAUSS,
			
		
		// EdibleConfidence
		FuncionesConfianza.FULLCONFIDENCE,
		FuncionesConfianza.FULLCONFIDENCE,
		FuncionesConfianza.FULLCONFIDENCE,
		FuncionesConfianza.FULLCONFIDENCE,
		
		// EdibletimeConfidence
		FuncionesConfianza.FULLCONFIDENCE,
		FuncionesConfianza.FULLCONFIDENCE,
		FuncionesConfianza.FULLCONFIDENCE,
		FuncionesConfianza.FULLCONFIDENCE,
			
		// Jail Confidence
		FuncionesConfianza.FULLCONFIDENCE,
		FuncionesConfianza.FULLCONFIDENCE,
		FuncionesConfianza.FULLCONFIDENCE,
		FuncionesConfianza.FULLCONFIDENCE,
			
		// JailTime Confidence
		FuncionesConfianza.FULLCONFIDENCE,
		FuncionesConfianza.FULLCONFIDENCE,
		FuncionesConfianza.FULLCONFIDENCE,
		FuncionesConfianza.FULLCONFIDENCE,
			
		
		// directionConfidence
		FuncionesConfianza.FULLCONFIDENCE,
		FuncionesConfianza.CONFIANZA_GAUSS,
		FuncionesConfianza.CONFIANZA_GAUSS,
		FuncionesConfianza.CONFIANZA_GAUSS,
		FuncionesConfianza.CONFIANZA_GAUSS,
		
		// positionConfidence
		FuncionesConfianza.CONFIANZA_GAUSS,
		FuncionesConfianza.CONFIANZA_GAUSS,
		FuncionesConfianza.CONFIANZA_GAUSS,
		FuncionesConfianza.CONFIANZA_GAUSS,
		
		//Pill node
		FuncionesConfianza.CONFIANZA_GAUSS,
		//PowerPill node
		FuncionesConfianza.CONFIANZA_GAUSS,
		
		// ghostnode
		FuncionesConfianza.CONFIANZA_GAUSS,
		FuncionesConfianza.CONFIANZA_GAUSS,
		FuncionesConfianza.CONFIANZA_GAUSS,
		FuncionesConfianza.CONFIANZA_GAUSS
		

	};
	
	private static final FuncionCalcularVariable[] funcionesValor = {
			
			//closestPill
			FuncionCalcularVariable.PREVVALUE,
			//closestPPillDistance
			FuncionCalcularVariable.PREVVALUE,
			
			
			//distance BLINKY
			FuncionCalcularVariable.PREVVALUE,
			//distance PINKY
			FuncionCalcularVariable.PREVVALUE,
			//distance INKY
			FuncionCalcularVariable.PREVVALUE,
			//distance SUE
			FuncionCalcularVariable.PREVVALUE,
			
			
			// Edible BLINKY
			FuncionCalcularVariable.PREVVALUE,
			// Edible PINKY
			FuncionCalcularVariable.PREVVALUE,
			// Edible INKY
			FuncionCalcularVariable.PREVVALUE,
			// Edible SUE
			FuncionCalcularVariable.PREVVALUE,
			
			// EdibleTime BLINKY
			FuncionCalcularVariable.PREVVALUE,
			// EdibleTime PINKY
			FuncionCalcularVariable.PREVVALUE,
			// EdibleTime INKY
			FuncionCalcularVariable.PREVVALUE,
			// EdibleTime SUE
			FuncionCalcularVariable.PREVVALUE,
			
			// Jail BLINKY
			FuncionCalcularVariable.PREVVALUE,
			// Jail PINKY
			FuncionCalcularVariable.PREVVALUE,
			// Jail INKY
			FuncionCalcularVariable.PREVVALUE,
			// Jail SUE
			FuncionCalcularVariable.PREVVALUE,
			
			// JailTime BLINKY
			FuncionCalcularVariable.PREVVALUE,
			// JailTime PINKY
			FuncionCalcularVariable.PREVVALUE,
			// JailTime INKY
			FuncionCalcularVariable.PREVVALUE,
			// JailTime SUE
			FuncionCalcularVariable.PREVVALUE,
			

			// "PacmanToClosestPPillNode",
			FuncionCalcularVariable.PREVVALUE,
			// "PacmanToPillNode",
			FuncionCalcularVariable.PREVVALUE,
			
			// node BLINKY
			FuncionCalcularVariable.PREVVALUE,
			// node PINKY
			FuncionCalcularVariable.PREVVALUE,
			// node INKY
			FuncionCalcularVariable.PREVVALUE,
			// node SUE
			FuncionCalcularVariable.PREVVALUE,
			
			// direction PacMan
			FuncionCalcularVariable.PREVVALUE,
			
			// direction BLINKY
			FuncionCalcularVariable.PREVVALUE,
			// direction PINKY
			FuncionCalcularVariable.PREVVALUE,
			// direction INKY
			FuncionCalcularVariable.PREVVALUE,
			// direction SUE
			FuncionCalcularVariable.PREVVALUE,
						
						
			// Position BLINKY
			FuncionCalcularVariable.PREVVALUE,
			// Position PINKY
			FuncionCalcularVariable.PREVVALUE,
			// Position INKY
			FuncionCalcularVariable.PREVVALUE,
			// Position SUE
			FuncionCalcularVariable.PREVVALUE,
			
			// Node Pill
			FuncionCalcularVariable.PREVVALUE,
			// Node PPill
			FuncionCalcularVariable.PREVVALUE,
									
									
			// Node BLINKY
			FuncionCalcularVariable.PREVVALUE,
			// Node PINKY
			FuncionCalcularVariable.PREVVALUE,
			// Node INKY
			FuncionCalcularVariable.PREVVALUE,
			// NodeSUE
			FuncionCalcularVariable.PREVVALUE

		};

	
	FuzzyMemory mFuzzyMemory;
	FuzzyEngine fuzzyMsPacman;
	FuzzyAction[] mfuzzyActions;
	
	public MsPacman() {
		setName("MsPacMan XX");
		
		List<Pair<String, VarFunctions>> l= new ArrayList<Pair<String, VarFunctions>>();
		int i=0;
		for(String s: variables) {
			l.add(new Pair<String, VarFunctions>(s,new VarFunctions(funciones[i].getFunction(), funcionesValor[i].getFuncion())));
			i++;
		}
		
		mFuzzyMemory= new FuzzyMemory(l);
		mfuzzyActions = generarAcciones();
		fuzzyMsPacman = new FuzzyEngine("MsPacMan", RULES_PATH + "mspacman.fcl", "FuzzyMsPacMan", new MaxActionSelector(mfuzzyActions));
	
		ConsoleFuzzyEngineObserver observer = new ConsoleFuzzyEngineObserver("MsPacMan","MsPacManRules");
		fuzzyMsPacman.addObserver(observer);
	}
	
	private FuzzyAction[] generarAcciones() {
		// TODO Auto-generated method stub
		FuzzyAction[] fa = new FuzzyAction[10];
		fa[0] = new FChaseBLINKY(mFuzzyMemory);
		fa[1] = new FChasePINKY(mFuzzyMemory);
		fa[2] = new FChaseINKY(mFuzzyMemory);
		fa[3] = new FChaseSUE(mFuzzyMemory);
		fa[4] = new FRunawayFromBLINKY(mFuzzyMemory);
		fa[5] = new FRunawayFromPINKY(mFuzzyMemory);
		fa[6] = new FRunawayFromINKY(mFuzzyMemory);
		fa[7] = new FRunawayFromSUE(mFuzzyMemory);
		fa[8] = new FGoToPill(mFuzzyMemory);
		fa[9] = new FGoToPPill(mFuzzyMemory);
		
		return fa;
	}

	@Override
	public MOVE getMove(Game game, long timeDue) {
		
		MsPacmanInput gi = new MsPacmanInput(game);
		
		mFuzzyMemory.update(gi.getVarsOfPacMan());
		HashMap<String,Double> m = mFuzzyMemory.getMem();
		
		return fuzzyMsPacman.run(m, game);
	}
}

