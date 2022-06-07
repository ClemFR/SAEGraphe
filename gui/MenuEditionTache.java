package gui;

import objets.Projet;
import objets.Tache;

import java.util.ArrayList;
import java.util.Scanner;

import static gui.MenuRacine.selecteur;

/**
 * Gère l'édition d'une tache sélectionnée préalablement
 * @author Clement L. & Eleanor M. & Charlie S-B & Guillaume M.
 */
public class MenuEditionTache {

    private Projet projetActuel;
    private Tache tacheActuelle;

    /**
     * @param projetActuel Projet actuellement sélectionné
     * @param tacheActuelle Tache actuellement sélectionnée dans laquelle on veut modifier
     *                      ses propriétés.
     */
    public MenuEditionTache(Projet projetActuel, Tache tacheActuelle) {
        this.projetActuel = projetActuel;
        this.tacheActuelle = tacheActuelle;
    }

    /**
     * Affiche le menu d'édition pour la tache actuellement sélectionnée
     */
    public void afficher() {
        boolean exit = false;
        int selection;
        Scanner entree = new Scanner(System.in);

        while (!exit) {
            System.out.println("1 - Modifier les prédécesseurs");
            System.out.println("2 - Modifier le nom");
            System.out.println("3 - Modifier la description");
            System.out.println("4 - Modifier la durée");
            System.out.println("5 - Voir les caractéristiques de la tache");
            System.out.println("6 - Quitter");
            selection = selecteur(1, 6);

            switch(selection) {
                case 1:
                    System.out.println(" --- Modification des prédécesseurs ---");
                    modificationPredecesseur();
                    System.out.println("");
                    break;

                case 2:
                    System.out.println(" --- Modification du nom ---");
                    System.out.print("Entrez le nouveau nom de la tache : ");
                    String nom = entree.nextLine();
                    tacheActuelle.setNom(nom);
                    System.out.println("");
                    break;

                case 3:
                    System.out.println(" --- Modification de la description ---");
                    System.out.println("Entrez la nouvelle description de la tache : ");
                    String description = entree.nextLine();
                    tacheActuelle.setDescription(description);
                    System.out.println("");
                    break;

                case 4:
                    do  {
                        System.out.print("Entrez une durée valide (entier) en H : ");
                        if (!entree.hasNextInt()) {
                            entree.nextLine();
                        }
                    } while (!entree.hasNextInt());
                    int duree = entree.nextInt();
                    entree.nextLine();
                    tacheActuelle.setDuree(duree);
                    System.out.println("");
                    break;

                case 5:
                    System.out.println(" --- Affichage des caractéristiques de la tache "
                                      + "---");
                    System.out.println("Nom : " + tacheActuelle.getNom());
                    System.out.println("Description : " + tacheActuelle.getDescription());
                    System.out.println("");
                    break;

                case 6:
                    exit = true;
                    System.out.println("");
                    break;

            }
        }
    }

    private void modificationPredecesseur() {
        boolean exit = false;
        int selection;
        int tacheSelectionnee;
        Scanner entree = new Scanner(System.in);

        while (!exit) {
            System.out.println("1 - Ajouter un prédécesseur");
            System.out.println("2 - Supprimer un prédécesseur");
            System.out.println("3 - Quitter");
            selection = selecteur(1, 3);

            switch(selection) {
                case 1:
                    System.out.println(" --- Ajout d'un prédécesseur ---");
                    /*
                     * On obtient toutes les taches et on retire elle-meme ainsi que les
                     * taches qui sont déjà prédécesseurs
                     */
                    ArrayList<Tache> toutesTaches = projetActuel.getToutesTaches();
                    toutesTaches.removeAll(tacheActuelle.getPredecesseurs());
                    toutesTaches.remove(tacheActuelle);

                    if (toutesTaches.size() > 0) {
                        for (int i = 0; i < toutesTaches.size(); i++) {
                            System.out.println((i + 1) + " - "
                                              + toutesTaches.get(i).getNom());
                        }
                        System.out.print("Entrez le numero de la tache a ajouter en "
                                        + "prédécesseur : ");

                        tacheSelectionnee = selecteur(1, toutesTaches.size());
                        tacheActuelle.addTachePrecedente(
                                      toutesTaches.get(tacheSelectionnee - 1));
                    } else {
                        System.out.println("Il n'y a aucune tache disponible pour ajouter"
                                          + " comme prédécesseur");
                    }
                    System.out.println("");
                    break;

                case 2:
                    System.out.println(" --- Suppression d'un prédécesseur ---");
                    ArrayList<Tache> predecesseurs = tacheActuelle.getPredecesseurs();
                    if (predecesseurs.size() > 0) {
                        for (int i = 0; i < predecesseurs.size(); i++) {
                            System.out.println((i + 1) + " - "
                                              + predecesseurs.get(i).getNom());
                        }
                        System.out.print("Entrez le numero de la tache a supprimer en "
                                        + "prédécesseur : ");

                        tacheSelectionnee = selecteur(1, predecesseurs.size());
                        tacheActuelle.retirerTachePrecedente(
                                      predecesseurs.get(tacheSelectionnee - 1));
                    } else {
                        System.out.println("Il n'y a aucune tache disponible à supprimer "
                                          + "comme prédécesseur");
                    }
                    System.out.println("");
                    break;

                case 3:
                    exit = true;
                    System.out.println("");
                    break;
            }
        }
    }
}
