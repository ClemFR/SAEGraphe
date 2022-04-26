package objets;
import java.util.ArrayList;
public class Tache {
	private ArrayList<Tache> conditions = new ArrayList<Tache>();
	public Duree taskDuration;
	private String nom;
	private String description;
	private Duree earliestDate;
	private Duree auPlusTard;

	public Tache(String nom , String description, Duree deLaTache) {
		this.taskDuration = deLaTache;
		this.nom = nom;
		this.description = description;
	}
	
	/**
	 * Calcule la date au plus tot de cette tache par rapport aux taches prealable
	 */
	public void FindEarliestDate() {
		Tache toCompare;
		boolean isShorter;
		int longerDuration;
		if (earliestDate == null )
			earliestDate = new Duree(0);
		
		for (int i = 0; i < conditions.size(); i++) {
			toCompare = conditions.get(i);
			if (toCompare.earliestDate == null ) toCompare.setEarliestDate(new Duree(0));
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

		message.append(this.nom + " :\ntemp restant : " + this.taskDuration + "\n"
					   + "Date au plus tot : " + this.earliestDate + "\n"
			           + this.description + "\nTache Prealable :");
		
		for (int nbTask = 0; nbTask < this.conditions.size(); nbTask++) {
			message.append("\n - " + this.conditions.get(nbTask).getNom());
		}
		return message.toString();
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