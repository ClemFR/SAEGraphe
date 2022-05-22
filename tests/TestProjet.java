package tests;

import objets.Projet;
import objets.Tache;
import objets.Duree;


import java.util.ArrayList;

public class TestProjet {
	private Projet projet;

	public TestProjet(ArrayList<Tache> donnees) {
		for (Tache donnee : donnees) {
			projet.addTache(donnee);
		}
	}

	
}
