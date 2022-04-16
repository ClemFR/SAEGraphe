package objets.duree;
public class Jour extends Temp {

	public Jour(double quantite) {
		super(quantite,1,'j');
		if (quantite >= 7) {
			throw new IllegalArgumentException("Nique ta mere met des semaines");
		}
		
	}
	public double convertToNext(double quantite) {
		this.removeTime(quantite);
		return 24 * quantite;
	}
	public double convertToPrevious(double quantite) {
		this.removeTime(quantite);
		return quantite / 7;
	}

}