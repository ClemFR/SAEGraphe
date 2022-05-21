/* TestDuree.java                                                     17/04/2022
 *
 */
package tests;

import static java.lang.Double.MAX_VALUE;
import static outils.Outillage.AssurerEgaliteDoubleErreur;

import exception.EchecTest;
import objets.Duree;
/**
 * Teste la classe Duree.
 */
public class TestDuree {

	private Duree[] dureesValides = {
			new Duree(       80),
			new Duree(    80,23),
			new Duree(  40,8,23),
			new Duree(  45,5,12),
			new Duree(  45,5,12)
	};

	public TestDuree() {
	}
	
	/* JEU DE TEST DESTINE A ETRE UTILISE POUR
	 * TESTER LE CONSTRUCTEUR DE LA CLASSE DUREE:  */

	
	public void testConstructor () {
		double[][] listeHorairesErreur = {

				/* Semaines  |   Jours   |  Heures  */
				{         -10,          0,          0},
				{   MAX_VALUE,  MAX_VALUE,  MAX_VALUE}, // On devrait peut être interdire des valeurs trop grande ( pas fait)
				{           0,        -10,          0},
				{           0,          0,        -10},
		};

		for (int i = 0; i < listeHorairesErreur.length; i++) {
			try {
				new Duree(listeHorairesErreur[i][0],
						  listeHorairesErreur[i][1],(int) listeHorairesErreur[i][2]);
				throw new EchecTest("Une duree invalide passee");
			} catch (Exception e) {
			}
		}
		
	}
	

	public void testToString() {
		String[] resultatsAttendus = {
				/* Dans le cas où : 24h -> 1j | 7j -> 1s | 4s -> 1m (ou 28j -> 1m)*/
				"3 jours, 8 heures",
				"11 semaines, 3 jours, 23 heures",
				"41 semaines, 1 jours, 23 heures",
				"45 semaines, 5 jours, 12 heures"

		};

		for (int i = 0; i < resultatsAttendus.length; i++) {
			if (!resultatsAttendus[i].equals(dureesValides[i].toString())) {
				throw new EchecTest("Echec horaire toString");
			}
		}
	}

	public void testGetDuree() {
		double[][] expectedResultForGetDuree = {
				{80,3.33333,0.47619,0.11904},
				{1943,80.95833,11.56547,2.89136},
				{6935,288.95833,41.27976,10.31994},
				{7692,320.5,45.78571,11.44642},
				{7692,320.5,45.78571,11.44642}
		};

		double precision = 1e-5;
		for (int i = 0; i < expectedResultForGetDuree.length; i++) {
			AssurerEgaliteDoubleErreur(dureesValides[i].getDuree('h'), expectedResultForGetDuree[i][0], precision);
			AssurerEgaliteDoubleErreur(dureesValides[i].getDuree('j'), expectedResultForGetDuree[i][1], precision);
			AssurerEgaliteDoubleErreur(dureesValides[i].getDuree('s'), expectedResultForGetDuree[i][2], precision);
		}
		
	}
    


}
