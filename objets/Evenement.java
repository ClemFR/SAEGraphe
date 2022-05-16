package objets;

import java.util.ArrayList;

public class Evenement {
	
	private ArrayList<Tache> sortante = new ArrayList<>();
	private Duree datePlusTot = new Duree(0);
	private Duree auPlusTard = new Duree(0);
	private Duree margeLibre = new Duree(0);
	private Duree margeTotale = new Duree(0);
	public Evenement() {
		
	}
	public boolean predecesseursEgaux(Tache aTester) {
		
		
		if (sortante.get(0).getConditions().size() != aTester.getConditions().size()) {
			return false;
		}
		boolean ok = true;
		for (Tache predSortante : sortante.get(0).getConditions()) {
			ok &= aTester.getConditions().contains(predSortante);
		}

		return ok;
	}
	public void trouverMargeLibre() {
		int total;
		Tache aComparer;
		for (int i = 0; i < conditions.size(); i++) {
			aComparer = conditions.get(i);
			total = datePlusTot.getHeures() - aComparer.datePlusTot.getHeures()
					- aComparer.dureeTache.getHeures();
			aComparer.margeLibre.setHeures(total);
		}
	}
	public void trouverMargeTotale() {
		int total;
		Tache aComparer;
		for (int i = 0; i < conditions.size(); i++) {
			aComparer = conditions.get(i);
			total = auPlusTard.getHeures() - aComparer.datePlusTot.getHeures()
					- aComparer.dureeTache.getHeures();
			aComparer.margeTotale.setHeures(total);
		}
	}
	public void trouverDatePlusTard() {
		Tache precedente;
		int datePlusCourte = Integer.MAX_VALUE;
		
		for (int i = 0; i < conditions.size(); i++) {
			precedente = conditions.get(i);

			if (auPlusTard.getHeures() - precedente.dureeTache.getHeures() < datePlusCourte) {
				datePlusCourte = auPlusTard.getHeures() - precedente.dureeTache.getHeures();
			}
			
		}
		for (int i = 0; i < conditions.size(); i++) {
			conditions.get(i).auPlusTard.setHeures(datePlusCourte);
		}
	}
	/**
	 * Calcule la date au plus tot de cette tache par rapport aux taches prealable
	 */
	public void trouverDatePlusTot() {

		int dureePlusLongue;
		for (Tache aComparer : sortante.get(0).getConditions()) {
			
			dureePlusLongue = aComparer.getEvenementOrigine().getDatePlusTot() + aComparer.getDuree();
			if (datePlusTot.getHeures() < dureePlusLongue) {
				datePlusTot.setHeures(dureePlusLongue);
			}
		}
		
	}
	public int getDatePlusTot() {
		return this.datePlusTot.getHeures();
	}
	public void addTache(Tache aAjouter) {
		this.sortante.add(aAjouter);
	}
}
