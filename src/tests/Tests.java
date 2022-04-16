package tests;
import objets.*;
import java.util.ArrayList;
public class Tests {

	public static void main(String[] args) {
		ArrayList<Tache> projet = new ArrayList<Tache>();
		Tache t1 = new Tache(3,0,0,0,"A","Commande et attente de la livraison des cablage");
		Tache t2 = new Tache(4,0,0,0,"B","Pose des cablage");
		Tache t3 = new Tache(1,0,0,0,"C","Inspection des cablage");
		Tache t4 = new Tache(4,0,0,0,"D","Commande et attente de la livraison du materiel de plomberie");
		Tache t5 = new Tache(2,0,0,0,"E","Travaux de plomberie extérieure");
		Tache t6 = new Tache(5,0,0,0,"F","Travaux de plomberie interieure");
		Tache t7 = new Tache(1,0,0,0,"G","Terrassement");
		Tache t8 = new Tache(3,0,0,0,"H","Fondations");
		Tache t9 = new Tache(5,0,0,0,"I","Construction de l'ossature");
		
		projet.add(t1);
		projet.add(t2);
		projet.add(t3);
		projet.add(t4);
		projet.add(t5);
		projet.add(t6);
		projet.add(t7);
		projet.add(t8);
		projet.add(t9);

		for (int i = 0; i < projet.size(); i++) {
			System.out.println(projet.get(i));
		}

	}


}