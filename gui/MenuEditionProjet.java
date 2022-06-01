package gui;

import objets.Duree;
import objets.Projet;
import objets.Tache;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static gui.MenuRacine.*;

/**
 * Gère l'édition d'un projet chargé préalablement
 * @author Clement L. & Eleanor M. & Charlie S-B. & Guillaume M. & Diego I.
 */
public class MenuEditionProjet {

    private Projet projetActuel;
    private boolean projetModifie = false;

    /**
     * @param projetActuel Projet voulant être modifié
     */
    public MenuEditionProjet(Projet projetActuel) {
        this.projetActuel = projetActuel;
    }

    /**
     * Affiche le menu d'édition pour le projet actuellement chargé
     */
    public void afficher() {
        boolean exit = false;
        int selection;
        Scanner entree = new Scanner(System.in);

        while (!exit) {
            System.out.println("1 - Créer une tache");
            System.out.println("2 - Modifier une tache");
            System.out.println("3 - Voir les taches");
            System.out.println("4 - Calculer les dates et les marges");
            System.out.println("5 - Voir les caractéristiques du projet");
            System.out.println("6 - Sauvegarder");
            System.out.println("7 - Quitter");
            selection = selecteur(1, 7);

            switch(selection) {
                case 1:
                    System.out.println(" --- Creation d'une tache ---");
                    String nom = saisieTexte("Entrez le nom de la tache : ");
                    String description = saisieTexte("Entrez la description de la tache : ");
                    int duree = saisieEntier("Entrez la duree de la tache : ");

                    Tache nvTache = new Tache(nom, description, new Duree(duree));
                    projetActuel.addTache(nvTache);
                    new MenuEditionTache(projetActuel, nvTache).afficher();                                         ;
                    projetModifie = true;
                    System.out.println("");
                    break;

                case 2:
                    System.out.println(" --- Modification d'une tache ---");
                    int tacheSelectionnee;
                    ArrayList<Tache> taches = projetActuel.getToutesTaches();
                    if (taches.size() > 0) {
                        for (int i = 0; i < taches.size(); i++) {
                            System.out.println((i + 1) + " - " + taches.get(i).getNom());
                        }
                        System.out.print("Entrez le numero de la tache a modifier : ");
                        tacheSelectionnee = selecteur(1, taches.size());
                        new MenuEditionTache(projetActuel,
                                taches.get(tacheSelectionnee - 1)).afficher();
                        projetModifie = true;
                    } else {
                        System.out.println("Aucune tache n'est disponible");
                    }
                    System.out.println("");
                    break;

                case 3:
                    System.out.println(" --- Affichage des taches ---");

                    System.out.println(projetActuel.afficherTaches());
                    break;

                case 4:
                    projetActuel.calculDesDates();
                    System.out.println("Dates et marges calculés avec succès!");
                    projetModifie = true;
                    System.out.println("");
                    break;

                case 5:
                    System.out.println(" --- Affichage des caracteristiques ---");
                    System.out.println("Nom : " + projetActuel.getNom());
                    System.out.println("Description : " + projetActuel.getDescription());
                    System.out.println("");
                    break;

                case 6:
                    System.out.println(" --- Sauvegarde du projet ---");
                    String nomFichier = saisieTexte("Entrez le nom du fichier : ");
                    try {
                        File sauvegarde = new File(MenuRacine.PATH_PROJETS
                                                  + nomFichier + ".txt");
                        if (sauvegarde.exists()) {
                            String reponse;
                            do {
                                reponse = saisieTexte("Le fichier existe déjà, "
                                        + "voulez-vous le remplacer ? (o/n) : ");
                            } while (!(reponse.equals("o") || reponse.equals("n")));

                            if (reponse.equals("o")) {
                                sauvegarde.delete();
                                sauvegarder(sauvegarde);
                                System.out.println("Sauvegarde effectuée");
                            } else {
                                System.out.println("Sauvegarde annulée");
                            }
                        } else {
                            sauvegarder(sauvegarde);
                            System.out.println("Sauvegarde effectuée");
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("");
                    break;

                case 7:
                    System.out.println(" --- Redirection vers le menu principal ---");
                    if (projetModifie) {
                        String reponse;
                        do {
                            reponse = saisieTexte("Des changements non sauvegardés ont "
                                    + "été effectués, voulez-vous vraiment quitter ? "
                                    + "(o/n) : ");
                        } while (!(reponse.equals("o") || reponse.equals("n")));
                        exit = reponse.equals("o");

                    } else {
                        exit = true;
                    }
                    System.out.println("");
                    break;
            }
        }
    }

    private void sauvegarder(File fichier) throws IOException {
        projetActuel.sauvegarder(fichier);
    }
}