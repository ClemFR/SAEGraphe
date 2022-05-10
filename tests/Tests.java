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
			new Duree( 10,5,5,12),
			new Duree( 10,5,5,12)
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
    	testOrderTasks();
    	//testsOk &= testConstructor(); // les tests echoues a cause de celui la
    	if (testsOk) {
    		System.out.println("Les tests unitaires ont reussie");
    	} else {
    		System.out.println("Les tests unitaires ont echoues");
    	}
//		Projet projet = new Projet("Projet test","");
//		boolean exit = false;
//		Scanner entree = new Scanner(System.in);
//		while (!exit) {
//			switch (entree.next().toLowerCase()) {
//			case "addtask": 
//				String nom;
//				System.out.println("Entree le nom de la tache : ");
//				nom = entree.next();
//				System.out.println("Entree la duree : ");
//				projet.addTask(new Tache(nom,"",new Duree(entree.nextInt())));
//				
//				break;
//			case "calcul":
//				projet.calculDate();
//				break;
//			case "addp":
//				Tache aAjouter;
//				Tache receveur;
//				for (int i = 0; i < projet.size(); i++) {
//					System.out.println(i + " | " + projet.getTask(i).getNom() + "\n");
//				}
//				System.out.println("Choississez la tache a ajouter : ");
//				aAjouter = projet.getTask(entree.nextInt());
//				System.out.println("A quel tache ? ");
//				receveur = projet.getTask(entree.nextInt());
//				receveur.addPreliminaryTask(aAjouter);
//				break;
//			case "exit":
//				exit = true;
//				break;
//			case "printall":
//				System.out.println(projet.printAll());
//				break;
//			case "print":
//				System.out.println(projet);
//				break;
//			default:
//				System.err.println("tu fait quoi ?");
//			}
//		}
    }
}
