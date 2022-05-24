/* TestDuree.java                                                     17/04/2022
 *
 */
package tests;

import static java.lang.Double.MAX_VALUE;
import static outils.Outillage.AssurerEgaliteDouble;

import exception.EchecTest;
import objets.Duree;
/**
 * Teste la classe Duree.
 */
public class TestDuree {


    /**********************             JEUX DE DONNEES              **********************/

    private double[][] listeHorairesErreur = {

                /* Semaines  |   Jours   |  Heures  */
                {         -10,          0,          0},
                {   MAX_VALUE,  MAX_VALUE,  MAX_VALUE}, // On devrait peut être interdire des valeurs trop grande ( pas fait)
                {           0,        -10,          0},
                {           0,          0,        -10},
    };


    private Duree[] dureesValides = {
            new Duree(       80),
            new Duree(    80,23),
            new Duree(  40,8,23),
            new Duree(  45,5,12),
            new Duree(  45,5,12)
    };

    /**********************             RESULTATS ATTENDUES             **********************/

    
    public void testConstructor () {
        

        for (int i = 0; i < listeHorairesErreur.length; i++) {
            try {
                new Duree(listeHorairesErreur[i][0],
                          listeHorairesErreur[i][1],(int) listeHorairesErreur[i][2]);
                throw new EchecTest("Erreur , la duree invalide no " + i + " passe");
            } catch (Exception e) {
            }
        }
        
    }

    public void testToString(String[] resultatsAttendus) {
        

        for (int i = 0; i < resultatsAttendus.length; i++) {
            if (!resultatsAttendus[i].equals(dureesValides[i].toString())) {
                throw new EchecTest("Echec horaire toString no " + i);
            }
        }
    }

    
    public void testGetDuree(double[][] expectedResultForGetDuree) {
        

        double precision = 1e-5;
        for (int i = 0; i < expectedResultForGetDuree.length; i++) {
            if(!AssurerEgaliteDouble(dureesValides[i].getDuree('h'), expectedResultForGetDuree[i][0], precision)
               ||!AssurerEgaliteDouble(dureesValides[i].getDuree('j'), expectedResultForGetDuree[i][1], precision)
               ||!AssurerEgaliteDouble(dureesValides[i].getDuree('s'), expectedResultForGetDuree[i][2], precision)) { 
                throw new EchecTest("Echec test getDuree : no : " + i);
            }
        }
        
    }

    


}
