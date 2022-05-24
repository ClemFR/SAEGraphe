package objets;
import exception.CycleException;

import java.util.ArrayList;

public class Tache {
	private ArrayList<Tache> conditions = new ArrayList<Tache>();
	
	public Duree dureeTache;
	private String nom;
	private String description;
	private Evenement origine;

	public Tache(String nom , String description, Duree deLaTache) {
		this.dureeTache = deLaTache;
		this.nom = nom;
		this.description = description;
		this.origine = null;
	}
	
	//TODO
	public void trouverMargeLibre() {
	
	}
	//TODO
	public void trouverMargeTotale() {
		
	}
	//TODO
	public void trouverDatePlusTard() {
		
	}
	
	//TODO
	public void trouverDatePlusTot() {
	
		
	}

	/**
	 * @param tachesPrecedentes un filtre de tache
	 * @return true si l'enssemble de tache prealable est comprit dans le filtre
	 *         ou si le filtre et l'enssemble de tache prealable sont vide
	 *         sinon false
	 */
	public boolean verifierPredecesseurs(ArrayList<Tache> tachesPrecedentes) {
		boolean filtreVide = (tachesPrecedentes == null || tachesPrecedentes.isEmpty())
				              && this.getConditions().isEmpty();
		boolean isTachesPrecedentesIncluses = false;
		if (!filtreVide && !this.getConditions().isEmpty()) {
			isTachesPrecedentesIncluses = true;
			for (int i = 0; i < this.getConditions().size(); i++) {
				isTachesPrecedentesIncluses
					&= tachesPrecedentes.contains(this.getConditions().get(i));
			} 
		}		        
		return isTachesPrecedentesIncluses || filtreVide;
	}
	
	@Override
	public String toString() {
		StringBuilder message = new StringBuilder();

		return message.toString();
	}




    public boolean detectionCycle(Tache prealable) {
        // recherche de cycle potentiel
        ArrayList<Tache> tachesPrecentes = new ArrayList<Tache>();
        ArrayList<Tache> tachesAnalysees = new ArrayList<Tache>();
        boolean cycleTrouve;

        ArrayList<Tache> tachesATraiter = new ArrayList<Tache>();
        tachesPrecentes = prealable.getConditions();
        cycleTrouve = false;

        do {
            for (Tache e : tachesPrecentes) {
                if (e.equals(this)) {
                    cycleTrouve = true;
                } 
                if (!tachesAnalysees.contains(e)) {
                    tachesAnalysees.add(e);
                }
                for (Tache a : e.getConditions()) {
                    if (!tachesATraiter.contains(a) && !tachesAnalysees.contains(a)) {
                        tachesATraiter.add(a);
                    }
                }
            }
            tachesPrecentes = tachesATraiter;
            tachesATraiter.clear();
        } while (tachesPrecentes.size() != 0 && !cycleTrouve);
        return cycleTrouve;
    }



	public void addTachePrecedente(Tache prealable) {
        if(detectionCycle(prealable)) {
           throw new CycleException("Cycle trouve");
        }
        conditions.add(prealable);
	}
	
	public String getNom() {
		return this.nom;
	}
	public int getDuree() {
		return this.dureeTache.getHeures();
	}
	public ArrayList<Tache> getConditions() {
		return this.conditions;
	}
}