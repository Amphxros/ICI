package es.ucm.fdi.ici.c2122.practica4.grupo03.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

public class FuzzyMemory {
	
	public class VarInfo {
		
		int timeNoSee;
		Func<Integer> confdeT;
		Func<Double> cambioSiNoDato;
		double value;
		
		public VarInfo(int time, VarFunctions vf, double v) {
			timeNoSee = time;
			confdeT = vf.getFConfianza();
			cambioSiNoDato = vf.getFValor();
			value = v;
		}
		
		public double getConf() {
			Vector<Double> vec = new Vector<Double>();
			vec.add(Double.valueOf(value));
			return confdeT.calcular(this.timeNoSee, vec);
		}
		
		public void setTimeNoSee(int t) {
			timeNoSee = t;
		}
		
		public void setValue(double val) {
			value = val;
		}
		
		public double getValue() {
			value = cambioSiNoDato.calcular(value, null);
			return value;
		}
		
		public int getTimeNoSee() { return timeNoSee; }
	}
	
	Map<String,VarInfo> mem;
	
	public FuzzyMemory(List<Pair<String, VarFunctions>> lista) {
		mem = new HashMap<String,VarInfo>();
		for(Pair<String,VarFunctions> p : lista) {
			mem.put(p.getFirst(), new VarInfo(0,p.getSecond(), 0));
		}
		
	}
	
	public void update(List<Pair<String,Double>> upVars) {
		
		for(Pair<String,Double> ai : upVars) {
			if(ai.getSecond() == -1) {
				mem.get(ai.getFirst()).setTimeNoSee(mem.get(ai.getFirst()).getTimeNoSee() + 1);
				mem.get(ai.getFirst()).getValue();
			}
			else {
				mem.get(ai.getFirst()).setTimeNoSee(0);
				mem.get(ai.getFirst()).setValue(ai.getSecond());
			}
		}
		
	}
	
	public HashMap<String,Double> getMem(){
		HashMap<String,Double> ls = new HashMap<String,Double>();
		for(Entry<String,VarInfo> e : mem.entrySet()) {
			ls.put(e.getKey(), Double.valueOf(e.getValue().getValue()));
			ls.put(e.getKey()+"Confidence", Double.valueOf(e.getValue().getConf()));
		}
		return ls;
	}
	
	public double getVar(String var){
		return getMem().get(var); 
	}
	
}
