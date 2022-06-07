package objets;

import java.io.Serializable;

/**
 * Evenement d'origine d'une taches un meme evenement peut -être associé
 * a plusieur taches.
 * @author Eleanor Mourgue , Charlie Sarrato-Boudet , 
 *         clement laurie , Diego Iglesias , Medard Guillaume
 *
 */
public class Evenement {
	
	private double datePlusTot;

	private double datePlusTard;
	
	
	public Evenement() {
		//Double.NaN --> Date pas encore calculée
		datePlusTot = Double.NaN;
		datePlusTard = Double.NaN;
		
	}
	
	public double getDatePlusTot() {
		return datePlusTot;
	}
	public double getDatePlusTard() {
		return datePlusTard;
	}
	

	public void setDatePlusTot(int duree) {
		datePlusTot = duree;
	}

	public void setDatePlusTard(int duree) {
		datePlusTard = duree;
	}

	
	
}
