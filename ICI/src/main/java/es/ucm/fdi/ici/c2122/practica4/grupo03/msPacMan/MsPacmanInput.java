package es.ucm.fdi.ici.c2122.practica4.grupo03.msPacMan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import es.ucm.fdi.ici.Input;
import es.ucm.fdi.ici.c2122.practica4.grupo03.utils.Func;
import es.ucm.fdi.ici.c2122.practica4.grupo03.utils.Pair;
import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Game;

public class MsPacmanInput extends Input {

	private static final String[] variables = {

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
	
	 List<Pair<String,Double>> msPacmanVar_;
	
	 private List<Func<Game>> generaLista(){
		
		 List <Func<Game>> list= new ArrayList<Func<Game>>();
		 
		 //pacman to pill distance
		 list.add(new Func<Game>() {
			@Override
			public double calcular(Game arg, Vector<Double> args) {
				// TODO Auto-generated method stub
				int pacman= arg.getPacmanCurrentNodeIndex();
				if(arg.isNodeObservable(pacman)) {
					int [] pills= arg.getActivePillsIndices();
	
					int nearest= arg.getClosestNodeIndexFromNodeIndex(pacman, pills, DM.PATH);
					return arg.getDistance(pacman, nearest, DM.PATH);
				}
				else {
					return -1;
				}
				
			}
		 });
		 
		 //pacman to closest ppill distance
		 list.add(new Func<Game>() {

			@Override
			public double calcular(Game arg, Vector<Double> args) {
				// TODO Auto-generated method stub
				int pacman= arg.getPacmanCurrentNodeIndex();
				if(arg.isNodeObservable(pacman)) {
					int [] pills= arg.getPowerPillIndices();
					if(pills.length>0) {
					int nearest= arg.getClosestNodeIndexFromNodeIndex(pacman, pills, DM.PATH);
					return arg.getDistance(pacman, nearest, DM.PATH);
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
		 
		 //pacman to blinky distance
		 list.add(new Func<Game>() {

			@Override
			public double calcular(Game arg, Vector<Double> args) {
				// TODO Auto-generated method stub
				int pacman= arg.getPacmanCurrentNodeIndex();
				if(arg.isNodeObservable(pacman)) {
					int ghost= arg.getGhostCurrentNodeIndex(GHOST.BLINKY);
					
					return game.getDistance(pacman, ghost, DM.PATH);
					
				}
				else {
					return -1;
				}
				
			}
		 });
		 
		 //pacman to pinky distance
		 list.add(new Func<Game>() {

			@Override
			public double calcular(Game arg, Vector<Double> args) {
				// TODO Auto-generated method stub
				int pacman= arg.getPacmanCurrentNodeIndex();
				if(arg.isNodeObservable(pacman)) {
					int ghost= arg.getGhostCurrentNodeIndex(GHOST.PINKY);
					
					return game.getDistance(pacman, ghost, DM.PATH);
					
				}
				else {
					return -1;
				}
				
			}
		 });

		 //pacman to inky distance
		 list.add(new Func<Game>() {

			@Override
			public double calcular(Game arg, Vector<Double> args) {
				// TODO Auto-generated method stub
				int pacman= arg.getPacmanCurrentNodeIndex();
				if(arg.isNodeObservable(pacman)) {
					int ghost= arg.getGhostCurrentNodeIndex(GHOST.INKY);
					
					return game.getDistance(pacman, ghost, DM.PATH);
					
				}
				else {
					return -1;
				}
				
			}
		 }); 
		 
		 //pacman to sue distance
		 list.add(new Func<Game>() {

			@Override
			public double calcular(Game arg, Vector<Double> args) {
				// TODO Auto-generated method stub
				int pacman= arg.getPacmanCurrentNodeIndex();
				if(arg.isNodeObservable(pacman)) {
					int ghost= arg.getGhostCurrentNodeIndex(GHOST.SUE);
					
					return game.getDistance(pacman, ghost, DM.PATH);
					
				}
				else {
					return -1;
				}
				
			}
		 }); 
		 

		//"BLINKYEdible",
		 list.add(new Func<Game>() {

				@Override
				public double calcular(Game arg, Vector<Double> args) {
					// TODO Auto-generated method stub
					int pacman= arg.getPacmanCurrentNodeIndex();
					if(arg.isNodeObservable(pacman)) {
						float ghostEdibleTime= arg.getGhostEdibleTime(GHOST.BLINKY);
						if(ghostEdibleTime > 0) {
							return 1;
						}
						else {
							return 0;
						}
					}
					else {
						return -1;
					}
					
				}
			 }); 
		 
		 
		//"PINKYEdible",
		 list.add(new Func<Game>() {

				@Override
				public double calcular(Game arg, Vector<Double> args) {
					// TODO Auto-generated method stub
					int pacman= arg.getPacmanCurrentNodeIndex();
					if(arg.isNodeObservable(pacman)) {
						float ghostEdibleTime= arg.getGhostEdibleTime(GHOST.PINKY);
						if(ghostEdibleTime > 0) {
							return 1;
						}
						else {
							return 0;
						}
					}
					else {
						return -1;
					}
					
				}
			 }); 

		//"INKYEdible",
		 list.add(new Func<Game>() {

				@Override
				public double calcular(Game arg, Vector<Double> args) {
					// TODO Auto-generated method stub
					int pacman= arg.getPacmanCurrentNodeIndex();
					if(arg.isNodeObservable(pacman)) {
						float ghostEdibleTime= arg.getGhostEdibleTime(GHOST.INKY);
						if(ghostEdibleTime > 0) {
							return 1;
						}
						else {
							return 0;
						}
					}
					else {
						return -1;
					}
					
				}
			 }); 

		 
		//"SUEEdible", 
		 list.add(new Func<Game>() {

				@Override
				public double calcular(Game arg, Vector<Double> args) {
					// TODO Auto-generated method stub
					int pacman= arg.getPacmanCurrentNodeIndex();
					if(arg.isNodeObservable(pacman)) {
						float ghostEdibleTime= arg.getGhostEdibleTime(GHOST.SUE);
						if(ghostEdibleTime > 0) {
							return 1;
						}
						else {
							return 0;
						}
					
					}
					else {
						return -1;
					}
					
				}
			 }); 
		 
		//"BLINKYEdibletime",
		 list.add(new Func<Game>() {

				@Override
				public double calcular(Game arg, Vector<Double> args) {
					// TODO Auto-generated method stub
					int pacman= arg.getPacmanCurrentNodeIndex();
					if(arg.isNodeObservable(pacman)) {
						float ghostEdibleTime= arg.getGhostEdibleTime(GHOST.BLINKY);
						return ghostEdibleTime;
					
					}
					else {
						return -1;
					}
					
				}
			 }); 
		 
		 
		//"PINKYEdibletime",
		 list.add(new Func<Game>() {

				@Override
				public double calcular(Game arg, Vector<Double> args) {
					// TODO Auto-generated method stub
					int pacman= arg.getPacmanCurrentNodeIndex();
					if(arg.isNodeObservable(pacman)) {
						float ghostEdibleTime= arg.getGhostEdibleTime(GHOST.PINKY);
						return ghostEdibleTime;
					
					}
					else {
						return -1;
					}
					
				}
			 }); 

		//"INKYEdibletime",
		 list.add(new Func<Game>() {

				@Override
				public double calcular(Game arg, Vector<Double> args) {
					// TODO Auto-generated method stub
					int pacman= arg.getPacmanCurrentNodeIndex();
					if(arg.isNodeObservable(pacman)) {
						float ghostEdibleTime= arg.getGhostEdibleTime(GHOST.INKY);
						return ghostEdibleTime;
					
					}
					else {
						return -1;
					}
					
				}
			 }); 

		 
		//"SUEEdibletime", 
		 list.add(new Func<Game>() {

				@Override
				public double calcular(Game arg, Vector<Double> args) {
					// TODO Auto-generated method stub
					int pacman= arg.getPacmanCurrentNodeIndex();
					if(arg.isNodeObservable(pacman)) {
						float ghostEdibleTime= arg.getGhostEdibleTime(GHOST.SUE);
						return ghostEdibleTime;
					
					}
					else {
						return -1;
					}
					
				}
			 }); 
			
		 //"BLINKYJail",
		 list.add(new Func<Game>() {

				@Override
				public double calcular(Game arg, Vector<Double> args) {
					// TODO Auto-generated method stub
					int pacman= arg.getPacmanCurrentNodeIndex();
					if(arg.isNodeObservable(pacman)) {
						float jail= arg.getGhostLairTime(GHOST.BLINKY);
						if(jail > 0) {
							return 1;
						}
						else {
							return 0;
						}
					
					}
					else {
						return -1;
					}
					
				}
			 }); 
		 
		 //"PINKYJail",
		 list.add(new Func<Game>() {

				@Override
				public double calcular(Game arg, Vector<Double> args) {
					// TODO Auto-generated method stub
					int pacman= arg.getPacmanCurrentNodeIndex();
					if(arg.isNodeObservable(pacman)) {
						float jail= arg.getGhostLairTime(GHOST.PINKY);
						if(jail > 0) {
							return 1;
						}
						else {
							return 0;
						}
					
					}
					else {
						return -1;
					}
					
				}
			 }); 
		 
		 //"INKYJail",
		 list.add(new Func<Game>() {

				@Override
				public double calcular(Game arg, Vector<Double> args) {
					// TODO Auto-generated method stub
					int pacman= arg.getPacmanCurrentNodeIndex();
					if(arg.isNodeObservable(pacman)) {
						float jail= arg.getGhostLairTime(GHOST.INKY);
						if(jail > 0) {
							return 1;
						}
						else {
							return 0;
						}
					
					}
					else {
						return -1;
					}
					
				}
			 }); 
		
		 //"SUEJail",
		 list.add(new Func<Game>() {

				@Override
				public double calcular(Game arg, Vector<Double> args) {
					// TODO Auto-generated method stub
					int pacman= arg.getPacmanCurrentNodeIndex();
					if(arg.isNodeObservable(pacman)) {
						float jail= arg.getGhostLairTime(GHOST.SUE);
						if(jail > 0) {
							return 1;
						}
						else {
							return 0;
						}
					
					}
					else {
						return -1;
					}
					
				}
			 }); 
		 
		 
		//"BLINKYJailtime",
		 list.add(new Func<Game>() {

				@Override
				public double calcular(Game arg, Vector<Double> args) {
					// TODO Auto-generated method stub
					int pacman= arg.getPacmanCurrentNodeIndex();
					if(arg.isNodeObservable(pacman)) {
						float jail= arg.getGhostLairTime(GHOST.BLINKY);
						return jail;
					
					}
					else {
						return -1;
					}
					
				}
			 }); 
		 
		 //"PINKYJailtime",
		 list.add(new Func<Game>() {

				@Override
				public double calcular(Game arg, Vector<Double> args) {
					// TODO Auto-generated method stub
					int pacman= arg.getPacmanCurrentNodeIndex();
					if(arg.isNodeObservable(pacman)) {
						float jail= arg.getGhostLairTime(GHOST.PINKY);
						return jail;
					
					}
					else {
						return -1;
					}
					
				}
			 }); 
		 
		 //"INKYJailtime",
		 list.add(new Func<Game>() {

				@Override
				public double calcular(Game arg, Vector<Double> args) {
					// TODO Auto-generated method stub
					int pacman= arg.getPacmanCurrentNodeIndex();
					if(arg.isNodeObservable(pacman)) {
						float jail= arg.getGhostLairTime(GHOST.INKY);
						return jail;
					
					}
					else {
						return -1;
					}
					
				}
			 }); 
		
		 //"SUEJailtime",
		 list.add(new Func<Game>() {

				@Override
				public double calcular(Game arg, Vector<Double> args) {
					// TODO Auto-generated method stub
					int pacman= arg.getPacmanCurrentNodeIndex();
					if(arg.isNodeObservable(pacman)) {
						float jail= arg.getGhostLairTime(GHOST.SUE);
						return jail;
					
					}
					else {
						return -1;
					}
					
				}
			 });
		
		 //"PacmanDirection"
		 list.add(new Func<Game>() {			
				@Override
				public double calcular(Game arg, Vector<Double> args) {
					// TODO Auto-generated method stub
					int pacman= arg.getPacmanCurrentNodeIndex();
					if(arg.isNodeObservable(pacman)) {
						switch(arg.getPacmanLastMoveMade()) {
							case UP:
								return 8;
							case DOWN:
								return 2;
							case LEFT:
								return 4;
							case RIGHT:
								return 6;
						default:
							return -1;
						}
					}
					else {
						return -1;
					}
			}
			 
		 });
		 
		 
			
		 //	"BLINKYDiretion",
			 list.add(new Func<Game>() {			
					@Override
					public double calcular(Game arg, Vector<Double> args) {
						// TODO Auto-generated method stub
						int ghost= arg.getGhostCurrentNodeIndex(GHOST.BLINKY);
						if(arg.isNodeObservable(ghost)) {
							switch(arg.getGhostLastMoveMade(GHOST.BLINKY)) {
								case UP:
									return 8;
								case DOWN:
									return 2;
								case LEFT:
									return 4;
								case RIGHT:
									return 6;
							default:
								return -1;
							}
						}
						else {
							return -1;
						}
				}
				 
			 });
			//	"PINKYDiretion",
			 list.add(new Func<Game>() {			
					@Override
					public double calcular(Game arg, Vector<Double> args) {
						// TODO Auto-generated method stub
						int ghost= arg.getGhostCurrentNodeIndex(GHOST.PINKY);
						if(arg.isNodeObservable(ghost)) {
							switch(arg.getGhostLastMoveMade(GHOST.PINKY)) {
								case UP:
									return 8;
								case DOWN:
									return 2;
								case LEFT:
									return 4;
								case RIGHT:
									return 6;
							default:
								return -1;
							}
						}
						else {
							return -1;
						}
				}
				 
			 });
			 //	"INKYDiretion",
			 list.add(new Func<Game>() {			
					@Override
					public double calcular(Game arg, Vector<Double> args) {
						// TODO Auto-generated method stub
						int ghost= arg.getGhostCurrentNodeIndex(GHOST.INKY);
						if(arg.isNodeObservable(ghost)) {
							switch(arg.getGhostLastMoveMade(GHOST.INKY)) {
								case UP:
									return 8;
								case DOWN:
									return 2;
								case LEFT:
									return 4;
								case RIGHT:
									return 6;
							default:
								return -1;
							}
						}
						else {
							return -1;
						}
				}
				 
			 });
			 //	"SUEDiretion"
			 list.add(new Func<Game>() {			
					@Override
					public double calcular(Game arg, Vector<Double> args) {
						// TODO Auto-generated method stub
						int ghost= arg.getGhostCurrentNodeIndex(GHOST.SUE);
						if(arg.isNodeObservable(ghost)) {
							switch(arg.getGhostLastMoveMade(GHOST.SUE)) {
								case UP:
									return 8;
								case DOWN:
									return 2;
								case LEFT:
									return 4;
								case RIGHT:
									return 6;
							default:
								return -1;
							}
						}
						else {
							return -1;
						}
				}
				 
			 });
			 
			 
//				"BLINKYPosition",
			 list.add(new Func<Game>() {			
					@Override
					public double calcular(Game arg, Vector<Double> args) {
						// TODO Auto-generated method stub
						int ghost= arg.getGhostCurrentNodeIndex(GHOST.BLINKY);
						int pacman= arg.getPacmanCurrentNodeIndex();
						if(arg.isNodeObservable(ghost)) {
							if(arg.isNodeObservable(pacman)) {
								switch(arg.getPacmanLastMoveMade()) {
									case UP:
										if(pacman < ghost) {
											return 0;
										}
										else {
											return 1;
										}
									case DOWN:
										if(pacman > ghost) {
											return 0;
										}
										else {
											return 1;
										}
									case LEFT:
										if(pacman < ghost) {
											return 0;
										}
										else {
											return 1;
										}
										
									case RIGHT:
										if(pacman > ghost) {
											return 0;
										}
										else {
											return 1;
										}
										
									default:
										return -1;
								}
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
			 
			 
			 //	"BLINKYPosition",
			 list.add(new Func<Game>() {			
					@Override
					public double calcular(Game arg, Vector<Double> args) {
						// TODO Auto-generated method stub
						int ghost= arg.getGhostCurrentNodeIndex(GHOST.BLINKY);
						int pacman= arg.getPacmanCurrentNodeIndex();
						if(arg.isNodeObservable(ghost)) {
							if(arg.isNodeObservable(pacman)) {
								switch(arg.getPacmanLastMoveMade()) {
									case UP:
										if(pacman < ghost) {
											return 0;
										}
										else {
											return 1;
										}
									case DOWN:
										if(pacman > ghost) {
											return 0;
										}
										else {
											return 1;
										}
									case LEFT:
										if(pacman < ghost) {
											return 0;
										}
										else {
											return 1;
										}
										
									case RIGHT:
										if(pacman > ghost) {
											return 0;
										}
										else {
											return 1;
										}
										
									default:
										return -1;
								}
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
			//	"PINKYPosition",
			 list.add(new Func<Game>() {			
					@Override
					public double calcular(Game arg, Vector<Double> args) {
						// TODO Auto-generated method stub
						int ghost= arg.getGhostCurrentNodeIndex(GHOST.PINKY);
						int pacman= arg.getPacmanCurrentNodeIndex();
						if(arg.isNodeObservable(ghost)) {
							if(arg.isNodeObservable(pacman)) {
								switch(arg.getPacmanLastMoveMade()) {
									case UP:
										if(pacman < ghost) {
											return 0;
										}
										else {
											return 1;
										}
									case DOWN:
										if(pacman > ghost) {
											return 0;
										}
										else {
											return 1;
										}
									case LEFT:
										if(pacman < ghost) {
											return 0;
										}
										else {
											return 1;
										}
										
									case RIGHT:
										if(pacman > ghost) {
											return 0;
										}
										else {
											return 1;
										}
										
									default:
										return -1;
								}
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
			 //	"INKYPosition",
			 list.add(new Func<Game>() {			
					@Override
					public double calcular(Game arg, Vector<Double> args) {
						// TODO Auto-generated method stub
						int ghost= arg.getGhostCurrentNodeIndex(GHOST.INKY);
						int pacman= arg.getPacmanCurrentNodeIndex();
						if(arg.isNodeObservable(ghost)) {
							if(arg.isNodeObservable(pacman)) {
								switch(arg.getPacmanLastMoveMade()) {
									case UP:
										if(pacman < ghost) {
											return 0;
										}
										else {
											return 1;
										}
									case DOWN:
										if(pacman > ghost) {
											return 0;
										}
										else {
											return 1;
										}
									case LEFT:
										if(pacman < ghost) {
											return 0;
										}
										else {
											return 1;
										}
										
									case RIGHT:
										if(pacman > ghost) {
											return 0;
										}
										else {
											return 1;
										}
										
									default:
										return -1;
								}
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
			 //	"SUEPosition"
			 list.add(new Func<Game>() {			
					@Override
					public double calcular(Game arg, Vector<Double> args) {
						// TODO Auto-generated method stub
						int ghost= arg.getGhostCurrentNodeIndex(GHOST.SUE);
						int pacman= arg.getPacmanCurrentNodeIndex();
						if(arg.isNodeObservable(ghost)) {
							if(arg.isNodeObservable(pacman)) {
								switch(arg.getPacmanLastMoveMade()) {
									case UP:
										if(pacman < ghost) {
											return 0;
										}
										else {
											return 1;
										}
									case DOWN:
										if(pacman > ghost) {
											return 0;
										}
										else {
											return 1;
										}
									case LEFT:
										if(pacman < ghost) {
											return 0;
										}
										else {
											return 1;
										}
										
									case RIGHT:
										if(pacman > ghost) {
											return 0;
										}
										else {
											return 1;
										}
										
									default:
										return -1;
								}
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
			 
			 
			 //"PacmanToPillNode",
			 list.add(new Func<Game>() {			
					@Override
					public double calcular(Game arg, Vector<Double> args) {
						// TODO Auto-generated method stub
						int i = 0;
						int pill= 0;
						if(arg.getActivePillsIndices().length == 0) {
							return -1;
						}
						do{
							pill= arg.getClosestNodeIndexFromNodeIndex(arg.getPacmanCurrentNodeIndex(), Arrays.copyOfRange(arg.getActivePillsIndices(), i, arg.getNumberOfActivePills() + 1), DM.PATH);
							i++;
						} while(!arg.isNodeObservable(pill) && i < arg.getNumberOfActivePills());
						if(i == arg.getNumberOfActivePills()) {
							return -1;
						}
						else {
							return pill;
						}
				}
				 
			 });
			 
			 //"PacmanToClosestPPillNode",
			 list.add(new Func<Game>() {			
					@Override
					public double calcular(Game arg, Vector<Double> args) {
						// TODO Auto-generated method stub
						int i = 0;
						int pill= 0;
						
						if(arg.getActivePowerPillsIndices().length == 0) {
							return -1;
						}
						do{
							pill= arg.getClosestNodeIndexFromNodeIndex(arg.getPacmanCurrentNodeIndex(), Arrays.copyOfRange(arg.getActivePowerPillsIndices(), i, arg.getNumberOfActivePowerPills() + 1), DM.PATH);
							i++;
						} while(!arg.isNodeObservable(pill) && i < arg.getNumberOfActivePowerPills());
						if(i == arg.getNumberOfActivePowerPills()) {
							return -1;
						}
						else {
							return pill;
						}
				}
				 
			 });
			 
			 
			 //"BLINKYNode",
			 list.add(new Func<Game>() {			
					@Override
					public double calcular(Game arg, Vector<Double> args) {
						// TODO Auto-generated method stub
						int ghost= arg.getGhostCurrentNodeIndex(GHOST.BLINKY);
						if(arg.isNodeObservable(ghost)) {
							return ghost;
						}
						else {
							return -1;
						}
				}
				 
			 });
			 
			 //"PINKYNode",
			 list.add(new Func<Game>() {			
					@Override
					public double calcular(Game arg, Vector<Double> args) {
						// TODO Auto-generated method stub
						int ghost= arg.getGhostCurrentNodeIndex(GHOST.PINKY);
						if(arg.isNodeObservable(ghost)) {
							return ghost;
						}
						else {
							return -1;
						}
				}
				 
			 });
			 
			 //"INKYNode",
			 list.add(new Func<Game>() {			
					@Override
					public double calcular(Game arg, Vector<Double> args) {
						// TODO Auto-generated method stub
						int ghost= arg.getGhostCurrentNodeIndex(GHOST.INKY);
						if(arg.isNodeObservable(ghost)) {
							return ghost;
						}
						else {
							return -1;
						}
				}
				 
			 });
			 
			 //"SUENode",
			 list.add(new Func<Game>() {			
					@Override
					public double calcular(Game arg, Vector<Double> args) {
						// TODO Auto-generated method stub
						int ghost= arg.getGhostCurrentNodeIndex(GHOST.SUE);
						if(arg.isNodeObservable(ghost)) {
							return ghost;
						}
						else {
							return -1;
						}
				}
				 
			 });
		 
		 
		 return list;
		 
	 }
	 
	 
	
	public MsPacmanInput(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void parseInput() {
		msPacmanVar_ = new ArrayList<Pair<String,Double>>();
		List<Func<Game>> lf = generaLista();
		int i = 0;
		for(String s : variables) {
			msPacmanVar_.add(new Pair<String,Double>(s,lf.get(i).calcular(getGame(), null)));
			i++;
		}
	}
	
	public List<Pair<String,Double>> getVarsOfPacMan(){
		return msPacmanVar_;
	}

}
