package objets;
import objets.duree.*;
import java.util.ArrayList;
public class Tache {
	private ArrayList<Tache> prealable = new ArrayList<Tache>();
	private Temp[] duree = new Temp[4];
	private String nom;
	private String description;

	public Tache(double semaine , double jour , 
		         double heure , double minute , 
		         String nom , String description) {

		this.duree[0] = new Semaine(semaine);
		this.duree[1] = new Jour(jour);
		this.duree[2] = new Heure(heure);
		this.duree[3] = new Minute(minute);
		this.nom = nom;
		this.description = description;
	}
	public String toString() {
		StringBuilder message = new StringBuilder();

		message.append(this.nom + " :\ntemp restant : " + this.duree[0] + " "
												+ this.duree[1] + " "
												+ this.duree[2] + " "
												+ this.duree[3] + "\n"
			           + this.description + "\nTache Prealable :");
		for (int nbTask = 0; nbTask < this.prealable.size(); nbTask++) {
			message.append("\n - " + this.prealable.get(nbTask).getNom());
		}
		return message.toString();
	}
	public void addPreliminaryTask(Tache prealable) {
		this.prealable.add(prealable);
	}
	public void ConvertAllTo(int noUnite) {
		Temp current;
		Temp next;
		for (int unite1 = 0,
			     unite2 = duree.length -1; unite1 < noUnite
			                               || unite2 > noUnite;) {
			if (unite1 < noUnite) {
				current = this.duree[unite1];
				next = this.duree[unite1 + 1];
				next.addTime(current.convertToNext(current.getQuantite()));
				unite1++;
			}
			if (unite2 > noUnite) {
				current = this.duree[unite2];
				next = this.duree[unite2 - 1];
				next.addTime(current.convertToPrevious(current.getQuantite()));
				unite2--;
			}
		}
	}
	public String getNom() {
		return this.nom;
	}
}