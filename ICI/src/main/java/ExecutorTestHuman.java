

import es.ucm.fdi.ici.c2122.practica5.grupo03.GhostsHuman;
import es.ucm.fdi.ici.c2122.practica5.grupo03.MsPacManHuman;
import pacman.Executor;
import pacman.controllers.GhostController;
import pacman.controllers.KeyBoardInput;
import pacman.controllers.PacmanController;


public class ExecutorTestHuman {

    public static void main(String[] args) {
        Executor executor = new Executor.Builder()
                .setTickLimit(4000)
                .setGhostPO(false)
                .setPacmanPO(false)
                .setVisual(true)
                .setScaleFactor(3.0)
                .setTimeLimit(150) // poner a 150 en estos ordenadores
                .build();

        PacmanController pacMan = new MsPacManHuman(new KeyBoardInput());
        GhostController ghosts = new GhostsHuman();
      
        

        executor.runGameTimedSpeedOptimised(pacMan, ghosts,true,"CBR test");
        
        //Time benchmark
		//long time = System.currentTimeMillis();
        //executor.runExperiment(pacMan, ghosts, 50, pacMan.getClass().getName()+ " - " + ghosts.getClass().getName());
		//System.out.println(System.currentTimeMillis()-time);

        
    }
}
