package es.ucm.fdi.ici.c2122.practica4.grupo03.utils;

import java.util.Vector;

public enum FuncionCalcularVariable {
	
	PREVVALUE (new Func<Double>() {
		@Override
		public double calcular(Double tiempo, Vector<Double> args) {
			// TODO Auto-generated method stub
			return tiempo;
		}
	}, "PREVVALUE"),
	
	INCREASEBYARG(new Func<Double>() {

		@Override
		public double calcular(Double tiempo, Vector<Double> args) {
			// TODO Auto-generated method stub
			return args.get(0) + tiempo;
		}
		
	}, "INCREASEBYARG"),
	
	DECREASEBYARG(new Func<Double>() {

		@Override
		public double calcular(Double tiempo, Vector<Double> args) {
			// TODO Auto-generated method stub
			if(tiempo == 0) return tiempo;
			return tiempo - args.get(0);
		}
		
	}, "DECREASEBYARG"),
	
	MINUSONE(new Func<Double>() {

		@Override
		public double calcular(Double tiempo, Vector<Double> args) {
			// TODO Auto-generated method stub
			if(tiempo == 0) { return 0; }
			return tiempo - 1;
		}
		
		
	}, "MINUSONE"),
	
	PLUSONE(new Func<Double>() {

		@Override
		public double calcular(Double arg, Vector<Double> args) {
			// TODO Auto-generated method stub
			return arg+1;
		}
		
	}, "PLUSONE");
	
	Func<Double> funcion;
	String rep;
	
	FuncionCalcularVariable(Func<Double>f, String s) {
		funcion = f;
		rep = s;
	}
	
	public String toString() {
		return rep;
	}
	
	public Func<Double> getFuncion(){ return funcion;}
}
