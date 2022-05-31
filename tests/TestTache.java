package tests;

import objets.Duree;
import objets.Evenement;
import objets.Projet;
import objets.Tache;
import exception.EchecTest;
import exception.CycleException;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Tests de {@link objets.Tache}
 */
public class TestTache {

	/**********************             JEUX DE DONNEES              **********************/

	private Duree[] dureesValides = {
			new Duree(       80),
			new Duree(    80,23),
			new Duree(  40,8,23),
			new Duree(  45,5,12),
			new Duree(  45,5,12)
	};


	private final Tache[][] JEUX_DE_DONNEES = {

			{       
	            new Tache(         "Ecrire","la specification des besoins", dureesValides[0]),
	            new Tache(               "Test","Faire un test qui echoue", dureesValides[1]),
	            new Tache(             "Algo","Tenter de resoudre le test", dureesValides[2]),
	            new Tache("Presentation","Presentation du travail realise", dureesValides[3]),
	            new Tache(   "Correction","Correction des points negatifs", dureesValides[4])
	        }

	};

	private final int[][][] listeContraintes = {
			{ /* Les taches aux quelles ont ajoute          Les contraintes */
				{               1,                                  0        },
				{               2,                                  0        },
				{               4,                                  3        },
				{               3,                                  1        },
				{               3,                                  2        }
			},
			{
				{               1,                                  0        },
				{               2,                                  0        },
				{               4,                                  3        },
				{               4,                                  1        },
				{               3,                                  2        }
			}
	};



	private Tache[] donneesExploitable;
	
	/**
	 * 
     * Tests de {@link objets.Tache#Tache(String, String, Duree)}
     *
	 * @param jeuDeDonnees choix du jeu de donner relatif a l'instance
	 *                     de l'objet
	 * @param indiceDependance choix des dependance entre les taches
	 */
	public TestTache(int jeuDeDonnees,int indiceDependance) {
		donneesExploitable = JEUX_DE_DONNEES[jeuDeDonnees];
		for (int i = 0; i < listeContraintes[indiceDependance].length; i++) {
			try  {
				donneesExploitable[listeContraintes[indiceDependance][i][0]]
						.addTachePrecedente(donneesExploitable[listeContraintes[indiceDependance][i][1]]);
			} catch (CycleException e ){
				System.out.println(e.getMessage());
			}

		}
	}






	/**
	 * 
     * Tests de {@link objets.Tache#trouverDatePlusTot()}
     *
	 * @param exceptedEarliestDate tableau de resultat attendu a comparer avec les calculs
	 */
	protected void testTrouverDatePlusTot(int[] exceptedEarliestDate) {
		for (Tache t : donneesExploitable) {
			t.setEvenementOrigine(new Evenement());
		}
		for (int i = 0; i < exceptedEarliestDate.length; i++) {
			donneesExploitable[i].trouverDatePlusTot();
			if (donneesExploitable[i].getDatePlusTot().getHeures() != exceptedEarliestDate[i]) {

				throw new EchecTest("Echec Tache trouverDatePlusTot test no " + i);
			}
		}
	}




	/**
	 * Tests de {@link objets.Tache#detectionCircuit(Tache)}
	 */
	protected void testCycle() {


		try {
			donneesExploitable[2].addTachePrecedente(donneesExploitable[3]);
			throw new EchecTest("Oups un cycle n'a pas �t� detect� !");
		} catch (CycleException e) {
		}

	}



	/**
	 * Tests de {@link objets.Tache#addTachePrecedente(Tache)}
	 */

	protected void testAjoutTachePrealableExistante() {
		try {
			JEUX_DE_DONNEES[0][4].addTachePrecedente(JEUX_DE_DONNEES[0][3]);
			throw new EchecTest("Tache prealable ajoutée en double");
		} catch (IllegalArgumentException erreurAttendue) {
			System.out.println("Test 'testAjoutTachePrealableExistante()' reussi");
		}
	}
}
