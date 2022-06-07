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

    /**
     * Affichage des commandes de base du logiciel.
     * @param args non utilisé
     */
    public static void main(String[] args) {
        boolean exit = false;
        int selection;

        // Detection si le dossier de sauvegarde des projets existe.
        File dossierProjets = new File(PATH_PROJETS);
        if (!dossierProjets.exists()) {
            dossierProjets.mkdir();
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

                    File chemin = new File(PATH_PROJETS);
                    File[] listeFichiers = chemin.listFiles();

                    if (listeFichiers.length == 0) {
                        System.out.println("ERREUR! Aucun projet n'a ete trouve");
                        System.out.println("");
                    } else {

                        for (int i = 0; i < listeFichiers.length; i++) {
                            System.out.println((i + 1) + " - "
                                              + listeFichiers[i].getName());
                        }

                        selection = selecteur(1, listeFichiers.length);
                        try {
                            Projet projetCharge = new Projet(listeFichiers[selection - 1]);
                            new MenuEditionProjet(projetCharge).afficher();
                        } catch (IOException e) {
                            System.out.println("ERREUR! Le fichier n'a pas pu etre ouvert");
                        }
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

    private static void charger() {

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
