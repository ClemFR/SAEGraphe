package outils;

public class Outillage {

	
	public static boolean AssurerEgaliteDouble(double effectiveResult,
			                                   double waitedResult,
			                                   double precision) {
		return effectiveResult - waitedResult <= precision;
		
	} 
}