package outils;

import exception.EchecTest;

public class Outillage {

	
	public static boolean AssurerEgaliteDouble(double effectiveResult,
			                                   double waitedResult,
			                                   double precision) {
		return effectiveResult - waitedResult <= precision;
		
	}


	public static void AssurerEgaliteDoubleErreur(double effectiveResult,
											   double waitedResult,
											   double precision) {
		if(!(effectiveResult - waitedResult <= precision)) {
			throw new EchecTest("");
		}

	}
}
