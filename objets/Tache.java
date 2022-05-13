package objets;
import java.util.ArrayList;
public class Tache {
	private ArrayList<Tache> conditions = new ArrayList<Tache>();
	public Duree dureeTache;
	private String nom;
	private String description;
	private Duree datePlusTot = new Duree(0);
	private Duree auPlusTard = new Duree(0);
	private Duree margeLibre = new Duree(0);
	private Duree margeTotale = new Duree(0);

	public Tache(String nom , String description, Duree deLaTache) {
		this.dureeTache = deLaTache;
		this.nom = nom;
		this.description = description;
	}
	
	public void trouverMargeLibre() {
		int total;
		Tache aComparer;
		for (int i = 0; i < conditions.size(); i++) {
			aComparer = conditions.get(i);
			total = datePlusTot.getHeures() - aComparer.datePlusTot.getHeures()
					- aComparer.dureeTache.getHeures();
			aComparer.margeLibre.setHeures(total);
		}
	}
	public void trouverMargeTotale() {
		int total;
		Tache aComparer;
		for (int i = 0; i < conditions.size(); i++) {
			aComparer = conditions.get(i);
			total = auPlusTard.getHeures() - aComparer.datePlusTot.getHeures()
					- aComparer.dureeTache.getHeures();
			aComparer.margeTotale.setHeures(total);
		}
	}
	public void trouverDatePlusTard() {
		Tache precedente;
		int dureePlusLongue;
		
		for (int i = 0; i < conditions.size(); i++) {
			precedente = conditions.get(i);
			dureePlusLongue = auPlusTard.getHeures() - precedente.dureeTache.getHeures();
			precedente.auPlusTard.setHeures(dureePlusLongue);
			
		}
	}
	/**
	 * Calcule la date au plus tot de cette tache par rapport aux taches prealable
	 */
	public void trouverDatePlusTot() {
		Tache aComparer;
		boolean isPlusPetit;
		int dureePlusLongue;
		
		for (int i = 0; i < conditions.size(); i++) {
			aComparer = conditions.get(i);
			dureePlusLongue = aComparer.datePlusTot.getHeures()
					          + aComparer.dureeTache.getHeures();
			isPlusPetit = datePlusTot.getHeures() < dureePlusLongue;
			
			if (isPlusPetit) datePlusTot.setHeures(dureePlusLongue);
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
				              && this.getConditions().isEmpty();
		boolean isTachesPrecedentesIncluses = false;
		if (!filtreVide && !this.getConditions().isEmpty()) {
			isTachesPrecedentesIncluses = true;
			for (int i = 0; i < this.getConditions().size(); i++) {
				isTachesPrecedentesIncluses
					&= tachesPrecedentes.contains(this.getConditions().get(i));
			} 
		}		        
		return isTachesPrecedentesIncluses || filtreVide;
	}
	@Override
	public String toString() {
		StringBuilder message = new StringBuilder();

		message.append(this.nom + " :\nDuree : " + this.dureeTache + "\n"
					   + "Date au plus tot : " + this.datePlusTot + "\n"
					   + "Date au plus tard : " + this.auPlusTard + "\n"
					   + "Marge Libre : " + margeLibre + "\n"
					   + "Marge Totale : " + margeTotale + "\n"
			           + this.description + "\nTache Prealable :");
					   
		for (int nbTache = 0; nbTache < this.conditions.size(); nbTache++) {
			message.append("\n - " + this.conditions.get(nbTache).getNom());
		}
		return message.toString();
	}
	public void setMargeTotale(Duree margeTotale) {
		this.margeTotale = margeTotale;
	}
	public void setDatePlusTard(Duree auPlusTard) {
		this.auPlusTard = auPlusTard;
	}
	public void addTachePrecedente(Tache prealable) {
		this.conditions.add(prealable);
	}
	
	public String getNom() {
		return this.nom;
	}
	public void setDatePlusTot(Duree datePlusTot) {
		this.datePlusTot = datePlusTot;
	}
	public Duree getDatePlusTot() {
		return this.datePlusTot;
	}
	
	public ArrayList<Tache> getConditions() {
		return this.conditions;
	}
}