package tests;

import objets.Duree;
import objets.Projet;
import objets.Tache;

import static tests.Tests.setOfValidTask;

import java.util.ArrayList;

public class TestProjet {
	protected static Projet testProjet() {
		Projet projet = new Projet("Test","test des projets");
		for (int i = setOfValidTask.length - 1; i >= 0; i--) {
			projet.ajouterTache(setOfValidTask[i]);
		}
		return projet;
	}
	@SuppressWarnings("unchecked")
	protected static void InitBd() {
		exceptedResultForGetTaskByPreviousTasks[1].add(setOfValidTask[0]);
		exceptedResultForGetTaskByPreviousTasks[2].add(setOfValidTask[1]);
		exceptedResultForGetTaskByPreviousTasks[2].add(setOfValidTask[2]);
		exceptedResultForGetTaskByPreviousTasks[3].add(setOfValidTask[3]);
		exceptedResultForGetTaskByPreviousTasks[4].add(setOfValidTask[4]);
	}
	@SuppressWarnings("rawtypes")
	protected static ArrayList[] exceptedResultForGetTaskByPreviousTasks = {
			new ArrayList<Tache>(),
			new ArrayList<Tache>(),
			new ArrayList<Tache>(),
			new ArrayList<Tache>(),
			new ArrayList<Tache>(),
	};
	protected static boolean testGetTasksByPreviousTasks() {
		
		Projet projet = testProjet();
		boolean testOk = true;
		ArrayList<Tache> test = new ArrayList<Tache>();
		for (int i = 0; i < exceptedResultForGetTaskByPreviousTasks.length; i++) {
			testOk = exceptedResultForGetTaskByPreviousTasks[i].size() == test.size();
			test = projet.getTachesSuivantes(test);
			
		}
		return testOk;
	}
	protected static void testOrderTasks() {
		Projet projet = testProjet();
		projet.calculDate();
		System.out.println(projet.afficherTaches());
	}
	protected static Tache[] projetDeux = {
			new Tache("1","1",new Duree(1)),
			new Tache("2","2",new Duree(2)),
			new Tache("3","3",new Duree(3)),
			new Tache("4","4",new Duree(4)),
			new Tache("5","5",new Duree(5)),
			new Tache("6","6",new Duree(6)),
			new Tache("7","7",new Duree(7)),
			new Tache("8","8",new Duree(8))
			
			
	};
	protected static void testProjetDeux() {
		Projet projet = new Projet("ProjetTest","test");
		
		projetDeux[1].addTachePrecedente(projetDeux[0]);
		projetDeux[2].addTachePrecedente(projetDeux[0]);
		projetDeux[3].addTachePrecedente(projetDeux[0]);
		projetDeux[4].addTachePrecedente(projetDeux[1]);
		projetDeux[5].addTachePrecedente(projetDeux[2]);
		projetDeux[6].addTachePrecedente(projetDeux[3]);
		projetDeux[7].addTachePrecedente(projetDeux[6]);
		projetDeux[7].addTachePrecedente(projetDeux[5]);
		
		for (int i = 0; i < projetDeux.length; i++) {
			projet.ajouterTache(projetDeux[i]);
		}
		projet.calculDate();
		System.out.println(projet.afficherTaches());
	}
}
