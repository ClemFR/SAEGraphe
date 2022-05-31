package gui;

import objets.Projet;

import java.io.*;
import java.util.Scanner;

public class MenuRacine {

    public static final String PATH_PROJETS = System.getProperty("user.dir") + "\\projets\\";

    public static void main(String[] args) {
        Scanner entree = new Scanner(System.in);
        boolean exit = false;
        int selection;

        // Detection si le dossier de sauvegarde des projets existe.
        File dossierProjets = new File(PATH_PROJETS);
        if (!dossierProjets.exists()) {
            dossierProjets.mkdir();
            System.out.println("Le dossier de sauvegarde des projets n'existait pas, il a été créé.");
        }

        while (!exit) {
            System.out.println("1 - Creer un projet");
            System.out.println("2 - Charger un projet");
            System.out.println("3 - Quitter");
            selection = selecteur(1, 3);

            switch (selection) {
                case 1:
                    System.out.println(" --- Creation d'un projet ---");
                    System.out.println("Entrez le nom du projet : ");
                    String nom = entree.next();
                    System.out.println("Entrez la description du projet : ");
                    String description = entree.next();
                    Projet projet = new Projet(nom, description);
                    new MenuEditionProjet(projet).afficher();
                    System.out.println("");
                    break;
                case 2:
                    System.out.println(" --- Chargement d'un projet ---");

                    File chemin = new File(PATH_PROJETS);
                    File[] listeFichiers = chemin.listFiles();

                    if (listeFichiers == null) {
                        System.out.println("ERREUR! Aucun projet n'a ete trouve");
                        System.out.println("");
                    } else {

                        for (int i = 0; i < listeFichiers.length; i++) {
                            System.out.println((i + 1) + " - " + listeFichiers[i].getName());
                        }

                        selection = selecteur(1, listeFichiers.length);

                        try {
                            FileInputStream fis = new FileInputStream(listeFichiers[selection - 1]);
                            ObjectInputStream ois = new ObjectInputStream(fis);

                            Projet p = (Projet) ois.readObject();
                            ois.close();
                            new MenuEditionProjet(p).afficher();

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
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

    public static int selecteur(int borneMin, int borneMax) {
        Scanner entree = new Scanner(System.in);
        int selection = borneMin - 1;
        do {
            System.out.print("Veuillez effectuer un choix (" + borneMin + " - " + borneMax + ") : ");
            if (entree.hasNextInt()) {
                selection = entree.nextInt();
            } else {
                entree.nextLine();
            }
        } while (selection < borneMin || selection > borneMax);
        System.out.println("");
        return selection;
    }
}
