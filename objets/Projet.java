package objets;

import java.util.ArrayList;

public class Projet {
    private String nom;
    private String description;
    private ArrayList<Tache> toutesTaches = new ArrayList<Tache>();
    private Tache FinDeProjet = new Tache("Fin", "Fin de projet",
                                      new Duree(0));

    public Projet(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    public void calculDate() {
        if (toutesTaches.contains(FinDeProjet)) {
            toutesTaches.remove(FinDeProjet);
        }
        toutesTaches = ordonnerTaches();
        for (int i = 0; i < toutesTaches.size(); i++) {
            toutesTaches.get(i).trouverDatePlusTot();
        }
        FinDeProjet.trouverDatePlusTot();
        FinDeProjet.setDatePlusTard(FinDeProjet.getDatePlusTot());
        toutesTaches.add(FinDeProjet);
        for (int i = toutesTaches.size() - 1; i >= 0; i--) {
            toutesTaches.get(i).trouverDatePlusTard();
            toutesTaches.get(i).trouverMargeTotale();
            toutesTaches.get(i).trouverMargeLibre();
        }
    }

    /**
     * Calcule les dates au plus tot de toute les taches d'un projet
     * // TODO Permettre de calculer les date au plus tard
     * // TODO Calculer la date de fin de projet
     */
    public ArrayList<Tache> ordonnerTaches() {
        ArrayList<Tache> filtre = new ArrayList<Tache>();
        ArrayList<Tache> tachesSuivantes
                = getTachesSuivantes(filtre);

        while (tachesSuivantes.size() != 0) {

            for (int calculDate = 0;
                 calculDate < tachesSuivantes.size();
                 calculDate++) {

                filtre.add(tachesSuivantes.get(calculDate));
            }
            // TODO : traduire variable
            if (getTachesSuivantes(tachesSuivantes).size() == 0) {
                for (int lastTasks = 0;
                         lastTasks < tachesSuivantes.size();
                         lastTasks++) {
                    // Création d'une tache qui sera la derniere tache du projet pour
                    // permettre de calculer la date de fin de projet
                    FinDeProjet.addTachePrecedente(tachesSuivantes
                            .get(lastTasks));
                }
            }
            tachesSuivantes
                    = getTachesSuivantes(tachesSuivantes);

        }

        return filtre;
    }

    /**
     * @param tachesPrecedentes un ensemble de tache servant de filtre
     * @return Un enssemble de tache ou chaque tache a un ensemble de
     * tache prealable qui est comprit dans le filtre.
     * Ou dans le cas ou le filtre est un ensemble vide
     * renvoie les taches qui n'ont pas de tache prealable
     */
    public ArrayList<Tache> getTachesSuivantes(ArrayList<Tache> tachesPrecedentes) {
        ArrayList<Tache> tachesTrouvees = new ArrayList<Tache>();
        for (int tachesATester = 0;
             tachesATester < toutesTaches.size();
             tachesATester++) {
            if (toutesTaches.get(tachesATester).verifierPredecesseurs(tachesPrecedentes)) {
                tachesTrouvees.add(toutesTaches.get(tachesATester));
            }
        }

        return tachesTrouvees;
    }

    @Override
    public String toString() {
        StringBuilder message = new StringBuilder();
        message.append(this.nom + " : \n" + this.description
                + "\nTache Prealable :");
        for (int tache = 0; tache < this.toutesTaches.size(); tache++) {
            message.append("\n - " + this.toutesTaches.get(tache).getNom());
        }
        message.append("\n");
        return message.toString();
    }

    /**
     * @return Un message affichant precisement toutes les taches
     * et leurs caracteristiques du projet
     */
    public String afficherTaches() {
        StringBuilder message = new StringBuilder();
        for (int tache = 0; tache < toutesTaches.size(); tache++) {
            message.append("\n\n" + toutesTaches.get(tache));
        }
        return message.toString();
    }

    public Tache getTache(int index) {
        return toutesTaches.get(index);
    }

    public int size() {
        return toutesTaches.size();
    }

    public void ajouterTache(Tache aAjouter) {
        this.toutesTaches.add(aAjouter);
    }

    public void retirerTache(Tache aRetirer) {
        this.toutesTaches.remove(aRetirer);
    }

}
