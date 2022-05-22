package tests;
import static tests.TestDuree.*;
import static tests.TestTache.*;
import static tests.TestProjet.*;

import exception.EchecTest;
import objets.Duree;
import objets.Tache;

public class Tests {

    /**********************             JEUX DE DONNEES              **********************/

    /* Ce tableau est interpreter pour ajouter des conditions dans une ArrayList 
     * Il est utiliser pour la fonction testVerifierCondition de TestTache
     */
    private static int[][] contenueDesConditions = {
            {},
            {0},
            {1,2},
            {3}
    };
    /**********************             RESULTATS ATTENDUES             **********************/

    private static String[] resultatAttendueToStringDuree = {
                /* Dans le cas où : 24h -> 1j | 7j -> 1s */
                "3 jours, 8 heures",
                "11 semaines, 3 jours, 23 heures",
                "41 semaines, 1 jours, 23 heures",
                "45 semaines, 5 jours, 12 heures"

    };



    private static double[][] resultatsAttenduGetDuree = {
                {  80,  3.33333, 0.47619,  0.11904},
                {1943, 80.95833,11.56547,  2.89136},
                {6935,288.95833,41.27976, 10.31994},
                {7692,    320.5,45.78571, 11.44642},
                {7692,    320.5,45.78571, 11.44642}
    };

    private static int[] resultatsAttenduCalculDatePlusTot = {
            0,80,80,7015,14707
    };

    /* Chaque ligne du tableau représente les resultats attendue pour 
     * une variation des taches contenue dans le filtre
     * 
     */
    private static boolean[][] resultatsAttenduVerifConditions = {
            {true,false,false,false,false},
            {false,true,true,false,false},
            {false,false,false,true,false},
            {false,false,false,false,true}
    };



    /**
     * Lancement de tout les tests unitaires :
     *  - Test du constructeur de 'Duree'
     *  - Test de la conversion de 'Duree' en String
     *  - Test de la conversion de 'Duree' en differentes unite de temp
     *  - Test pour trouver la date au plus tot d une 'Tache'
     *  - Test pour trouver les predecesseur d une Tache dans un enssemble
     *  - Test pour la detection de cycle lors d'ajout d'une tache prealable a une autre
     */ 
    public static void main(String[] args) {


        try {
            TestDuree testConstructeur = new TestDuree();
            testConstructeur.testConstructor();
            System.out.println("Test du constructeur : OK!");
        } catch (EchecTest e) {
            System.out.println(e.getMessage());
        }

        try {
            TestDuree testToString = new TestDuree();
            testToString.testToString(resultatAttendueToStringDuree);
            System.out.println("Test toString : OK!");
        } catch (EchecTest e) {
            System.out.println(e.getMessage());
        }

        try {
            TestDuree testDuree = new TestDuree();
            testDuree.testGetDuree(resultatsAttenduGetDuree);
            System.out.println("Test getDuree : OK!");
        } catch (EchecTest e) {
            System.out.println(e.getMessage());
        }

        try {
            TestTache testTrouverDatePlusTot = new TestTache(0,0);
            testTrouverDatePlusTot.testFindEarliestDate(resultatsAttenduCalculDatePlusTot);
            
            System.out.println("Test trouverDatePlusTot : OK!");
        } catch (EchecTest e){
            System.out.println(e.getMessage());
        }
        try {
            TestTache testVerifPredecesseurs = new TestTache(0,0);
            testVerifPredecesseurs.testVerifierCondition(resultatsAttenduVerifConditions[0],contenueDesConditions[0]);
            testVerifPredecesseurs.testVerifierCondition(resultatsAttenduVerifConditions[1],contenueDesConditions[1]);
            testVerifPredecesseurs.testVerifierCondition(resultatsAttenduVerifConditions[2],contenueDesConditions[2]);
            testVerifPredecesseurs.testVerifierCondition(resultatsAttenduVerifConditions[3],contenueDesConditions[3]);

            System.out.println("Test verifierPredecesseurs : OK!");
        } catch (EchecTest e){
            System.out.println(e.getMessage());
        }
        try {
            TestTache testCycle = new TestTache(0,0);
            testCycle.testCycle();
            System.out.println("Test verif cycle : OK!");
        } catch (EchecTest e) {
            System.out.println(e.getMessage());
        }

    }
}
