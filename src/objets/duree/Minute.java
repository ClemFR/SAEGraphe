package objets.duree;
public class Minute extends Temp {
	public Minute(double quantite) {
		super(quantite,3,'m');
		if (quantite >= 60) {
			throw new IllegalArgumentException("Nique ta mere met des heures");
		}
		
	}
	public double convertToPrevious(double quantite) {
		removeTime(quantite);
		return quantite / 60;
	}
	public double convertToNext(double quantite) {
		return 0;
	}
}
