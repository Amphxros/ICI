package es.ucm.fdi.ici.c2122.practica2.grupo03;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import es.ucm.fdi.ici.Input;
import es.ucm.fdi.ici.c2122.practica2.grupo03.mspacman.MsPacManInput;

import es.ucm.fdi.ici.c2122.practica2.grupo03.mspacman.actions.GoToClosestGhost;
import es.ucm.fdi.ici.c2122.practica2.grupo03.mspacman.actions.GoToClosestPill;

import es.ucm.fdi.ici.c2122.practica2.grupo03.mspacman.actions.GoToClosestPowerPill;
import es.ucm.fdi.ici.c2122.practica2.grupo03.mspacman.actions.GoToSecondPowerPill;
import es.ucm.fdi.ici.c2122.practica2.grupo03.mspacman.actions.GoToThirdPowerPill;
import es.ucm.fdi.ici.c2122.practica2.grupo03.mspacman.actions.GoToFarthestPowerPill;

import es.ucm.fdi.ici.c2122.practica2.grupo03.mspacman.actions.RunAwayFromClosestGhost;
import es.ucm.fdi.ici.c2122.practica2.grupo03.mspacman.actions.RunAwayFromPPill;

import es.ucm.fdi.ici.c2122.practica2.grupo03.mspacman.transitions.CloseGhost;
import es.ucm.fdi.ici.c2122.practica2.grupo03.mspacman.transitions.EatPowerPill;
import es.ucm.fdi.ici.c2122.practica2.grupo03.mspacman.transitions.NoCloseGhost;
import es.ucm.fdi.ici.c2122.practica2.grupo03.mspacman.transitions.NoEdibleGhost;
import es.ucm.fdi.ici.c2122.practica2.grupo03.mspacman.transitions.NoEdibleGhostAndNoPills;
import es.ucm.fdi.ici.c2122.practica2.grupo03.mspacman.transitions.NoEdibleGhostAndPPills;
import es.ucm.fdi.ici.c2122.practica2.grupo03.mspacman.transitions.NoPowerPills;
import es.ucm.fdi.ici.c2122.practica2.grupo03.mspacman.transitions.PowerPillClose;
import es.ucm.fdi.ici.c2122.practica2.grupo03.mspacman.transitions.PowerPills;
import es.ucm.fdi.ici.fsm.CompoundState;
import es.ucm.fdi.ici.fsm.FSM;
import es.ucm.fdi.ici.fsm.SimpleState;
import es.ucm.fdi.ici.fsm.Transition;
import es.ucm.fdi.ici.fsm.observers.ConsoleFSMObserver;
import es.ucm.fdi.ici.fsm.observers.GraphFSMObserver;
import pacman.controllers.PacmanController;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

/*
 * The Class NearestPillPacMan.
 */
public class MsPacMan extends PacmanController {

	FSM fsm;
	public MsPacMan() {
		setName("MsPacMan XX");
		
    	fsm = new FSM("MsPacMan");
    	
    	fsm.addObserver(new ConsoleFSMObserver("MsPacMan"));
		GraphFSMObserver graphObserver = new GraphFSMObserver("MsPacMan");
		fsm.addObserver(graphObserver);

////////////////////////////////////////// HUIR CON PILLS		//////////////////////////////////////////////////////////////
		
		FSM runAway= new FSM("GO AWAY");
		GraphFSMObserver runAwayobserver = new GraphFSMObserver(runAway.toString());
		runAway.addObserver(runAwayobserver);
		
		SimpleState getClosestPPill = new SimpleState("closest PPill", new GoToClosestPowerPill());
		SimpleState getSecondPPill = new SimpleState("second PPill", new GoToSecondPowerPill());
		SimpleState getThirdPPill = new SimpleState("third PPill", new GoToThirdPowerPill());
		SimpleState getFarthestPPill = new SimpleState("closest PPill", new GoToFarthestPowerPill());
		SimpleState runFromGhost= new SimpleState("Flee Ghosts", new RunAwayFromClosestGhost());

		Transition close_Ghost= new CloseGhost();
		Transition No_Close_Ghost= new NoCloseGhost();
		Transition powerPillClose= new PowerPillClose();
		
		
		runAway.add(getClosestPPill, close_Ghost, runFromGhost);
		runAway.add(getSecondPPill, powerPillClose, getClosestPPill);
		runAway.add(runFromGhost, No_Close_Ghost, getClosestPPill);
		runAway.ready(getClosestPPill);
		
		CompoundState RunPPillscompound = new CompoundState("RunPPillcompound", runAway);
		
		//////////////////////////////////////////		SEEK		//////////////////////////////////////////////////////////////
		FSM seek= new FSM("GO EAT GHOSTS");
		GraphFSMObserver seekobserver = new GraphFSMObserver(seek.toString());
		seek.addObserver(seekobserver);
		
		SimpleState chaseGhost= new SimpleState("ChaseGhost", new GoToClosestGhost());
		SimpleState goToPill= new SimpleState("ChasePill", new GoToClosestPill());
		SimpleState fleeGhost= new SimpleState("Flee Ghosts", new RunAwayFromClosestGhost());

		Transition closeGhost= new CloseGhost();
		Transition NoCloseGhost= new NoCloseGhost();
		
		seek.add(chaseGhost, closeGhost, fleeGhost);
		seek.add(fleeGhost, NoCloseGhost, chaseGhost);
		seek.ready(chaseGhost);
		CompoundState Chasecompound = new CompoundState("Chasecompound", seek);
		//////////////////////////////////////////  	HUIR SIN PPILLS		//////////////////////////////////////////////////////////////
		
		FSM run= new FSM("RUN WITH NO PILLS");
		GraphFSMObserver runobserver = new GraphFSMObserver(run.toString());
		run.addObserver(runobserver);
		
		SimpleState ghostNear= new SimpleState("Ghost cerca", new RunAwayFromClosestGhost());
		SimpleState goToPills= new SimpleState("Pills", new GoToClosestPill());
		SimpleState farthestPill= new SimpleState("far pill", new GoToFarthestPowerPill());
		
		Transition close= new CloseGhost();
		Transition noClose= new NoCloseGhost();
		
		run.add(farthestPill, close, ghostNear);
		run.add(ghostNear, noClose, farthestPill);

		run.ready(farthestPill);
		
		CompoundState Runcompound = new CompoundState("Runcompound", run);
		
		
		
		Transition eat= new EatPowerPill();
		Transition noEdible= new NoEdibleGhost();
		Transition notEdibleNoPPills= new NoEdibleGhostAndNoPills();
		Transition ppills= new PowerPills();
		
		fsm.add(RunPPillscompound,eat, Chasecompound);
		fsm.add(Chasecompound, noEdible,RunPPillscompound);
		fsm.add(Runcompound,ppills,RunPPillscompound);
		fsm.add(RunPPillscompound, notEdibleNoPPills, Runcompound);
		
		
		fsm.ready(RunPPillscompound);

	}
	
	
	public void preCompute(String opponent) {
    		fsm.reset();
    }
	
	
	
    /* (non-Javadoc)
     * @see pacman.controllers.Controller#getMove(pacman.game.Game, long)
     */
    @Override
    public MOVE getMove(Game game, long timeDue) {
       	Input in = new MsPacManInput(game); 
    	return fsm.run(in);
    }
    
    
}