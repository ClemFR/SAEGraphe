package objets;
import java.util.ArrayList;
public class Tache {
	private ArrayList<Tache> prealable = new ArrayList<Tache>();
	private int[] duree;
	private String nom;
	private String description;

	public Tache(String nom , String description, Duree deLaTache) {
		this.duree = deLaTache.getDureeTableau();
		this.nom = nom;
		this.description = description;
	}
	public String toString() {
		StringBuilder message = new StringBuilder();

		message.append(this.nom + " :\ntemp restant : " + this.duree + "\n"
			           + this.description + "\nTache Prealable :");
		for (int nbTask = 0; nbTask < this.prealable.size(); nbTask++) {
			message.append("\n - " + this.prealable.get(nbTask).getNom());
		}
		return message.toString();
	}
	public void addPreliminaryTask(Tache prealable) {
		this.prealable.add(prealable);
	}
	public String getNom() {
		return this.nom;
	}
}