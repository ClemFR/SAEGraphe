package gui;

import objets.Projet;

import java.io.*;
import java.util.Scanner;

/**
 * Menu principal de gestion de projet. Permet de charger un projet existant ou de créer
 * un nouveau projet.
 * @author Clement L. & Eleanor M. & Charlie S-B. & Guillaume M. & Diego I.
 */
public class MenuRacine {

    /**
     * Chemins du dossier contant les fichiers de sauvegarde
     */
    public static final String PATH_PROJETS = System.getProperty("user.dir")
                                              + "\\projets\\";
    public static final File SAUVEGARDES = new File(PATH_PROJETS);
    /**
     * Affichage des commandes de base du logiciel.
     * @param args non utilisé
     */
    public static void main(String[] args) {
        boolean exit = false;
        int selection;

        // Detection si le dossier de sauvegarde des projets existe.
        if (!SAUVEGARDES.exists()) {
        	SAUVEGARDES.mkdir();
            System.out.println("Le dossier de sauvegarde des projets n'existait pas, "
                              + "il a été créé.");
        }

        while (!exit) {
            System.out.println("1 - Creer un projet");
            System.out.println("2 - Charger un projet");
            System.out.println("3 - Quitter");
            selection = selecteur(1, 3);

            switch (selection) {
                case 1:
                    System.out.println(" --- Creation d'un projet ---");
                    String nom = saisieTexte("Entrez le nom du projet : ");
                    String description = saisieTexte("Entrez la description du projet : ");

                    Projet projet = new Projet(nom, description);
                    new MenuEditionProjet(projet).afficher();
                    System.out.println("");
                    break;
                case 2:
                	System.out.println(" --- Chargement d'un projet ---");
                	File[] listeFichiers = SAUVEGARDES.listFiles();
                	for (int i = 0; i < listeFichiers.length; i++) {
                		System.out.println((i + 1) + " - "
                				+ listeFichiers[i].getName());
                	}
                	try {
                		Projet selectionner = charger(selecteur(1, listeFichiers.length),listeFichiers);
                		new MenuEditionProjet(selectionner).afficher();
                	} catch (IOException e) {
                		e.printStackTrace();
                	}
                    System.out.println("");
                    break;
                case 3:
                    System.out.println(" --- Quitter ---");
                    exit = true;
                    break;
            }
        }
    }

    private static Projet charger(int noFichier, File[] listeFichiers) throws IOException {
    	
        if (listeFichiers.length == 0) {
            throw new FileNotFoundException();
        }
		return new Projet(listeFichiers[noFichier - 1]);
    }

    public static int selecteur(int borneMin, int borneMax) {
        Scanner entree = new Scanner(System.in);
        int selection = borneMin - 1;
        do {
            System.out.print("Veuillez effectuer un choix (" + borneMin + " - "
                            + borneMax + ") : ");
            if (entree.hasNextInt()) {
                selection = entree.nextInt();
            } else {
                entree.nextLine();
            }
        } while (selection < borneMin || selection > borneMax);
        System.out.println("");
        return selection;
    }

    public static String saisieTexte(String message) {
        Scanner entree = new Scanner(System.in);
        System.out.print(message);
        String saisie = entree.nextLine();
        return saisie;
    }

    public static int saisieEntier(String message) {
        Scanner entree = new Scanner(System.in);
        do {
            System.out.print(message);
        } while (!entree.hasNextInt());
        int saisie = entree.nextInt();
        entree.nextLine();
        return saisie;
    }
}
