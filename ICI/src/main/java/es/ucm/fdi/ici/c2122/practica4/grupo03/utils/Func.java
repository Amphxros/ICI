package es.ucm.fdi.ici.c2122.practica4.grupo03.utils;

import java.util.Vector;

public interface Func<T> {
	public double calcular(T arg, Vector<Double> args);
}
