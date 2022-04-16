package objets.duree;
public class Semaine extends Temp {
	public Semaine(double quantite) {
		super(quantite,0,'s');
	}

	public double convertToPrevious(double quantite) {
		return 0;
	}
	public double convertToNext(double quantite) {
		this.removeTime(quantite);
		return quantite * 7;
	}
}