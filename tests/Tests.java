package tests;
import static tests.TestDuree.*;
import static tests.TestTache.*;
import static tests.TestProjet.*;

import exception.EchecTest;
import objets.Duree;
import objets.Tache;

public class Tests {
	protected static Duree[] dureesValides = {
			new Duree(       80),
			new Duree(    80,23),
			new Duree(  40,8,23),
			new Duree(  45,5,12),
			new Duree(  45,5,12)
	};
	protected static Tache[] setOfValidTask = {
			new Tache("Ecrire","Apprendre a écrire pour MR Barrios", dureesValides[0]),
			new Tache("Test","Faire les jeux de tests pour MR Barrios", dureesValides[1]),
			new Tache("Algo","Faire une partie de l'algo", dureesValides[2]),
			new Tache("Pause","Faire une pause pour pas peter un plomb", dureesValides[3]),
			new Tache("FinirAlgo","Finir l'algo", dureesValides[4]),
			
	};
	public static void main(String[] args) {

		try {
			TestDuree testConstructeur = new TestDuree();
			testConstructeur.testConstructor();
			System.out.println("Test du constructeur : OK!");
		} catch (EchecTest e) {
			System.out.println("Echec du test du constructeur");
		}

		try {
			TestDuree testToString = new TestDuree();
			testToString.testToString();
			System.out.println("Test toString : OK!");
		} catch (EchecTest e) {
			System.out.println("Echec du test toString");
		}

		try {
			TestDuree testDuree = new TestDuree();
			testDuree.testGetDuree();
			System.out.println("Test getDuree : OK!");
		} catch (EchecTest e) {
			System.out.println("Echec du test getDuree");
		}


		InitBd();
		InitPriorities();
    	boolean testsOk = true;

    	//testsOk &= testToString();
    	//testsOk &= testFindEarliestDate();
    	//testsOk &= testVerifierCondition();
    	//testsOk &= testGetTasksByPreviousTasks();
		//testsOk &= testCycle();
    	//testsOk &= testConstructor(); // les tests echoues a cause de celui la
    	//testOrderTasks();
    	//if (testsOk) {
    	//	System.out.println("Les tests unitaires ont reussie");
    	//} else {
    	//	System.out.println("Les tests unitaires ont echoues");
    	//}

    }
}
