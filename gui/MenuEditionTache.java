package gui;

import objets.Projet;
import objets.Tache;

import java.util.ArrayList;
import java.util.Scanner;

import static gui.MenuRacine.selecteur;

/**
 * G�re l'�dition d'une tache s�lectionn�e pr�alablement
 * @author Clement L. & Eleanor M. & Charlie S-B & Guillaume M.
 */
public class MenuEditionTache {

    private Projet projetActuel;
    private Tache tacheActuelle;

    /**
     * @param projetActuel Projet actuellement s�lectionn�
     * @param tacheActuelle Tache actuellement s�lectionn�e dans laquelle on veut modifier
     *                      ses propri�t�s.
     */
    public MenuEditionTache(Projet projetActuel, Tache tacheActuelle) {
        this.projetActuel = projetActuel;
        this.tacheActuelle = tacheActuelle;
    }

    /**
     * Affiche le menu d'�dition pour la tache actuellement s�lectionn�e
     */
    public void afficher() {
        boolean exit = false;
        int selection;
        Scanner entree = new Scanner(System.in);

        while (!exit) {
            System.out.println("1 - Modifier les pr�d�cesseurs");
            System.out.println("2 - Modifier le nom");
            System.out.println("3 - Modifier la description");
            System.out.println("4 - Modifier la dur�e");
            System.out.println("5 - Voir les caract�ristiques de la tache");
            System.out.println("6 - Quitter");
            selection = selecteur(1, 6);

            switch(selection) {
                case 1:
                    System.out.println(" --- Modification des pr�d�cesseurs ---");
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
                        System.out.print("Entrez une dur�e valide (entier) en H : ");
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
                    System.out.println(" --- Affichage des caract�ristiques de la tache "
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
            System.out.println("1 - Ajouter un pr�d�cesseur");
            System.out.println("2 - Supprimer un pr�d�cesseur");
            System.out.println("3 - Quitter");
            selection = selecteur(1, 3);

            switch(selection) {
                case 1:
                    System.out.println(" --- Ajout d'un pr�d�cesseur ---");
                    /*
                     * On obtient toutes les taches et on retire elle-meme ainsi que les
                     * taches qui sont d�j� pr�d�cesseurs
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
                                        + "pr�d�cesseur : ");

                        tacheSelectionnee = selecteur(1, toutesTaches.size());
                        tacheActuelle.addTachePrecedente(
                                      toutesTaches.get(tacheSelectionnee - 1));
                    } else {
                        System.out.println("Il n'y a aucune tache disponible pour ajouter"
                                          + " comme pr�d�cesseur");
                    }
                    System.out.println("");
                    break;

                case 2:
                    System.out.println(" --- Suppression d'un pr�d�cesseur ---");
                    ArrayList<Tache> predecesseurs = tacheActuelle.getPredecesseurs();
                    if (predecesseurs.size() > 0) {
                        for (int i = 0; i < predecesseurs.size(); i++) {
                            System.out.println((i + 1) + " - "
                                              + predecesseurs.get(i).getNom());
                        }
                        System.out.print("Entrez le numero de la tache a supprimer en "
                                        + "pr�d�cesseur : ");

                        tacheSelectionnee = selecteur(1, predecesseurs.size());
                        tacheActuelle.retirerTachePrecedente(
                                      predecesseurs.get(tacheSelectionnee - 1));
                    } else {
                        System.out.println("Il n'y a aucune tache disponible � supprimer "
                                          + "comme pr�d�cesseur");
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
