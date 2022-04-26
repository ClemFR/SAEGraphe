package objets;

import java.util.ArrayList;

public class Projet {
	private String nom;
	private String description;
	private ArrayList<Tache> allTasks = new ArrayList<Tache>();
	
	public Projet(String nom , String descriptif) {
		this.nom = nom;
		this.description = descriptif;
	}
	/**
	 * Calcule les dates au plus tot de toute les taches d'un projet
	 * // TODO Permettre de calculer les date au plus tard
	 * // TODO Calculer la date de fin de projet
	 */
	public void datesCalculation() {
		ArrayList<Tache> emptySet = new ArrayList<Tache>();
		ArrayList<Tache> setOfDateIndependentEachOther 
		                 = getTasksByPreviousTasks(emptySet);
		
		while (setOfDateIndependentEachOther.size() != 0) {
			
			for (int calculableDate = 0; 
					 calculableDate < setOfDateIndependentEachOther.size(); 
					 calculableDate++) {
				
				setOfDateIndependentEachOther.get(calculableDate).FindEarliestDate();
			}
			setOfDateIndependentEachOther 
			= getTasksByPreviousTasks(setOfDateIndependentEachOther);
		}
	}
	/**
	 * @param filtered un enssemble de tache servant de filtre
	 * @return Un enssemble de tache ou chaque tache a un enssemble de 
	 *         tache prealable qui est comprit dans le filtre.
	 *         Ou dans le cas ou le filtre est un essemble vide
	 *         renvoie les taches qui n'ont pas de tache prealable
	 */
	public ArrayList<Tache> getTasksByPreviousTasks(ArrayList<Tache> filtered) {
		ArrayList<Tache> tasksFound = new ArrayList<Tache>();
		for (int taskToTest = 0; taskToTest < allTasks.size(); taskToTest++) {
			if (allTasks.get(taskToTest).hasTheseConditions(filtered)) {
				tasksFound.add(allTasks.get(taskToTest));
			}
		}
		return tasksFound; 
	}
	@Override
	public String toString() {
		StringBuilder message = new StringBuilder();
		message.append(this.nom + " : \n" + this.description  
				       + "\nTache Prealable :");
		for (int nbTask = 0; nbTask < this.allTasks.size(); nbTask++) {
			message.append("\n - " + this.allTasks.get(nbTask).getNom());
		}
		message.append("\n");
		return message.toString();
	}
	/**
	 * @return Un message affichant precisement toutes les taches 
	 *         et leurs caracteristiques du projet
	 */
	public String printAll() {
		StringBuilder message = new StringBuilder();
		for (int task = 0; task < allTasks.size(); task++) {
			message.append("\n\n" + allTasks.get(task));
		}
		return message.toString();
	}
	public void addTask(Tache toAdd) {
		this.allTasks.add(toAdd);
	}
	public void removeTask(Tache toRemove) {
		this.allTasks.remove(toRemove);
	}
	
}
