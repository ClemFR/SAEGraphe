package objets.duree;
public class Heure extends Temp {

	public Heure(double quantite) {
		super(quantite,2,'h');
		if (quantite >= 24) {
			throw new IllegalArgumentException("Nique ta mere met des jours");
		}
		
	}
	public double convertToNext(double quantite) {
		this.removeTime(quantite);
		return 60 * quantite;
	}
	public double convertToPrevious(double quantite) {
		this.removeTime(quantite);
		return quantite / 24;
	}
}
