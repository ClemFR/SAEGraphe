package objets;
import java.util.ArrayList;
public class Tache {
	private ArrayList<Tache> conditions = new ArrayList<Tache>();
	private Duree taskDuration;
	private String nom;
	private String description;
	private Duree earliestDate;
	private Duree auPlusTard;

	public Tache(String nom , String description, Duree deLaTache) {
		this.taskDuration = deLaTache;
		this.nom = nom;
		this.description = description;
	}
	
	
	public void FindEarliestDate() {
		Tache toCompare;
		boolean isShorter;
		int longerDuration;
		
		if (earliestDate == null)
			earliestDate = new Duree(taskDuration.getHours());
		
		for (int i = 0; i < conditions.size(); i++) {
			toCompare = conditions.get(i);
			longerDuration =  toCompare.earliestDate.getHours() + taskDuration.getHours();
			isShorter = earliestDate.getHours() < longerDuration;
			
			if (isShorter) earliestDate.setHours(longerDuration);
		}
		
	}
	public boolean hasTheseConditions(ArrayList<Tache> expectedConditions) {
		boolean hasCondition = !this.getConditions().isEmpty();
		if (expectedConditions != null) {
			for (int i = 0; i < this.getConditions().size(); i++) {
				hasCondition &= expectedConditions.contains(this.getConditions().get(i));
			} 
		}
		if (this.getConditions().isEmpty()) {
			hasCondition |= expectedConditions == null || expectedConditions.isEmpty();
		}
				        
		return hasCondition;
		
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
	
	public Duree getEarliestDate () {
		return this.earliestDate;
	}
	
	public ArrayList<Tache> getConditions() {
		return this.conditions;
	}
}