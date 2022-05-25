package objets;
/**
 * Evenement d'origine d'une taches un meme evenement peut -être associé
 * a plusieur taches.
 * @author Eleanor Mourgue , Charlie Sarrato-Boudet , 
 *         clement laurie , Diego Iglesias , Medard Guillaume
 *
 */
public class Evenement {
	
	
	private Duree datePlusTot;
	private Duree datePlusTard;
	
	
	public Evenement() {
		datePlusTot = new Duree(0);
		datePlusTard = new Duree(0);
		
	}
	
	public Duree getDatePlusTot() {
		return datePlusTot;
	}
	public Duree getDatePlusTard() {
		return datePlusTard;
	}
	

	public void setDatePlusTot(int duree) {
		datePlusTot.setHeures(duree);
	}

	public void setDatePlusTard(int duree) {
		datePlusTard.setHeures(duree);
	}

	
	
}
