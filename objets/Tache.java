package objets;
import java.util.ArrayList;
public class Tache {
	private ArrayList<Tache> conditions = new ArrayList<Tache>();
	public Duree taskDuration;
	private String nom;
	private String description;
	private Duree earliestDate = new Duree(0);
	private Duree auPlusTard = new Duree(0);
	private Duree margeLibre = new Duree(0);
	private Duree margeTotale = new Duree(0);

	public Tache(String nom , String description, Duree deLaTache) {
		this.taskDuration = deLaTache;
		this.nom = nom;
		this.description = description;
	}
	
	public void FindMargeLibre() {
		int totale;
		Tache toCompare;
		for (int i = 0; i < conditions.size(); i++) {
			toCompare = conditions.get(i);
			totale = earliestDate.getHours() - toCompare.earliestDate.getHours() - toCompare.taskDuration.getHours();
			toCompare.margeLibre.setHours(totale);
		}
	}
	public void FindMargeTotale() {
		int totale;
		Tache toCompare;
		for (int i = 0; i < conditions.size(); i++) {
			toCompare = conditions.get(i);
			totale = auPlusTard.getHours() - toCompare.earliestDate.getHours() - toCompare.taskDuration.getHours();
			toCompare.margeTotale.setHours(totale);
		}
	}
	public void FindLastestDate() {
		Tache toCompare;
		int longerDuration;
		
		for (int i = 0; i < conditions.size(); i++) {
			toCompare = conditions.get(i);
			longerDuration = auPlusTard.getHours() - toCompare.taskDuration.getHours();
			toCompare.auPlusTard.setHours(longerDuration);
			
		}
	}
	/**
	 * Calcule la date au plus tot de cette tache par rapport aux taches prealable
	 */
	public void FindEarliestDate() {
		Tache toCompare;
		boolean isShorter;
		int longerDuration;
		
		for (int i = 0; i < conditions.size(); i++) {
			toCompare = conditions.get(i);
			longerDuration =  toCompare.earliestDate.getHours() + toCompare.taskDuration.getHours();
			isShorter = earliestDate.getHours() < longerDuration;
			
			if (isShorter) earliestDate.setHours(longerDuration);
		}
		
	}
	/**
	 * @param filtered un filtre de tache
	 * @return true si l'enssemble de tache prealable est comprit dans le filtre
	 *         ou si le filtre et l'enssemble de tache prealable sont vide
	 *         sinon false
	 */
	public boolean hasTheseConditions(ArrayList<Tache> filtered) {
		boolean emptyFiltered = (filtered == null || filtered.isEmpty()) && this.getConditions().isEmpty();
		boolean arePrerequisiteTaskInclued = false;
		if (!emptyFiltered && !this.getConditions().isEmpty()) {
			arePrerequisiteTaskInclued = true;
			for (int i = 0; i < this.getConditions().size(); i++) {
				arePrerequisiteTaskInclued &= filtered.contains(this.getConditions().get(i));
			} 
		}		        
		return arePrerequisiteTaskInclued || emptyFiltered;
	}
	@Override
	public String toString() {
		StringBuilder message = new StringBuilder();

		message.append(this.nom + " :\nDuree : " + this.taskDuration + "\n"
					   + "Date au plus tot : " + this.earliestDate + "\n"
					   + "Date au plus tard : " + this.auPlusTard + "\n"
					   + "Marge Libre : " + margeLibre + "\n"
					   + "Marge Totale : " + margeTotale + "\n"
			           + this.description + "\nTache Prealable :");
					   
		for (int nbTask = 0; nbTask < this.conditions.size(); nbTask++) {
			message.append("\n - " + this.conditions.get(nbTask).getNom());
		}
		return message.toString();
	}
	public void setMargeTotale(Duree margeTotale) {
		this.margeTotale = margeTotale;
	}
	public void setLastestDate(Duree auPlusTard) {
		this.auPlusTard = auPlusTard;
	}
	public void addPreliminaryTask(Tache prealable) {
		this.conditions.add(prealable);
	}
	
	public String getNom() {
		return this.nom;
	}
	public void setEarliestDate(Duree earliestDate) {
		this.earliestDate = earliestDate;
	}
	public Duree getEarliestDate () {
		return this.earliestDate;
	}
	
	public ArrayList<Tache> getConditions() {
		return this.conditions;
	}
}