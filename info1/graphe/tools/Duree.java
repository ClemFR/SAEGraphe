/* Duree.java                                                         17/04/2022
 * Diego Iglesias, pas de droits d'auteur
 */

package info1.graphe.tools;

/**
 * Gestion de durées.
 * Une durée est stockée sous la forme d'un entier positif
 * représentant un nombre d'heures.
 * Une durée est créée à partir d'un nombre d'heures et/ou de jours
 * et/ou de semaines et/ou de mois.
 *
 * Dans le cas où la durée est créée à partir d'un ou plusieurs nombres autres
 * qu'un nombre unique d'heures,
 * la durée est convertie en heures et est arrondie
 * pour donner un nombre entier.
 * Les conversions sont réalisées selon le schéma suivant :
 *  - 1 mois = 4 semaines
 *  - 1 semaine = 7 jours
 *  - 1 jour = 24 heures
 *
 * Exemple : création d'une durée à partir d'une durée
 * de 1 jour et 2 heures : duree = 24 + 2 = 26 heures.
 * Exemple 2 : création d'une durée à partir d'une durée
 * de 2.5 semaines, 1.3 jours et 1 heure : duree = 420 + 31 + 1 = 452 heures.
 *
 * Aucun nombre fourni pour la création d'une durée ne peut être négatif.
 * La durée peut être affichée en heures, jour, semaine, mois, année.
 */
public class Duree {
    private int duree;

    /**
     * Constructeur d'une duree à partir d'un nombre d'heures.
     * @param heures nombre d'heures qu'on souhaite utiliser pour la duree.
     */
    public Duree(int heures) {
        if(heures < 0) {
            throw new IllegalArgumentException("Le nombre d'heures ne peut" +
                    " pas être négatif.");
        }
        duree = heures;
    }

    /**
     * Constructeur d'une duree à partir d'un nombre de jours et d'heures.
     * @param jours nombre de jours qu'on souhaite utiliser pour la duree.
     * @param heures nombre d'heures qu'on souhaite utiliser pour la duree.
     */
    public Duree(double jours, int heures) {
        if(jours < 0 || heures < 0) {
            throw new IllegalArgumentException("Le nombre de jours" +
                    " et d'heures ne peuvent pas être négatifs.");
        }
        duree = (int) Math.round(jours * 24) + heures;
    }

    /**
     * Constructeur d'une duree à partir d'un nombre de semaines,
     * de jours et d'heures.
     * @param semaines nombre de semaines qu'on souhaite utiliser pour la duree.
     * @param jours nombre de jours qu'on souhaite utiliser pour la duree.
     * @param heures nombre d'heures qu'on souhaite utiliser pour la duree.
     */
    public Duree(double semaines, double jours, int heures) {
        if(semaines < 0 || jours < 0 || heures < 0) {
            throw new IllegalArgumentException("Le nombre de semaines," +
                    " de jours et d'heures ne peuvent pas être négatifs.");
        }
        duree = (int) Math.round(semaines * 7 * 24)
                + (int) Math.round(jours * 24) + heures;
    }

    /**
     * Constructeur d'une duree à partir d'un nombre de mois, de semaines,
     * de jours et d'heures.
     * @param mois nombre de mois qu'on souhaite utiliser pour la duree.
     * @param semaines nombre de semaines qu'on souhaite utiliser pour la duree.
     * @param jours nombre de jours qu'on souhaite utiliser pour la duree.
     * @param heures nombre d'heures qu'on souhaite utiliser pour la duree.
     */
    public Duree(double mois, double semaines, double jours, int heures) {
        if(mois < 0 || semaines < 0 || jours < 0 || heures < 0) {
            throw new IllegalArgumentException("Le nombre de mois, " +
                    "de semaines, de jours et d'heures ne peuvent pas être" +
                    " négatifs.");
        }
        duree = (int) Math.round(mois * 4 * 7 * 24)
                + (int) Math.round(semaines * 7 * 24)
                + (int) Math.round(jours * 24) + heures;
    }

    /**
     * Getter de la duree.
     * @return la duree en heures.
     */
    public int getDuree() {
        return duree;
    }

    /**
     * Getter de la duree selon l'unité souhaitée
     * (m = mois, s = semaine, j = jour, h = heure).
     * @param unite l'unité souhaitée.
     * @return la duree en fonction de l'unité souhaitée.
     */
    public double getDuree(char unite) {
        return switch (unite) {
            case 'm' -> (double) duree / (30 * 24);
            case 's' -> (double) duree / (7 * 24);
            case 'j' -> (double) duree / 24;
            case 'h' -> (double) duree;
            default -> throw new IllegalArgumentException("L'unité " +
                    "n'est pas reconnue.");
        };
    }

    /**
     * Renvoie une duree sous forme d'un tableau de 4 entiers.
     * L'indice 0 correspond au nombre de mois,
     * l'indice 1 au nombre de semaines,
     * l'indice 2 au nombre de jours et l'indice 3 au nombre d'heures.
     * Exemple : si la duree est de 2 heures,
     * le tableau renvoyé sera [0, 0, 0, 2].
     * Exemple 2 : si la duree est de 342 heures,
     * le tableau renvoyé sera [9, 3, 2, 3].
     * @return un tableau de 4 entiers.
     */
    public int[] getDureeTableau() {
        int[] tableau = new int[4];
        int dureeRestante = duree;
        tableau[0] = (int) Math.floor((double) dureeRestante / (30 * 24));
        dureeRestante -= tableau[0] * 30 * 24;
        tableau[1] = (int) Math.floor((double) dureeRestante / (7 * 24));
        dureeRestante -= tableau[1] * 7 * 24;
        tableau[2] = (int) Math.floor((double) dureeRestante / 24);
        dureeRestante -= tableau[2] * 24;
        tableau[3] = dureeRestante;

        // Résoudre un petit problème de conversion,
        // il peut arriver que le programme retourne un tableau avec
        // une duree en semaine de 4 alors que cela devrait être 0 et 1 de plus
        // dans les mois.
        // A explorer car ce problème pourrait être dans les autres unités.
        // Cas d'erreur : new Duree(4, 48, 30, 10).toString();
        return tableau;
    }

    /**
     * Methode toString()
     * @return la duree sous forme d'une chaine de caracteres.
     */
    @Override
    public String toString() {
        int[] tableau = getDureeTableau();
        boolean aPrecedent = false;
        String dureeString = "";
        if(tableau[0] != 0) {
            dureeString += tableau[0] + " mois";
            aPrecedent = true;
        }
        if(tableau[1] != 0) {
            if(aPrecedent) {
                dureeString += ", ";
            }
            dureeString += tableau[1] + " semaines";
            aPrecedent = true;
        }
        if(tableau[2] != 0) {
            if(aPrecedent) {
                dureeString += ", ";
            }
            dureeString += tableau[2] + " jours";
            aPrecedent = true;
        }
        if(tableau[3] != 0) {
            if(aPrecedent) {
                dureeString += ", ";
            }
            dureeString += tableau[3] + " heures";
        }
        return dureeString;
    }

}
