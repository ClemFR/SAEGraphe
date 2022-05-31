package outils;

import exception.EchecTest;

/**
 * V�rifie que deux r�sultats soient �gaux sous une certaine pr�cision
 */
public class Outillage {

	
	public static boolean AssurerEgaliteDouble(double effectiveResult,
			                                   double waitedResult,
			                                   double precision) {
		return effectiveResult - waitedResult <= precision;
		
	}

}
