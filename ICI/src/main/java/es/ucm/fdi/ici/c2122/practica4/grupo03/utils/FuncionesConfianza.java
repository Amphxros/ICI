package es.ucm.fdi.ici.c2122.practica4.grupo03.utils;

import java.util.Vector;

public enum FuncionesConfianza {
	
	CONFIANZA_GAUSS (new Func<Integer>() {
		@Override
		public double calcular(Integer tiempo, Vector<Double> args) {
			// TODO Auto-generated method stub
			return 100 * Math.exp(-1 * tiempo * tiempo / 1500);
		}
	}),
	
	FULLCONFIDENCE(new Func<Integer>() {
		@Override
		public double calcular(Integer tiempo, Vector<Double> args) {
			// TODO Auto-generated method stub
			return 100;
		}
		
	});
	
	

	Func<Integer> funcion;
	
	public final Func<Integer> func;
	
	FuncionesConfianza(Func<Integer> f){
		this.func = f;
	}
	
	public Func<Integer> getFunction() { return func; }

}
