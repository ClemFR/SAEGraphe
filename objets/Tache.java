package objets;
import java.util.ArrayList;
public class Tache {
	private ArrayList<Tache> conditions = new ArrayList<Tache>();
	
	public Duree dureeTache;
	private String nom;
	private String description;
	private Evenement origine;

	public Tache(String nom , String description, Duree deLaTache) {
		this.dureeTache = deLaTache;
		this.nom = nom;
		this.description = description;
		this.origine = null;
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
	
	public void addTachePrecedente(Tache prealable) {
		this.conditions.add(prealable);
	}
	public void setEvenementOrigine(Evenement e) {
		this.origine = e;
	}
	public Evenement getEvenementOrigine() {
		return this.origine;
	}
	public String getNom() {
		return this.nom;
	}
	public int getDuree() {
		return this.dureeTache.getHeures();
	}
	public ArrayList<Tache> getConditions() {
		return this.conditions;
	}
}