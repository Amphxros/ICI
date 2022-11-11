

import java.util.Vector;

import es.ucm.fdi.ici.c2122.practica5.grupo03.Ghosts;
import es.ucm.fdi.ici.c2122.practica5.grupo03.MsPacMan;
import pacman.Executor;
import pacman.controllers.GhostController;
import pacman.controllers.PacmanController;


public class ExecutorTest {

    public static void main(String[] args) {
        Executor executor = new Executor.Builder()
                .setTickLimit(4000)
                .setGhostPO(false)
                .setPacmanPO(false)
                .setVisual(true)
                .setScaleFactor(3.0)
                .setTimeLimit(150) // poner a 150 en estos ordenadores
                .build();
       /* 
        Vector<Double> vp = new Vector<Double>(12);
        
        for(Double d: vecPAC) {
        	vp.add(d);
        }
        
        Vector<Double> vg = new Vector<Double>(12);
        
        for(Double d: vecG) {
        	vg.add(d);
        }*/

        PacmanController pacMan = new MsPacMan(/*vp, 100*/);
        GhostController ghosts = new Ghosts(/*vg, 100*/);
      
        

        executor.runGameTimedSpeedOptimised(pacMan, ghosts,false,"CBR test");
        
        //Time benchmark
		//long time = System.currentTimeMillis();
        //executor.runExperiment(pacMan, ghosts, 50, pacMan.getClass().getName()+ " - " + ghosts.getClass().getName());
		//System.out.println(System.currentTimeMillis()-time);

        
    }
}
