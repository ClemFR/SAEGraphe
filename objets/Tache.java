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
	private double margeLibre;
	private double margeTotale;
	
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
		margeLibre = Double.NaN;
		margeTotale = Double.NaN;
	}
	
	
	/**
	 * Trouve la date au plus tot de la tache courante
	 * en fonction des predecesseurs. Dans le cas où il y aurait
	 * plusieur date possible seul la plus grande sera retenue
	 */
	public void trouverDatePlusTot() {
		int dureePlusLongue;
		
		
		if (Double.isNaN(origine.getDatePlusTot())) {
			setDatePlusTot(0);
		}

		for (Tache aComparer : predecesseurs) {

			dureePlusLongue = aComparer.getDatePlusTot().getHeures()
					+ aComparer.dureeTache.getHeures();
			
			if (origine.getDatePlusTot() < dureePlusLongue) {
				setDatePlusTot(dureePlusLongue);
			}
		}

	}

	/**
	 * Trouve les marges Libre des predecesseurs
	 */
	public void trouverMargeLibre() {
		int total;

		for (Tache aComparer : predecesseurs) {
			total = getDatePlusTot().getHeures() - aComparer.getDatePlusTot().getHeures()
					- aComparer.dureeTache.getHeures();
			if (Double.isNaN(aComparer.margeLibre) ||  total < margeLibre) 
				aComparer.setMargeLibre(total);

		}
	}





	/**
	 * Trouve les marges Totale des predecesseurs
	 */
	public void trouverMargeTotale() {
		int total;

		for (Tache aComparer : predecesseurs) {
			total = getDatePlusTard().getHeures()- aComparer.getDatePlusTot().getHeures()
					- aComparer.dureeTache.getHeures();

			if (Double.isNaN(aComparer.margeTotale) || total < aComparer.margeTotale) 
				aComparer.setMargeTotale(total);
		}
	}


	/**
	 * Trouve les dates au plus tard des predecesseurs en fonction de la 
	 * tache courante en passant par leurs evenement d'origine.
	 * Dans le cas où il y aurait plusieurs date possible
	 * seul la plus petite sera retenue. Lance récursivement cette fonction sur
	 * toutes les taches précédantes
	 */
	public void trouverDatePlusTard() {

		int dureePlusLongue;
		for (Tache aComparer : predecesseurs) {

			dureePlusLongue = getDatePlusTard().getHeures() - aComparer.dureeTache.getHeures();
			if (Double.isNaN(aComparer.origine.getDatePlusTard())
				|| dureePlusLongue < aComparer.getDatePlusTard().getHeures()) {
				aComparer.setDatePlusTard(dureePlusLongue);
			}
			
		}
		
		for (Tache precedante : predecesseurs) {
			precedante.trouverDatePlusTard();
		}
		
	}
	
	/**
	 * @return true si toutes les dates au plus tot des taches précédantes est calculée
	 */
	public boolean isPredecesseursCalculer() {
		boolean tachesPrecedCalcules = true;
		
		for (Tache aTester : predecesseurs) {
			tachesPrecedCalcules &= !Double.isNaN(aTester.getEvenementOrigine().getDatePlusTot());       
		}
		return tachesPrecedCalcules;
	}
	
	
	/**
	 * Permet de comparer les prédécesseurs de deux taches
	 * @param une Premiere tache à comparer
	 * @param deux Seconde tache à comparer
	 * @return true si la liste des prédécesseurs est strictement identiques, false sinon
	 */
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
				+ "Date au plus tot : " + (origine == null ? "date non calculée" :  getDatePlusTot())  + "\n"
				+ "Date au plus tard : " + (origine == null ? "date non calculée" :  getDatePlusTard()) + "\n"
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
			for (Tache aAnalyserPredecesseurs : tachesPrecedantes) {
				if (aAnalyserPredecesseurs.equals(this)) {
					cycleTrouve = true;
				} 
				if (!tachesAnalysees.contains(aAnalyserPredecesseurs)) {
					tachesAnalysees.add(aAnalyserPredecesseurs);
				}
				for (Tache unPredecesseur : aAnalyserPredecesseurs.getPredecesseurs()) {
					if (!tachesATraiter.contains(unPredecesseur) && !tachesAnalysees.contains(unPredecesseur)) {
						tachesATraiter.add(unPredecesseur);
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

	/**
	 * @param aRetirer Tache precedente à retirer
	 * @throws IllegalArgumentException Si aRetirer n'est pas dans la liste des prédécesseurs
	 */
	public void retirerTachePrecedente(Tache aRetirer) {
		if (!predecesseurs.contains(aRetirer)) {
			throw new IllegalArgumentException("Cette tâche n'est pas préalable");
		}
		predecesseurs.remove(aRetirer);
	}

	
	public Duree getDatePlusTot() {
		return new Duree((int) origine.getDatePlusTot());
	}


	public Duree getDatePlusTard() {
		return new Duree((int) origine.getDatePlusTard());
	}


	public Duree getMargeLibre() {
		return new Duree((int)margeLibre);
	}


	public Duree getMargeTotale() {
		return new Duree((int)margeTotale);
	}

	public void setDatePlusTot(int duree) {
		origine.setDatePlusTot(duree);
	}

	public void setDatePlusTard(int duree) {
		origine.setDatePlusTard(duree);
	}

	public void setMargeLibre(int duree) {
		margeLibre = duree;
	}

	public void setMargeTotale(int duree) {
		margeTotale = duree;
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

	public void setNom(String nom) {this.nom = nom;}


	public int getDuree() {
		return dureeTache.getHeures();
	}

	public void setDuree(int duree) {
		this.dureeTache.setHeures(duree);
	}


	public ArrayList<Tache> getPredecesseurs() {
		return predecesseurs;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description){
		this.description = description;
	}
}