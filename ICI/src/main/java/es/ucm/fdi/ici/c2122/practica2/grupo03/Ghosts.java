package es.ucm.fdi.ici.c2122.practica2.grupo03;

import java.awt.BorderLayout;
import java.util.EnumMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

import es.ucm.fdi.ici.c2122.practica2.grupo03.ghosts.GhostsInput;
import es.ucm.fdi.ici.c2122.practica2.grupo03.ghosts.actions.BeNearbyPacmanAction;
import es.ucm.fdi.ici.c2122.practica2.grupo03.ghosts.actions.ChasePacmanAction;
import es.ucm.fdi.ici.c2122.practica2.grupo03.ghosts.actions.CutPathIn20MovementsAction;
import es.ucm.fdi.ici.c2122.practica2.grupo03.ghosts.actions.CutPathIn30MovementsAction;
import es.ucm.fdi.ici.c2122.practica2.grupo03.ghosts.actions.RunAwayFromClosestGhostAction;
import es.ucm.fdi.ici.c2122.practica2.grupo03.ghosts.actions.RunAwayFromPacmanAction;
import es.ucm.fdi.ici.c2122.practica2.grupo03.ghosts.transitions.ClosestGhost;
import es.ucm.fdi.ici.c2122.practica2.grupo03.ghosts.transitions.ClosestGhost2;
import es.ucm.fdi.ici.c2122.practica2.grupo03.ghosts.transitions.ClosestGhost3;
import es.ucm.fdi.ici.c2122.practica2.grupo03.ghosts.transitions.FurthestGhost;
import es.ucm.fdi.ici.c2122.practica2.grupo03.ghosts.transitions.FurthestGhost2;
import es.ucm.fdi.ici.c2122.practica2.grupo03.ghosts.transitions.FurthestGhost3;
import es.ucm.fdi.ici.c2122.practica2.grupo03.ghosts.transitions.GhostsEdibleAndPacManNearPPill;
import es.ucm.fdi.ici.c2122.practica2.grupo03.ghosts.transitions.GhostsNotEdibleAndPacManFarPPill;
import es.ucm.fdi.ici.c2122.practica2.grupo03.ghosts.transitions.NotClosestGhost;
import es.ucm.fdi.ici.c2122.practica2.grupo03.ghosts.transitions.PacManFurtherThan20;
import es.ucm.fdi.ici.c2122.practica2.grupo03.ghosts.transitions.PacManFurtherThan30;
import es.ucm.fdi.ici.c2122.practica2.grupo03.ghosts.transitions.PacManFurtherThan40;
import es.ucm.fdi.ici.c2122.practica2.grupo03.ghosts.transitions.PacManFurtherThan50;
import es.ucm.fdi.ici.c2122.practica2.grupo03.ghosts.transitions.PacManNoTimetoEatMe1;
import es.ucm.fdi.ici.c2122.practica2.grupo03.ghosts.transitions.PacManNoTimetoEatMe2;
import es.ucm.fdi.ici.c2122.practica2.grupo03.ghosts.transitions.PacManNotFurtherThan20;
import es.ucm.fdi.ici.c2122.practica2.grupo03.ghosts.transitions.PacManNotFurtherThan30;
import es.ucm.fdi.ici.c2122.practica2.grupo03.ghosts.transitions.PacManNotFurtherThan40;
import es.ucm.fdi.ici.c2122.practica2.grupo03.ghosts.transitions.PacManNotFurtherThan50;
import es.ucm.fdi.ici.c2122.practica2.grupo03.ghosts.transitions.PacManTryingtoEatMeAndClosestGhost;
import es.ucm.fdi.ici.c2122.practica2.grupo03.ghosts.transitions.SecondClosestGhost;
import es.ucm.fdi.ici.c2122.practica2.grupo03.ghosts.transitions.SecondClosestGhost2;
import es.ucm.fdi.ici.c2122.practica2.grupo03.ghosts.transitions.SecondClosestGhost3;
import es.ucm.fdi.ici.c2122.practica2.grupo03.ghosts.transitions.ThirdClosestGhost;
import es.ucm.fdi.ici.c2122.practica2.grupo03.ghosts.transitions.ThirdClosestGhost2;
import es.ucm.fdi.ici.c2122.practica2.grupo03.ghosts.transitions.ThirdClosestGhost3;
import es.ucm.fdi.ici.fsm.CompoundState;
import es.ucm.fdi.ici.fsm.FSM;
import es.ucm.fdi.ici.fsm.SimpleState;
import es.ucm.fdi.ici.fsm.Transition;
import es.ucm.fdi.ici.fsm.observers.ConsoleFSMObserver;
import es.ucm.fdi.ici.fsm.observers.GraphFSMObserver;
import pacman.controllers.GhostController;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class Ghosts extends GhostController {

	EnumMap<GHOST,FSM> fsms;
	public Ghosts()
	{
		setName("Ghosts XX");

		fsms = new EnumMap<GHOST,FSM>(GHOST.class);
		for(GHOST ghost: GHOST.values()) {
			FSM fsm = new FSM(ghost.name());
			fsm.addObserver(new ConsoleFSMObserver(ghost.name()));
			GraphFSMObserver graphObserver = new GraphFSMObserver(ghost.name());
			fsm.addObserver(graphObserver);

			
						///////////////////////////////////////////        HUIR      /////////////////////////////////////////////////////////////
						
						FSM cfsmrunAway = new FSM("HUIR");
						GraphFSMObserver runAwayobserver = new GraphFSMObserver(cfsmrunAway.toString());
						cfsmrunAway.addObserver(runAwayobserver);
				    	
						//estados
						
				    	SimpleState huirPacMan = new SimpleState("huirPacman", new RunAwayFromPacmanAction(ghost));
				    	SimpleState huirGhost = new SimpleState("huirGhost", new RunAwayFromClosestGhostAction(ghost));
				    	SimpleState mantenerseEnCercanias = new SimpleState("mantenerse en las cercanias", new BeNearbyPacmanAction(ghost));
				    	
				    	//transiciones
				    	
				    	Transition notClosest = new NotClosestGhost(ghost);
				    	Transition ClosestAndChased = new PacManTryingtoEatMeAndClosestGhost(ghost);
				    	Transition NoTimeToEat1 = new PacManNoTimetoEatMe1(ghost);
				    	Transition NoTimeToEat2 = new PacManNoTimetoEatMe2(ghost);
				    	
				    	//creación de maquina 
				    	
				    	cfsmrunAway.add(huirPacMan, ClosestAndChased, huirGhost);
				    	cfsmrunAway.add(huirPacMan, NoTimeToEat1, mantenerseEnCercanias);
				    	
				    	cfsmrunAway.add(huirGhost, notClosest, huirPacMan);
				    	cfsmrunAway.add(huirGhost, NoTimeToEat2, mantenerseEnCercanias);
				    	
				    	cfsmrunAway.ready(huirPacMan);
				    	
				    	CompoundState runAwaycompound = new CompoundState("runAwaycompound", cfsmrunAway);
				    	
				    	
				    	///////////////////////////////////////        PERSEGUIR      ///////////////////////////////////////////////////////
				    	
				    	
				    	FSM cfsmChase = new FSM("PERSEGUIR");
				    	GraphFSMObserver Chaseobserver = new GraphFSMObserver(cfsmChase.toString());
				    	cfsmChase.addObserver(Chaseobserver);
				    	
				    	
				    	
				    	/// estados 
				    	
				    	
				    	
				    	
				    	
						    			///////////////////////        Fantasma más cercano al PacMan      /////////////////////////////
						    	
						    			FSM cfsmclosestGhost = new FSM("Fantasma mas cercano");
						    			GraphFSMObserver closestGhostobserver = new GraphFSMObserver(cfsmclosestGhost.toString());
						    			cfsmclosestGhost.addObserver(closestGhostobserver);
						    	
						    			//estados
								
						    			SimpleState chasePacman = new SimpleState("chase pacman", new ChasePacmanAction(ghost));
						    			SimpleState moves5 = new SimpleState("be onto him on 5 moves", new CutPathIn20MovementsAction(ghost));
						    	
						    			//transiciones
						    	
						    			Transition further5 = new PacManFurtherThan20(ghost);
						    			Transition notfurther5 = new PacManNotFurtherThan20(ghost);
						    			
						    			//creación de maquina 
						    	    	
						    			cfsmclosestGhost.add(moves5, notfurther5, chasePacman);
						    			cfsmclosestGhost.add(chasePacman, further5, moves5);
						    			
						    			cfsmclosestGhost.ready(chasePacman);
						    	    	
						    	    	CompoundState closestGhostcompound = new CompoundState("ClosestGhostcompound", cfsmclosestGhost);
						    	    	
						    	    	
						    	    	
						    	    	
						    	    	
						    	    	
						    	    	
						    	    	///////////////////        Fantasma segundo más cercano al PacMan      //////////////////////////
						    	    	
						    	    	FSM cfsmsecondclosestGhost = new FSM("Segundo fantasma mas cercano");
						    			GraphFSMObserver secondclosestGhostobserver = new GraphFSMObserver(cfsmsecondclosestGhost.toString());
						    			cfsmsecondclosestGhost.addObserver(secondclosestGhostobserver);
						    	
						    			//estados
								
						    			SimpleState moves20 = new SimpleState("be onto him on 10 moves", new CutPathIn20MovementsAction(ghost));
						    			SimpleState moves202 = new SimpleState("be onto him on 10 moves", new CutPathIn20MovementsAction(ghost));
						    	
						    			//transiciones
						    	
						    			Transition further10 = new PacManFurtherThan30(ghost);
						    			Transition notfurther10 = new PacManNotFurtherThan30(ghost);
						    			
						    			//creación de maquina 
						    	    	
						    			cfsmsecondclosestGhost.add(moves202, notfurther10, moves20);
						    			cfsmsecondclosestGhost.add(moves20, further10, moves202);
						    			
						    			cfsmsecondclosestGhost.ready(moves20);
						    	    	
						    	    	CompoundState secondclosestGhostcompound = new CompoundState("SecondClosestGhostcompound", cfsmsecondclosestGhost);
						    	    	
						    	    	
						    	    	
						    	    	
						    	    	
						    	    	
						    	    	
						    	    	
						    	    	///////////////////        Fantasma tercero más cercano al PacMan      //////////////////////////
						    	    	
						    	    	FSM cfsmthirdclosestGhost = new FSM("Tercer fantasma mas cercano");
						    			GraphFSMObserver thirdclosestGhostobserver = new GraphFSMObserver(cfsmthirdclosestGhost.toString());
						    			cfsmthirdclosestGhost.addObserver(thirdclosestGhostobserver);
						    	
						    			//estados
								
						    			SimpleState moves30 = new SimpleState("be onto him on 10 moves", new CutPathIn30MovementsAction(ghost));
						    			SimpleState moves301 = new SimpleState("be onto him on 10 moves", new CutPathIn30MovementsAction(ghost));
						    	
						    			//transiciones
						    	
						    			Transition further20 = new PacManFurtherThan40(ghost);
						    			Transition notfurther20 = new PacManNotFurtherThan40(ghost);
						    			
						    			//creación de maquina 
						    	    	
						    			cfsmthirdclosestGhost.add(moves301, notfurther20, moves30);
						    			cfsmthirdclosestGhost.add(moves30, further20, moves301);
						    			
						    			cfsmthirdclosestGhost.ready(moves30);
						    	    	
						    	    	CompoundState thirdclosestGhostcompound = new CompoundState("ThirdClosestGhostcompound", cfsmthirdclosestGhost);
						    	    	
						    	    	
						    	    	
						    	    	
						    	    	
						    	    	
						    	    	
						    	    	
						    	    	///////////////////        Fantasma más lejano al PacMan      //////////////////////////
						    	    	
						    	    	FSM cfsmfurthestGhost = new FSM("Fantasma mas lejano");
						    			GraphFSMObserver furthestGhostobserver = new GraphFSMObserver(cfsmfurthestGhost.toString());
						    			cfsmfurthestGhost.addObserver(furthestGhostobserver);
						    	
						    			//estados
								
						    			SimpleState moves304 = new SimpleState("be onto him on 20 moves", new CutPathIn30MovementsAction(ghost));
						    			SimpleState moves305 = new SimpleState("be onto him on 20 moves", new CutPathIn30MovementsAction(ghost));
						    	
						    			//transiciones
						    	
						    			Transition further30 = new PacManFurtherThan50(ghost);
						    			Transition notfurther30 = new PacManNotFurtherThan50(ghost);
						    			
						    			//creación de maquina 
						    	    	
						    			cfsmfurthestGhost.add(moves305, notfurther30, moves304);
						    			cfsmfurthestGhost.add(moves304, further30, moves305);
						    			
						    			cfsmfurthestGhost.ready(moves304);
						    	    	
						    	    	CompoundState furthestGhostcompound = new CompoundState("FurthestGhostcompound", cfsmfurthestGhost);
				    			
				    	    	
				    	    	
				    	    	
				    	    	
				    	/// transiciones
				    	    	
				        Transition closest = new ClosestGhost(ghost);
				    	Transition secondclosest = new SecondClosestGhost(ghost);
				    	Transition thirdclosest = new ThirdClosestGhost(ghost);
				    	Transition furthest = new FurthestGhost(ghost);
				    	
				    	Transition closest2 = new ClosestGhost2(ghost);
				    	Transition secondclosest2 = new SecondClosestGhost2(ghost);
				    	Transition thirdclosest2 = new ThirdClosestGhost2(ghost);
				    	Transition furthest2 = new FurthestGhost2(ghost);
				    	
				    	Transition closest3 = new ClosestGhost3(ghost);
				    	Transition secondclosest3 = new SecondClosestGhost3(ghost);
				    	Transition thirdclosest3 = new ThirdClosestGhost3(ghost);
				    	Transition furthest3 = new FurthestGhost3(ghost);
				    	    	
				    	
				    	//creación de maquina 
				    	
				    	cfsmChase.add(closestGhostcompound, secondclosest, secondclosestGhostcompound);
				    	cfsmChase.add(closestGhostcompound, thirdclosest, thirdclosestGhostcompound);
				    	cfsmChase.add(closestGhostcompound, furthest, furthestGhostcompound);
				    	
				    	cfsmChase.add(secondclosestGhostcompound, closest, closestGhostcompound);
				    	cfsmChase.add(secondclosestGhostcompound, thirdclosest2, thirdclosestGhostcompound);
				    	cfsmChase.add(secondclosestGhostcompound, furthest2, furthestGhostcompound);
				    	
				    	cfsmChase.add(thirdclosestGhostcompound, closest2, closestGhostcompound);
				    	cfsmChase.add(thirdclosestGhostcompound, secondclosest2, secondclosestGhostcompound);
				    	cfsmChase.add(thirdclosestGhostcompound, furthest3, furthestGhostcompound);
				    	
				    	cfsmChase.add(furthestGhostcompound, closest3, closestGhostcompound);
				    	cfsmChase.add(furthestGhostcompound, secondclosest3, secondclosestGhostcompound);
				    	cfsmChase.add(furthestGhostcompound, thirdclosest3, thirdclosestGhostcompound);
				    	
				    	cfsmChase.ready(closestGhostcompound);
				    	
				    	CompoundState Chasecompound = new CompoundState("Chasecompound", cfsmChase);
				    	
				    	
				    	
		    	
	    	
	    	///////////////////////////////////////         JUNTAR perseguir y huir       //////////////////////////////////////////
	    	
	    	//transiciones
	    	
	    	Transition notEdible = new GhostsNotEdibleAndPacManFarPPill(ghost);
	    	Transition edible = new GhostsEdibleAndPacManNearPPill(ghost);
	    	
	    	//creación de maquina 
	    	
	    	fsm.add(runAwaycompound, notEdible, Chasecompound);
	    	fsm.add(Chasecompound, edible, runAwaycompound);
	    	
	    	
			fsm.ready(Chasecompound);
			
			//graphObserver.showInFrame(new Dimension(800,600));
			
			
			JFrame frame = new JFrame();
	    	JPanel main = new JPanel();
	    	main.setLayout(new BorderLayout());
	    	main.add(graphObserver.getAsPanel(true, null), BorderLayout.EAST);
	    	main.add(runAwayobserver.getAsPanel(true, null), BorderLayout.CENTER);
	    	main.add(Chaseobserver.getAsPanel(true, null), BorderLayout.WEST);
	    			    	frame.getContentPane().add(main);
	    	frame.pack();
	    	//frame.setVisible(true);
	    	
	    	
	    	// Mas detallado maquina de estados de la parte de perseguir
	    	
	    	//JFrame frame2 = new JFrame();
	    	//JPanel Ghostpositions = new JPanel();
	    	//Ghostpositions.setLayout(new BorderLayout());
	    	//Ghostpositions.add(closestGhostobserver.getAsPanel(true, null), BorderLayout.NORTH);
	    	//Ghostpositions.add(secondclosestGhostobserver.getAsPanel(true, null), BorderLayout.EAST);
	    	//Ghostpositions.add(thirdclosestGhostobserver.getAsPanel(true, null), BorderLayout.WEST);
	    	//Ghostpositions.add(furthestGhostobserver.getAsPanel(true, null), BorderLayout.SOUTH);		
	    	    
	    
	    	//frame2.getContentPane().add(Ghostpositions);
	    	//frame2.pack();
	    	//frame2.setVisible(true);
	    	
	    	
			
			fsms.put(ghost, fsm);
		}
		
		
							
	}
	
	public void preCompute(String opponent) {
    	for(FSM fsm: fsms.values())
    		fsm.reset();
    }
	
	@Override
	public EnumMap<GHOST, MOVE> getMove(Game game, long timeDue) {
		EnumMap<GHOST,MOVE> result = new EnumMap<GHOST,MOVE>(GHOST.class);
		
		GhostsInput in = new GhostsInput(game);
		
		for(GHOST ghost: GHOST.values())
		{
			FSM fsm = fsms.get(ghost);
			MOVE move = fsm.run(in);
			result.put(ghost, move);
		}
		
		return result;
		
	
		
	}

}
