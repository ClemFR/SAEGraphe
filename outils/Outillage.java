package outils;

import exception.EchecTest;

/**
 * Vérifie que deux résultats soient égaux sous une certaine précision
 */
public class Outillage {

	
	public static boolean AssurerEgaliteDouble(double effectiveResult,
			                                   double waitedResult,
			                                   double precision) {
		return effectiveResult - waitedResult <= precision;
		
	}

}
