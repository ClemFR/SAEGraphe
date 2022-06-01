package objets;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Un projet qui possède un corps de taches a ordennancer 
 * ainssi que taches spéciale permettant d'estimer la date de fin 
 * de projet 
 * @author Eleanor Mourgue , Charlie Sarrato-Boudet , 
 *         clement laurie , Diego Iglesias , Medard Guillaume
 *
 */
public class Projet {

	private String nom;
	private String description;
	private ArrayList<Tache> toutesTaches;
	
	/**
	 *  Tache supplementaire ajouter automatiquement pour calculer la date de fin de projet et les dates au plus Tard
	 */
	private Tache finProjet;


	/**
	 * Définit un nouveau projet
	 * @param nom Le nom du projet
	 * @param description La description du projet
	 */
	public Projet(String nom, String description) {
		this.nom = nom;
		this.description = description;
		finProjet = new Tache("Fin", "Fin de projet",new Duree(0));
		toutesTaches = new ArrayList<Tache>();
	}

	/**
	 * Définit un nouveau projet a partir d'un fichier
	 * @param aCharger le fichier a charger
	 */
	public Projet(File aCharger) throws IOException {
		String[] lignes = new String[(int) Files.lines(Path.of(aCharger.getAbsolutePath())).count()];
		int noLigne;
		int nbreTaches;
		toutesTaches = new ArrayList<Tache>();

		FileReader fichier = new FileReader(aCharger);
		BufferedReader br = new BufferedReader(fichier);
		noLigne = 0;
		for (String line = br.readLine(); line != null; line = br.readLine()) {
			lignes[noLigne] = line;
			noLigne++;
		}
		br.close();

		this.nom = lignes[0];
		this.description = lignes[1];

		nbreTaches = noLigne / 5;
		for (int i = 1; i <= nbreTaches; i++) {
			int placementTache = i * 5 - 1;

			String nomTache = lignes[placementTache];
			String descriptionTache = lignes[placementTache + 1];
			int duree = Integer.parseInt(lignes[placementTache + 2]);
			toutesTaches.add(new Tache (nomTache, descriptionTache, new Duree(duree)));
		}
		System.out.println(toutesTaches.size());

		for (int tacheActuelle = 1; tacheActuelle <= nbreTaches; tacheActuelle++) {
			String predecesseur = lignes[tacheActuelle * 5 + 2];

			if (!predecesseur.isBlank()) {
				String[] predecesseurs = predecesseur.split("-");
				for (String s : predecesseurs) {
					s = s.trim();
					int indexPredecesseur = Integer.parseInt(s);
					toutesTaches.get(tacheActuelle - 1).addTachePrecedente(toutesTaches.get(indexPredecesseur));
				}
			}

		}
		finProjet = new Tache("Fin", "Fin de projet",new Duree(0));
	}

	
	/**
	 * Ordonne les calcules de toutes les dates/marges des taches du projet
	 */
	public void calculDesDates() {

		setEvenements(toutesTaches);
		finProjet.setEvenementOrigine(new Evenement());
		for (Tache finale : getTacheFinales()) {
			finProjet.addTachePrecedente(finale);
		}

		toutesTaches.add(finProjet);
		ArrayList<Tache> tachesCalculer = new ArrayList<Tache>();

		for (int i = 0; tachesCalculer.size() != toutesTaches.size(); i++) {
			Tache courante = toutesTaches.get(i % toutesTaches.size());
			if (!tachesCalculer.contains(courante) &&  courante.isPredecesseursCalculer()) {
				courante.trouverDatePlusTot();
				tachesCalculer.add(courante);
			}
		}
		
		
		finProjet.setDatePlusTard((int)finProjet.getEvenementOrigine().getDatePlusTot());
		tachesCalculer.clear();
		
		finProjet.trouverDatePlusTard();
		
		for (Tache aCalculer : toutesTaches) {
			aCalculer.trouverMargeLibre();
			aCalculer.trouverMargeTotale();
		}

	}

	


	/**
	 * Associe des evenements et des taches de tel sorte a
	 * avoir des evenements commun pour toute les taches qui
	 * aurait des predecesseur strictement identique
	 * @param tachesAFiltrer l'enssemble de tache sur lequel on associe
	 *                       les evenements
	 */
	private void setEvenements(ArrayList<Tache> tachesAFiltrer) {

		ArrayList<Tache> copie = new ArrayList<>();
		copie.addAll(tachesAFiltrer);
		ArrayList<Tache> aRetirer = new ArrayList<Tache>();
		Tache modele;
		while (!copie.isEmpty()) {
			modele = copie.get(0);
			modele.setEvenementOrigine(new Evenement());
			copie.remove(modele);
			for (Tache aTester : copie) {

				if (Tache.memePredecesseur(aTester, modele)) {
					aTester.setEvenementOrigine(modele.getEvenementOrigine());
					aRetirer.add(aTester);
				}
			}	
			copie.removeAll(aRetirer);
		}
	}



	/**
	 * Trouver les dernières taches du projet , c'est à dire
	 * celle qui n'apparaissent dans aucune liste de taches precedante
	 * des autres taches
	 * Cette liste sera utiliser pour ajouter les bonne taches precedante a
	 * La tache 'FinDeProjet'
	 * 
	 * @return la liste des dernieres taches du projet
	 */
	public ArrayList<Tache> getTacheFinales() {
		ArrayList<Tache> tachesTrouve = new ArrayList<Tache>();
		boolean estContenu;
		for (Tache aTester : toutesTaches) {
			estContenu = false;
			for (Tache filtre : toutesTaches) {
				estContenu |= filtre.getPredecesseurs().contains(aTester);
			}
			if (!estContenu) {
				tachesTrouve.add(aTester);
			}
		}
		return tachesTrouve;
	}


	

	
	
	@Override
	public String toString() {
		StringBuilder message = new StringBuilder();
		message.append(nom + " : \n" + description
				+ "\nTache Prealable :");
		for (int tache = 0; tache < toutesTaches.size(); tache++) {
			message.append("\n - " + toutesTaches.get(tache).getNom());
		}
		message.append("\n");
		return message.toString();
	}

	/**
	 * @return Un message affichant precisement toutes les taches
	 * et leurs caracteristiques du projet
	 */
	public String afficherTaches() {
		StringBuilder message = new StringBuilder();
		for (int tache = 0; tache < toutesTaches.size(); tache++) {
			message.append("\n\n" + toutesTaches.get(tache));
		}
		return message.toString();
	}

	public Tache getTache(int index) {
		return toutesTaches.get(index);
	}

	public int size() {
		return toutesTaches.size();
	}

	/**
	 * Permet d'ajouter une nouvelle tache au projet
	 * @param aAjouter Tache à ajouter.
	 */
	public void addTache(Tache aAjouter) {
		this.toutesTaches.add(aAjouter);
	}

	/**
	 * Permet de retirer une tache du projet
	 * @param aRetirer tache à retirer
	 */
	public void retirerTache(Tache aRetirer) {
		this.toutesTaches.remove(aRetirer);
		//TODO Vérifier qu'elle n'est pas utilisé en tache précédante
	}


	public void sauvegarder(File fichierAEnregistrer) throws IOException {
		ArrayList<Tache> predesseurs;

		FileWriter ecriture = new FileWriter(fichierAEnregistrer, StandardCharsets.UTF_8);
		ecriture.write(this.nom + "\n");
		ecriture.write(this.description + "\n\n\n");

		Tache[] aEnregistrer = toutesTaches.toArray(new Tache[0]);
		for (int i = 0; i < aEnregistrer.length; i++) {
			predesseurs = aEnregistrer[i].getPredecesseurs();

			ecriture.write(aEnregistrer[i].getNom() + "\n");
			ecriture.write(aEnregistrer[i].getDescription() + "\n");
			ecriture.write(aEnregistrer[i].getDuree() + "\n");

			/*
			 * On enregistre les taches précédentes de la tache en cours. L'enregistrement
			 * est effectué sous la forme d'un entier qui correspond à l'index de la tache
			 * dans le fichier. Cet index est identique à l'index de la tache dans le
			 * tableau aEnregistrer
			 */
			if (predesseurs.size() > 0) {
				String predecesseursAEcrire = "";
				for (Tache predesseur : predesseurs) {
					for (int k = 0; k < aEnregistrer.length; k++) {
						if (predesseur.equals(aEnregistrer[k])) {
							predecesseursAEcrire += k + " - ";
						}
					}
				}
				ecriture.write(predecesseursAEcrire
						       .substring(0, predecesseursAEcrire.length() - 2) + "\n");
			} else {
				ecriture.write("\n");
			}
			ecriture.write("------" + "\n");
		}
		ecriture.close();
	}

	public ArrayList<Tache> getToutesTaches() {
		return toutesTaches;
	}

	public String getNom() {
		return nom;
	}

	public String getDescription() {
		return description;
	}


}
