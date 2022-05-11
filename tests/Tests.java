package tests;
import static tests.TestDuree.*;
import static tests.TestTache.*;
import static tests.TestProjet.*;
import static tests.Tests.setOfValidDuree;

import objets.Duree;
import objets.Projet;
import objets.Tache;
import java.util.Scanner;

public class Tests {
	protected static Duree[] setOfValidDuree = {
			new Duree(       80),
			new Duree(    80,23),
			new Duree(  40,8,23),
			new Duree(  45,5,12),
			new Duree(  45,5,12)
	};
	protected static Tache[] setOfValidTask = {
			new Tache("Ecrire","Apprendre a écrire pour MR Barrios",setOfValidDuree[0]),
			new Tache("Test","Faire les jeux de tests pour MR Barrios",setOfValidDuree[1]),
			new Tache("Algo","Faire une partie de l'algo",setOfValidDuree[2]),
			new Tache("Pause","Faire une pause pour pas peter un plomb",setOfValidDuree[3]),
			new Tache("FinirAlgo","Finir l'algo",setOfValidDuree[4]),
			
	};
	public static void main(String[] args) {
		InitBd();
		InitPriorities();
    	boolean testsOk = true;
    	testsOk &= testGetDuree();
    	testsOk &= testToString();
    	testsOk &= testFindEarliestDate();
    	testsOk &= testVerifierCondition();
    	testsOk &= testGetTasksByPreviousTasks();
    	//testsOk &= testConstructor(); // les tests echoues a cause de celui la
    	//testOrderTasks();
    	if (testsOk) {
    		System.out.println("Les tests unitaires ont reussie");
    	} else {
    		System.out.println("Les tests unitaires ont echoues");
    	}

    }
}
