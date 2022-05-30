package objets;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Un projet qui possède un corps de taches a ordennancer 
 * ainssi que taches spéciale permettant d'estimer la date de fin 
 * de projet 
 * @author Eleanor Mourgue , Charlie Sarrato-Boudet , 
 *         clement laurie , Diego Iglesias , Medard Guillaume
 *
 */
public class Projet implements Serializable {

	private String nom;
	private String description;
	private ArrayList<Tache> toutesTaches;
	// Tache supplementaire ajouter automatiquement pour calculer la date de fin de projet et les dates au plus Tard
	private Tache FinDeProjet;


	public Projet(String nom, String description) {
		this.nom = nom;
		this.description = description;
		FinDeProjet = new Tache("Fin", "Fin de projet",new Duree(0));
		toutesTaches = new ArrayList<Tache>();
	}

	
	/**
	 * Ordonne les calcules de toutes les dates/marges des taches du projet
	 */
	public void calculDesDates() {

		toutesTaches = ordonnerTaches();
		FinDeProjet.setEvenementOrigine(new Evenement());
		for (Tache finale : getTacheFinales()) {
			FinDeProjet.addTachePrecedente(finale);
		}

		toutesTaches.add(FinDeProjet);

		for (int i = 0; i < toutesTaches.size(); i++) {
			toutesTaches.get(i).trouverDatePlusTot();
		}
		// pour la derniere taches d'un projet la date au plus tot et au plus tard doivent être égale
		FinDeProjet.setDatePlusTard(FinDeProjet.getDatePlusTot().getHeures());
		for (int i = toutesTaches.size() - 1; i >= 0; i--) {
			toutesTaches.get(i).trouverDatePlusTard();

		}

		for (Tache duProjet : toutesTaches) {
			duProjet.trouverMargeLibre();
			duProjet.trouverMargeTotale();
		}

	}

	/**
	 * Ordonne les taches du projet de telle sorte a ce que lorsqu'une
	 * taches calcule ses dates au plus tot et au plus tard , la tache précédente 
	 * est été calculer .
	 * @return La nouvelles liste des taches ordonner
	 */
	public ArrayList<Tache> ordonnerTaches() {
		ArrayList<Tache> filtre = new ArrayList<Tache>();
		ArrayList<Tache> tachesSuivantes
		= getTachesSuivantes(filtre);

		while (tachesSuivantes.size() != 0) {

			for (int calculDate = 0;
					calculDate < tachesSuivantes.size();
					calculDate++) {
				
				setEvenements(tachesSuivantes);
				filtre.add(tachesSuivantes.get(calculDate));

			}

			tachesSuivantes
			= getTachesSuivantes(tachesSuivantes);

		}

		return filtre;
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




	/**
	 * @param tachesPrecedentes un ensemble de tache servant de filtre
	 * @return Un enssemble de tache ou chaque tache a un ensemble de
	 * tache prealable qui est comprit dans le filtre.
	 * Ou dans le cas ou le filtre est un ensemble vide
	 * renvoie les taches qui n'ont pas de tache prealable
	 */
	public ArrayList<Tache> getTachesSuivantes(ArrayList<Tache> tachesPrecedentes) {
		ArrayList<Tache> tachesTrouvees = new ArrayList<Tache>();
		for (int tachesATester = 0;
				tachesATester < toutesTaches.size();
				tachesATester++) {
			if (toutesTaches.get(tachesATester).verifierPredecesseurs(tachesPrecedentes)) {
				tachesTrouvees.add(toutesTaches.get(tachesATester));
			}
		}

		return tachesTrouvees;
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

	public void addTache(Tache aAjouter) {
		this.toutesTaches.add(aAjouter);
	}

	public void retirerTache(Tache aRetirer) {
		this.toutesTaches.remove(aRetirer);
	}

}
