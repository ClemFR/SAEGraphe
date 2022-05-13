package tests;

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
}
