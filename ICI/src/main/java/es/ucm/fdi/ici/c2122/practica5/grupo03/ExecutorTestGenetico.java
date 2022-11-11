package es.ucm.fdi.ici.c2122.practica5.grupo03;

import java.util.Vector;

import pacman.Executor;
import pacman.controllers.GhostController;
import pacman.controllers.PacmanController;

public class ExecutorTestGenetico {

    public static void main(String args[]) {
        Vector<Double> d = new Vector<Double>();
        for(int i = 0; i < 12; i++) {
        	d.add((double)1/12);
        }
        for(int i = 12; i < 24; i++) {
        	d.add((double)1/12);
        }
        
    	new ExecutorTestGenetico().ejecutar(100, d);
        
    }
    
    public int ejecutar(int sv, Vector<Double> args) {
    	
    	Executor executor = new Executor.Builder()
                .setTickLimit(4000)
                .setGhostPO(false)
                .setPacmanPO(false)
                .setVisual(false)
                .setScaleFactor(3.0)
                .setTimeLimit(150)
                .build();
        
        Vector<Double> coefsPacMan = new Vector<Double>();
        Vector<Double> coefsGhosts = new Vector<Double>();
        
        int numcoefs = args.size()/2;
        int safevalue = sv;
        
        for(int i = 0; i < numcoefs; i++) {
        	coefsPacMan.add(args.get(i));        	
        }
        
        for(int i = numcoefs; i < args.size(); i++) {
        	coefsGhosts.add(args.get(i));        	
        }
        
        System.out.println("Hay " + args.size() + " coefs");
        System.out.println("Hay " + coefsPacMan.size() + " coefs de pacman");
        System.out.println("Hay " + coefsGhosts.size() + " coefs de ghosts");
        
        PacmanController pacMan = new MsPacManGenetico(coefsPacMan, safevalue);
        GhostController ghosts = new GhostsGenetico(coefsGhosts, safevalue);
        
		System.out.println(executor.runGameTimedSpeedOptimised(pacMan, ghosts,false,"CBR test"));

		return 3; 
    	
    }
    
}