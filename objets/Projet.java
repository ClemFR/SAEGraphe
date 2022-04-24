package objets;

import java.util.ArrayList;

public class Projet {
	private String nom;
	private String description;
	private ArrayList<Tache> allTask = new ArrayList<Tache>();
	
	public Projet(String nom , String descriptif) {
		this.nom = nom;
		this.description = descriptif;
	}
	public void addTask(Tache toAdd) {
		this.allTask.add(toAdd);
	}
	public void removeTask(Tache toRemove) {
		this.allTask.remove(toRemove);
	}
	public String toString() {
		StringBuilder message = new StringBuilder();

		message.append(this.nom + " : \n" + this.description  
				       + "\nTache Prealable :");
		for (int nbTask = 0; nbTask < this.allTask.size(); nbTask++) {
			message.append("\n - " + this.allTask.get(nbTask).getNom());
		}
		message.append("\n");
		return message.toString();
	}
	public String printAll() {
		StringBuilder message = new StringBuilder();
		
		for (int i = 0; i < allTask.size(); i++) {
			message.append("\n\n" + allTask.get(i).toString());
		}
		return message.toString();
	}
	public void Calculate() {
		ArrayList<Tache> conditions = getTrouver(null);
		while (conditions.size() != 0) {
			
			for (int i = 0; i < conditions.size(); i++) {
				conditions.get(i).FindEarliestDate();
			}
			conditions = getTrouver(conditions);
		}
		
		
		
	}
	public ArrayList<Tache> getTrouver(ArrayList<Tache> condition) {
		ArrayList<Tache> trouver = new ArrayList<Tache>();
		for (int i = 0; i < allTask.size(); i++) {
			if (allTask.get(i).hasTheseConditions(condition)) {
				trouver.add(allTask.get(i));
			}
		}
		return trouver; // bouchon
		

	}
	
}
