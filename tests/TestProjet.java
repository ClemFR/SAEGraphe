package tests;

import objets.Projet;
import static tests.Tests.setOfValidTask;

public class TestProjet {
	protected static void testProjet() {
		Projet projet = new Projet("Test","test des projets");
		for (int i = 0; i < setOfValidTask.length; i++) {
			projet.addTask(setOfValidTask[i]);
		}
		projet.Calculate();
		System.out.println(projet.printAll());
	}
}
