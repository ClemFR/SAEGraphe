package gui;

import objets.Duree;
import objets.Projet;
import objets.Tache;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import exception.ProjetVideException;

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
			System.out.println("4 - Calculer les dates, les marges et le chemin critique");
			System.out.println("5 - Voir les caractéristiques du projet");
			System.out.println("6 - Sauvegarder");
			System.out.println("7 - Quitter");

			switch(selecteur(1, 7)) {
			case 1:
				System.out.println(" --- Creation d'une tache ---");
				String nom = saisieTexte("Entrez le nom de la tache : ");
				String description = saisieTexte("Entrez la description de la tache : ");
				int duree = saisieEntier("Entrez la duree de la tache : ");

				Tache nvTache = new Tache(nom, description, new Duree(duree));
				projetActuel.addTache(nvTache);
				new MenuEditionTache(projetActuel, nvTache).affichageCaracteristique();                                         ;
				projetModifie = true;
				System.out.println("");
				break;

			case 2:
				System.out.println(" --- Modification d'une tache ---");
				try {
					modifTache();
				} catch (ProjetVideException e) {
					System.out.println(e.getMessage());
				}

				projetModifie = true;
				System.out.println("");
				break;

			case 3:

				System.out.println(" --- Affichage des taches ---");

				try {
					System.out.println(projetActuel.afficherTaches());
					System.out.println("--------------");
					System.out.println("Le chemin critique est composé des taches "
							+ "suivantes :");
					afficherCheminCritique();
					System.out.println("");
				} catch (ProjetVideException e) {
					System.out.println(e.getMessage() + "\n");
				}
				break;

			case 4:

				try {
					projetActuel.calculDesDates();
					System.out.println("Dates et marges calculés avec succès!");
					projetModifie = true;
					System.out.println("");
				} catch (ProjetVideException e) {
					System.out.println(e.getMessage() + "\n");
				}
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


	private void modifTache()throws ProjetVideException {

		if (projetActuel.isEmpty()) {
			throw new ProjetVideException("Aucune tache n'est disponible");
		}

		for (int i = 0; i < projetActuel.size(); i++) {
			System.out.println((i + 1) + " - " + projetActuel.getTache(i).getNom());
		}
		System.out.print("Entrez le numero de la tache a modifier : ");
		new MenuEditionTache(projetActuel,
				projetActuel.getTache(selecteur(1, projetActuel.size()) - 1)).afficher();
	}

	private void afficherCheminCritique() {
		final String SEPARATEUR = " - ";
		ArrayList<Tache> tachesCritiques = projetActuel.getCheminCritique();
		String affichage = "";

		if (tachesCritiques.size() == 0) {
			affichage = "Aucun chemin critique n'a été trouvé";
		} else {
			for (Tache aAfficher : tachesCritiques) {
				affichage += aAfficher.getNom() + SEPARATEUR;
			}
			//retrait dernier tiret
			affichage = affichage.substring(0, affichage.length() - 2);
		}
		System.out.println(affichage);
	}

	private void sauvegarder(File fichier) throws IOException {
		projetActuel.sauvegarder(fichier);
	}
}