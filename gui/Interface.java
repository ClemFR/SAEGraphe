package gui;
import java.util.Scanner;

import objets.Duree;
import objets.Projet;
import objets.Tache;


public class Interface {
	
	public static void main(String[] args) {
		Projet projet = new Projet("Projet test","");
		boolean exit = false;
		Scanner entree = new Scanner(System.in);
		while (!exit) {
			switch (entree.next().toLowerCase()) {
			case "addtask": 
				String nom;
				System.out.println("Entree le nom de la tache : ");
				nom = entree.next();
				System.out.println("Entree la duree : ");
				projet.addTache(new Tache(nom,"",new Duree(entree.nextInt())));
				
				break;
			case "calcul":
				projet.calculDate();
				break;
			case "addp":
				Tache aAjouter;
				Tache receveur;
				for (int i = 0; i < projet.size(); i++) {
					System.out.println(i + " | " + projet.getTache(i).getNom() + "\n");
				}
				System.out.println("Choississez la tache a ajouter : ");
				aAjouter = projet.getTache(entree.nextInt());
				System.out.println("A quel tache ? ");
				receveur = projet.getTache(entree.nextInt());
				receveur.addTachePrecedente(aAjouter);
				break;
			case "exit":
				exit = true;
				break;
			case "printall":
				System.out.println(projet.afficherTaches());
				break;
			case "print":
				System.out.println(projet);
				break;
			default:
				System.err.println("Mauvaise entree : ) ");
			}
		}
	
	}
}
