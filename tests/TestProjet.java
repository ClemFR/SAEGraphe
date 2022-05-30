package tests;

import objets.Duree;
import objets.Evenement;
import objets.Projet;
import objets.Tache;

import exception.CycleException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class TestProjet {
	
	private Projet projet = new Projet("Test" ,"Test");
	
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
        	new Tache("1","",new Duree(1)),
        	new Tache("2","",new Duree(2)),
        	new Tache("3","",new Duree(3)),
        	new Tache("4","",new Duree(4)),
        	new Tache("5","",new Duree(5)),
        	new Tache("6","",new Duree(6)),
        	new Tache("7","",new Duree(7)),
        	new Tache("8","",new Duree(8)),
        	
        	
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
        }
    };



    private Tache[] donneesExploitable;
    /**
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
        	t.setEvenementOrigine(new Evenement());
        	projet.addTache(t);
        	
        }
        
        
    }
    public void test() {
    	
    	projet.calculDesDates();
    	System.out.println(projet.afficherTaches());
    }

    public void testSauveragrde() {
    	File f = new File("testSvg.ser");
        try {
            FileOutputStream fos = new FileOutputStream(f.getAbsoluteFile());
            ObjectOutputStream oos =  new ObjectOutputStream(fos) ;
            oos.writeObject(projet);
            fos.close();
            System.out.println("Sauvegarde effectuée");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
