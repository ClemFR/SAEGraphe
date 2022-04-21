package info1.graphe.outils;

public class Outillage {

	
	public static boolean AssurerEgaliteDouble(double effectiveResult,
			                                   double waitedResult,
			                                   double precision) {
		return effectiveResult - waitedResult <= precision;
		
	} 
}
