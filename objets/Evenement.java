package objets;

import java.util.ArrayList;

public class Evenement {
	
	
	private Duree datePlusTot = new Duree(0);
	private Duree auPlusTard = new Duree(0);
	private Duree margeLibre = new Duree(0);
	private Duree margeTotale = new Duree(0);
	
	public Evenement() {
		
	}
	
	public int getDatePlusTot() {
		return this.datePlusTot.getHeures();
	}
	
}
