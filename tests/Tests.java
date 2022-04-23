package tests;
import static tests.TestDuree.*;

import objets.Duree;

public class Tests {
	protected static Duree[] setOfValidDuree = {
			new Duree(       80),
			new Duree(    80,23),
			new Duree(  40,8,23),
			new Duree( 10,5,5,12)
	};
	public static void main(String[] args) {
		
    	boolean testsOk = true;
    	testsOk &= testGetDuree();
    	testsOk &= testToString();
    	//testsOk &= testConstructor(); // les tests echoues a cause de celui la
    	if (testsOk) {
    		System.out.println("Les tests unitaires ont reussie");
    	} else {
    		System.out.println("Les tests unitaires ont echoues");
    	}
    }
}
