/* TestDuree.java                                                     17/04/2022
 *
 */
package tests;

import static java.lang.Double.MAX_VALUE;
import static outils.Outillage.AssurerEgaliteDouble;
import static tests.Tests.setOfValidDuree;

import objets.Duree;
/**
 * Teste la classe Duree.
 */
public class TestDuree {
	
	/* JEU DE TEST DESTINE A ETRE UTILISE POUR
	 * TESTER LE CONSTRUCTEUR DE LA CLASSE DUREE:  */
	protected static double[][] setOfUniteOfTime = {
			
			/*   Mois   |  Semaines  |   Jours   |  Heures  */
			{        -10,           0,          0,          0},
			{          0,         -10,          0,          0},
			{          0,           0,        -10,          0},
			{          0,           0,          0,        -10},
			{          0,           0,          0,          0}, // Une duree ne devrait idealement pas pouvoir etre nulle
			{  MAX_VALUE,   MAX_VALUE,  MAX_VALUE,  MAX_VALUE} // On devrait peut être interdire des valeurs trop grande ( pas fait)
			
	};
	
	protected static boolean testConstructor () {
		boolean testOk = true;
		
		for (int i = 0; i < setOfUniteOfTime.length; i++) {
			try {
				new Duree(setOfUniteOfTime[i][0],setOfUniteOfTime[i][1],
						  setOfUniteOfTime[i][2],(int) setOfUniteOfTime[i][3]);
				testOk = false;
			} catch (Exception e) {
			}
		}
		
		
		return testOk;
	}
	
	protected static String[] expectedResultForToString = {
			/* Dans le cas où : 24h -> 1j | 7j -> 1s | 4s -> 1m (ou 28j -> 1m)*/
			"3 jours, 8 heures",
			"2 mois, 3 semaines, 3 jours, 23 heures",
			"10 mois, 1 semaines, 1 jours, 23 heures",
			"11 mois, 1 semaines, 5 jours, 12 heures"
			
	};
	protected static boolean testToString() {
		boolean testOk = true;
		
		for (int i = 0; i < expectedResultForToString.length; i++) {
			testOk &= expectedResultForToString[i].equals(setOfValidDuree[i].toString());
		}
		return testOk;
	}
	protected static double[][] expectedResultForGetDuree = {
			{80,3.33333,0.47619,0.11904},
			{1943,80.95833,11.56547,2.89136},
			{6935,288.95833,41.27976,10.31994},
			{7692,320.5,45.78571,11.44642},
			{7692,320.5,45.78571,11.44642}
	};
	protected static boolean testGetDuree() {
		double precision = 1e-5;
		boolean testOk = true;
		for (int i = 0; i < expectedResultForGetDuree.length; i++) {
			testOk &= AssurerEgaliteDouble(setOfValidDuree[i].getDuree('h'), expectedResultForGetDuree[i][0], precision);
			testOk &= AssurerEgaliteDouble(setOfValidDuree[i].getDuree('j'), expectedResultForGetDuree[i][1], precision);
			testOk &= AssurerEgaliteDouble(setOfValidDuree[i].getDuree('s'), expectedResultForGetDuree[i][2], precision);
			testOk &= AssurerEgaliteDouble(setOfValidDuree[i].getDuree('m'), expectedResultForGetDuree[i][3], precision);
		}
		
		return testOk;
	}
    


}
