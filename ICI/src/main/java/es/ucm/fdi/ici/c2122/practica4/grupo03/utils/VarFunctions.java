package es.ucm.fdi.ici.c2122.practica4.grupo03.utils;

public class VarFunctions{
	
	Func<Integer> funcionConfianza;
	Func<Double>  funcionValor;
	
	public VarFunctions(Func<Integer> i, Func<Double> d) {
		funcionConfianza = i;
		funcionValor = d;
	}
	
	public Func<Integer> getFConfianza(){ return funcionConfianza; }
	public Func<Double>  getFValor() { return funcionValor; }
}