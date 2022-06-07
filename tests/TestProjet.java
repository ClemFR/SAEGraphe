package tests;

import objets.Duree;
import objets.Evenement;
import objets.Projet;
import objets.Tache;
import objets.Duree;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import exception.CycleException;


/**
 * Tests de {@link objets.Projet}
 */
public class TestProjet {

	private Projet projet = new Projet("Test" ,"description test");
	
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
        },
        {
        	new Tache("t1","d1",new Duree(1)),
        	new Tache("t2","d2",new Duree(2)),
        	new Tache("t3","d3",new Duree(3)),
        	new Tache("t4","d4",new Duree(4)),
        	new Tache("t5","d5",new Duree(5)),
        	new Tache("t6","d6",new Duree(6)),
        	new Tache("t7","d7",new Duree(7)),
        	new Tache("t8","d8",new Duree(8)),
        },
        {
        	new Tache("t1","d1",new Duree(1)),
        	new Tache("t2","d2",new Duree(2)),
        	new Tache("t3","d3",new Duree(3)),
        	new Tache("t4","d4",new Duree(4)),
        	new Tache("t5","d5",new Duree(5)),
        	new Tache("t6","d6",new Duree(6)),
        	new Tache("t7","d7",new Duree(7)),
        	new Tache("t8","d8",new Duree(8)),
        	new Tache("t9","d9",new Duree(9)),
        	new Tache("t10","d10",new Duree(10)),
        	new Tache("t11","d12",new Duree(11)),
        	new Tache("t12","d13",new Duree(12)),
        	new Tache("t13","d14",new Duree(13)),
			//new Tache ("f1", "fictive 1", new Duree(0)),
			//new Tache ("f2", "fictive 2", new Duree(0))
        },
		{
			new Tache("t1", "d1", new Duree(1)),
			new Tache("t2", "d2", new Duree(2)),
			new Tache("t3", "d3", new Duree(3)),
			new Tache("t4", "d4", new Duree(4)),
			new Tache("t5", "d5", new Duree(5)),
			new Tache("t6", "d6", new Duree(6)),
			new Tache("t7", "d7", new Duree(7)),
			new Tache("t8", "d8", new Duree(8)),
		},
		{
			new Tache("A", "d1", new Duree(1)),
			new Tache("B", "d2", new Duree(7)),
			new Tache("C", "d3", new Duree(6)),
			new Tache("D", "d4", new Duree(5)),
			new Tache("E", "d5", new Duree(4)),
			new Tache("F", "d6", new Duree(7)),
			new Tache("G", "d7", new Duree(15)),
			new Tache("H", "d8", new Duree(4)),
		}

    };
    /*
     * Destiné a reférencer le tableau "JEUX_DE_DONNES"
     */
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
            {               3,                                  0        },
            {               4,                                  1        },
            {               5,                                  2        },
            {               6,                                  3        },
            {               7,                                  5        },
            {               7,                                  6        }
        },
        {
				{2,0},
				{2,4},
				{3,0},
				{3,4},
				{4,5},
				{5,1},
				{6,1},
				{7,5},
				{8,5},
				{9,6},
				{10,2},
				{11,2},
				{11,4},
				{11,7},
				{12,8},

        },
		{
				{1,0},
				{2,0},
				{3,1},
				{4,3},
				{6,4},
				{5,2},
				{5,2},
				{7,5},
				{7,6}

		},
		{
				{2,0},
				{3,0},
				{2,1},
				{3,1},
				{4,1},
				{7,4},
				{7,3},
				{6,0},
				{6,4},
				{5,2},
				{5,3}
		}
    };


    private Tache[] donneesExploitable;
    
    /**
     * 
     * Tests de {@link objets.Projet#Projet(String, String)}
     * @param jeuDeDonnees choix du jeu de donner relatif a l'instance
     *                     de l'objet
     * @param indiceDependance choix des dependance entre les taches
     */
    public TestProjet(int jeuDeDonnees,int indiceDependance) {
        donneesExploitable = JEUX_DE_DONNEES[jeuDeDonnees];
        for (int i = 0; i < listeContraintes[indiceDependance].length; i++) {
            try  {
                donneesExploitable[listeContraintes[indiceDependance][i][0]]
                .addTachePrecedente(donneesExploitable[listeContraintes[indiceDependance][i][1]]);
            } catch (CycleException e ){
                System.out.println(e.getMessage());
            }
            
        }
        for (Tache t : donneesExploitable) {

        	projet.addTache(t);
        	
        }
        
        
    }
    
    /**
     * Tests de {@link objets.Projet#afficherTaches()}
     */
    public void test() {

    	projet.calculDesDates();
    	System.out.println(projet.afficherTaches());
		System.out.println("---------------");
		ArrayList<Tache> cheminCritique = projet.getCheminCritique();
		String chemin = "";
		for (Tache t : cheminCritique) {
			chemin += t.getNom() + " - ";
		}
		chemin = chemin.substring(0, chemin.length()-2);
		System.out.println(chemin);
	}

    public void sauvegarder() {
		File chemin = new File("./projets/test.txt");

		if (chemin.exists()) {
			chemin.delete();
		}

		try {
    	    projet.sauvegarder(chemin);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	public void charger() {
		File chemin = new File("./projets/test.txt");
		try {
			projet = new Projet(chemin);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
