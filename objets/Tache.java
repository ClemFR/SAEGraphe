package objets;
import exception.CycleException;

import java.util.ArrayList;


/**
 * Taches fesant partie d'une liste de taches d'un projet.
 * Cette taches peut elle meme en contenir d'autre qui représente alors
 * des predecesseurs a cette taches.
 * Cette classe intervient dans le calculs des dates au plus tot et au plus tard
 * de cette tache ou de ses predecesseurs
 * Un evenement lui sera associé permettant alors à plusieur taches de choisir
 * les bons resultats.
 * @author Eleanor Mourgue , Charlie Sarrato-Boudet , 
 *         clement laurie , Diego Iglesias , Medard Guillaume
 *
 */
public class Tache {
	private ArrayList<Tache> predecesseurs = new ArrayList<Tache>();

	public Duree dureeTache;
	private String nom;
	private String description;
	
	// Evenement d'origine d'une tache par rapport a la representation d'un diagramme pertt
	private Evenement origine;
	private Duree margeLibre;
	private Duree margeTotale;

	
	
	
	/**
	 * 
	 * @param nom 
	 * @param description
	 * @param deLaTache
	 */
	public Tache(String nom , String description, Duree deLaTache) {
		this.dureeTache = deLaTache;
		this.nom = nom;
		this.description = description;
		this.origine = null;
		margeLibre = new Duree(0);
		margeTotale = new Duree(0);
	}

	
	/**
	 * Trouve la date au plus tot de la tache courante
	 * en fonction des predecesseurs. Dans le cas où il y aurait
	 * plusieur date possible seul la plus grande sera retenue
	 */
	public void trouverDatePlusTot() {
		int dureePlusLongue;

		for (Tache aComparer : predecesseurs) {

			dureePlusLongue = aComparer.getDatePlusTot().getHeures()
					+ aComparer.dureeTache.getHeures();
			if (getDatePlusTot().getHeures() < dureePlusLongue) {
				setDatePlusTot(dureePlusLongue);
			}
		}

	}
	
	
	/**
	 * Trouve les marges Libre des predecesseurs
	 */
	public void trouverMargeLibre() {
		int total;
		Tache aComparer;
		for (int i = 0; i < predecesseurs.size(); i++) {
			aComparer = predecesseurs.get(i);
			total = getDatePlusTot().getHeures() - aComparer.getDatePlusTot().getHeures()
					- aComparer.dureeTache.getHeures();
			aComparer.setMargeLibre(total);
		}
	}
	
	/**
	 * Trouve les marges Totale des predecesseurs
	 */
	public void trouverMargeTotale() {
		int total;
		Tache aComparer;
		for (int i = 0; i < predecesseurs.size(); i++) {
			aComparer = predecesseurs.get(i);
			total = getDatePlusTard().getHeures()- aComparer.getDatePlusTot().getHeures()
					- aComparer.dureeTache.getHeures();
			aComparer.setMargeTotale(total);
		}
	}


	/**
	 * Trouve les dates au plus tard des predecesseurs en fonction de la 
	 * tache courante en passant par leurs evenement d'origine.
	 * Dans le cas où il y aurait plusieur date possible
	 * seul la plus petite sera retenue
	 */
	public void trouverDatePlusTard() {

		int dureePlusLongue;

		for (Tache aComparer : predecesseurs) {

			dureePlusLongue = getDatePlusTard().getHeures() - aComparer.dureeTache.getHeures();
			if (aComparer.getDatePlusTard().getHeures() == 0 
				|| dureePlusLongue < aComparer.getDatePlusTard().getHeures()) {

				aComparer.setDatePlusTard(dureePlusLongue);
			}


		}
	}
	/**
	 * @param tachesPrecedentes un filtre de tache
	 * @return true si l'enssemble de tache prealable est comprit dans le filtre
	 *         ou si le filtre et l'enssemble de tache prealable sont vide
	 *         sinon false
	 */
	public boolean verifierPredecesseurs(ArrayList<Tache> tachesPrecedentes) {
		boolean filtreVide = (tachesPrecedentes == null || tachesPrecedentes.isEmpty())
				&& this.getPredecesseurs().isEmpty();
		boolean isTachesPrecedentesIncluses = false;
		if (!filtreVide && !this.getPredecesseurs().isEmpty()) {
			isTachesPrecedentesIncluses = true;
			for (int i = 0; i < this.getPredecesseurs().size(); i++) {
				isTachesPrecedentesIncluses
				&= tachesPrecedentes.contains(this.getPredecesseurs().get(i));
			} 
		}		        
		return isTachesPrecedentesIncluses || filtreVide;
	}
	public static boolean memePredecesseur(Tache une, Tache deux) {
		boolean memeTaille = une.getPredecesseurs().size() == deux.getPredecesseurs().size();
		boolean memeContenue = true;
		if (memeTaille) {
			for (Tache t : une.getPredecesseurs()) {
				memeContenue &= deux.getPredecesseurs().contains(t);
			}
		}

		return memeContenue && memeTaille;
	}




	@Override
	public String toString() {
		StringBuilder message = new StringBuilder();

		message.append(nom + " :\nDuree : " + dureeTache + "\n"
				+ "Date au plus tot : " + origine.getDatePlusTot() + "\n"
				+ "Date au plus tard : " + origine.getDatePlusTard() + "\n"
				+ "Marge Libre : " + margeLibre + "\n"
				+ "Marge Totale : " + margeTotale + "\n"
				+ description + "\nTache Prealable :");

		for (int nbTache = 0; nbTache < this.predecesseurs.size(); nbTache++) {
			message.append("\n - " + this.predecesseurs.get(nbTache).getNom());
		}
		return message.toString();

	}




	/**
	 * Detecte un circuit si l'on retrouver la tache courante dans l'arbre
	 * des taches precedantes de 'aVerifier'
	 * @param aVerifier la taches dont on va verifier l'arbre jusqu'à l'origine
	 * @return true si un circuit a été détecté false sinon
	 */
	public boolean detectionCircuit(Tache aVerifier) {

		ArrayList<Tache> tachesPrecedantes = new ArrayList<Tache>();
		ArrayList<Tache> tachesAnalysees = new ArrayList<Tache>();
		boolean cycleTrouve;

		ArrayList<Tache> tachesATraiter = new ArrayList<Tache>();
		tachesPrecedantes = aVerifier.getPredecesseurs();
		cycleTrouve = false;

		do {
			for (Tache e : tachesPrecedantes) {
				if (e.equals(this)) {
					cycleTrouve = true;
				} 
				if (!tachesAnalysees.contains(e)) {
					tachesAnalysees.add(e);
				}
				for (Tache a : e.getPredecesseurs()) {
					if (!tachesATraiter.contains(a) && !tachesAnalysees.contains(a)) {
						tachesATraiter.add(a);
					}
				}
			}
			tachesPrecedantes = tachesATraiter;
			tachesATraiter.clear();
		} while (tachesPrecedantes.size() != 0 && !cycleTrouve);
		return cycleTrouve;
	}


	/**
	 * @param prealable que l'on veut ajouter a la liste des predecesseurs
	 * @throws CycleException si son ajout creer un circuit dans le projet
	 */
	public void addTachePrecedente(Tache prealable) {
		if(detectionCircuit(prealable)) {
			throw new CycleException("Cycle trouve");
		}
		predecesseurs.add(prealable);
	}


	public Duree getDatePlusTot() {
		return origine.getDatePlusTot();
	}


	public Duree getDatePlusTard() {
		return origine.getDatePlusTard();
	}


	public Duree getMargeLibre() {
		return margeLibre;
	}


	public Duree getMargeTotale() {
		return margeTotale;
	}

	public void setDatePlusTot(int duree) {
		origine.setDatePlusTot(duree);
	}

	public void setDatePlusTard(int duree) {
		origine.setDatePlusTard(duree);
	}

	public void setMargeLibre(int duree) {
		margeLibre.setHeures(duree);
	}

	public void setMargeTotale(int duree) {
		margeTotale.setHeures(duree);
	}

	public Evenement getEvenementOrigine() {
		return origine;
	}


	public void setEvenementOrigine(Evenement origine) {
		this.origine = origine;
	}



	public String getNom() {
		return this.nom;
	}


	public int getDuree() {
		return dureeTache.getHeures();
	}



	public ArrayList<Tache> getPredecesseurs() {
		return predecesseurs;
	}
}