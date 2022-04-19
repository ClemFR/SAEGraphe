/* TestDuree.java                                                     17/04/2022
 *
 */
package info1.graphe.tools;

/**
 * Teste la classe Duree.
 */
public class TestDuree {
    public static void main(String[] args) {
        Duree d1 = new Duree(4);
        Duree d2 = new Duree(100);
        Duree d3 = new Duree(3, 5);
        try {
            Duree d4 = new Duree(-10);
        } catch(IllegalArgumentException e) {
            System.out.println("Erreur : " + e.getMessage());
        }

        Duree d5 = new Duree(4, 48);
        Duree d6 = new Duree(89, 31, 3, 25);
        Duree d7 = new Duree(4, 48, 30, 10);

        System.out.println("d1 = " + d1);
        System.out.println("d2 = " + d2);
        System.out.println("d3 = " + d3);
        System.out.println("d5 = " + d5);
        System.out.println("d6 = " + d6);
        System.out.println("d7 = " + d7);
    }


}
