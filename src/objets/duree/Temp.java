package objets.duree;
public abstract class Temp {

	private double quantite;
	private char unite;
	private int position;


	public Temp(double quantite, int position, char unite) {
		this.quantite = quantite;
		this.unite = unite;
		this.position = position;
	}

	public String toString() {
		return quantite + "" + unite;
	}
	public abstract double convertToNext(double quantite);
	public abstract double convertToPrevious(double quantite);
	public void addTime(double quantite) {
		this.quantite += quantite;
	}
	public void removeTime(double quantite) {
		this.quantite -= quantite;
	}
	public double getQuantite() {
		return this.quantite;
	}
	public int getPosition() {
		return this.position;
	}
}