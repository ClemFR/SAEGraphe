/* Duree.java                                                         17/04/2022
 * Diego Iglesias, pas de droits d'auteur
 */

package objets;

/**
 * Gestion de duréees.
 * Une durée est stockée sous la forme d'un entier positif representant un nombre d'heures.
 * Une duree est créée a  partir d'un nombre d'heures et/ou de jours
 * et/ou de semaines.
 *
 * Dans le cas ou la duree est creee a  partir d'un ou plusieurs nombres autres
 * qu'un nombre unique d'heures, la duree est convertie en heures
 * et est arrondie pour donner un nombre entier.
 * Les conversions sont realisees selon le schema suivant :
 *  - 1 semaine = 7 jours
 *  - 1 jour = 24 heures
 *
 * Exemple : creation d'une duree a  partir d'une duree
 * de 1 jour et 2 heures : duree = 24 + 2 = 26 heures.
 * Exemple 2 : creation d'une duree a  partir d'une duree
 * de 2.5 semaines, 1.3 jours et 1 heure : duree = 420 + 31 + 1 = 452 heures.
 *
 * Aucun nombre fourni pour la creation d'une duree ne peut etre negatif.
 * La duree peut etre affichee en heures, jour, semaine.
 */
public class Duree {

    private int heures;


    /**
     * Constructeur d'une duree a  partir d'un nombre d'heures.
     * @param heures nombre d'heures qu'on souhaite utiliser pour la duree.
     */
    public Duree(int heures) {
        if (heures < 0) {
            throw new IllegalArgumentException("Le nombre d'heures ne peut pas"
                                               + " etre negatif.");
        }

        this.heures = heures;

        

    }

    /**
     * Constructeur d'une duree a  partir d'un nombre de jours et d'heures.
     * @param jours nombre de jours qu'on souhaite utiliser pour la duree.
     * @param heures nombre d'heures qu'on souhaite utiliser pour la duree.
     */
    public Duree(double jours, int heures) {
        if (jours < 0 || heures < 0) {
            throw new IllegalArgumentException("Le nombre de jours" +
                    " et d'heures ne peuvent pas etre negatifs.");
        }

        this.heures = (int) Math.round(jours * 24) + heures;

    }

    /**
     * Constructeur d'une duree a partir d'un nombre de semaines,
     * de jours et d'heures.
     * @param semaines nombre de semaines qu'on souhaite utiliser pour la duree.
     * @param jours nombre de jours qu'on souhaite utiliser pour la duree.
     * @param heures nombre d'heures qu'on souhaite utiliser pour la duree.
     */
    public Duree(double semaines, double jours, int heures) {
        if (semaines < 0 || jours < 0 || heures < 0) {
            throw new IllegalArgumentException("Le nombre de semaines,"
                                               + " de jours et d'heures ne peuvent pas"
                                               + " etre negatifs.");
        }

        this.heures = (int) Math.round(semaines * 7 * 24)
                + (int) Math.round(jours * 24) + heures;
    }


    /**
     * Getter de la duree selon l'unite souhaitee
     * (s = semaine, j = jour, h = heure).
     * @param unite l'unite souhaitee.
     * @return la duree en fonction de l'unite souhaitee.
     */
    public double getDuree(char unite) {
        return switch (unite) {

            case 's' -> (double) heures / (7 * 24);
            case 'j' -> (double) heures / 24;
            case 'h' -> (double) heures;


            default -> throw new IllegalArgumentException("L'unite " +
                    "n'est pas reconnue.");
        };
    }

    /**
     * Renvoie une duree sous forme d'un tableau de 3 entiers.
     * L'indice 0 correspond au nombre de semaines, l'indice 1 au nombre de jours
     * et l'indice 2 au nombre d'heures.
     * Exemple : si la duree est de 2 heures,
     *           le tableau renvoye sera [ 0, 0, 2].
     * Exemple 2 : si la duree est de 342 heures,
     *             le tableau renvoye sera [2, 0, 6].
     * @return un tableau de 3 entiers.
     */
    public int[] getDureeTableau() {
        int dureeRestante = heures;
        int[] tableau = new int[3];
        tableau[2] = dureeRestante % 24;
        dureeRestante /= 24;
        tableau[1] = dureeRestante % 7;
        dureeRestante /= 7;
        tableau[0] = dureeRestante;
		return tableau;

    }

    /**
     * Methode toString()
     * @return la duree sous forme d'une chaine de caracteres.
     */
    @Override
    public String toString() {
        int[] tableau = getDureeTableau();
        String dureeString = "";
        if (tableau[0] != 0) {
            dureeString += tableau[0] + " semaines, ";
        }
        if (tableau[1] != 0) {
            dureeString += tableau[1] + " jours, ";
        }
        if (tableau[2] != 0) {
            dureeString += tableau[2] + " heures";
        }
        if (dureeString.equals("")) {
        	dureeString += "0 heures";
        }
        return dureeString;
    }
    /**
     * Getter de la duree.
     * @return la duree en heures.
     */

    public int getHeures() {
        return heures;
    }
    public void addDuree(int aAjouter) {
        this.heures += aAjouter;
    }
    public void setHeures(int aAjouter) {
        this.heures = aAjouter;
    }


}