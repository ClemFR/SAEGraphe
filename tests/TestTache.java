package tests;

import objets.Duree;
import objets.Tache;

import static tests.Tests.setOfValidTask;

import java.util.ArrayList;

public class TestTache {
	
	
	
	protected static void InitPriorities() {
		setOfValidTask[1].addTachePrecedente(setOfValidTask[0]);
		setOfValidTask[2].addTachePrecedente(setOfValidTask[0]);
		setOfValidTask[4].addTachePrecedente(setOfValidTask[3]);
		setOfValidTask[3].addTachePrecedente(setOfValidTask[1]);
		setOfValidTask[3].addTachePrecedente(setOfValidTask[2]);
		
		
	}
	protected static int[] exceptedEarliestDate= {
			0,80,80,7015,14707
	};
	protected static boolean testFindEarliestDate() {
		boolean testOk = true;
		for (int i = 0; i < exceptedEarliestDate.length; i++) {
			setOfValidTask[i].trouverDatePlusTot();
			testOk &= setOfValidTask[i].getDatePlusTot().getHeures() == exceptedEarliestDate[i];

		}
		
		return testOk;
	}
	protected static boolean[][] expectedForHasTheseConditions = {
			{true,false,false,false,false},
			{false,true,true,false,false},
			{false,false,false,true,false},
			{false,false,false,false,true}
	};

	protected static boolean testVerifierCondition() {
		boolean testOk = true;
		ArrayList<Tache> condition = new ArrayList<Tache>();
		for (int i = 0; i < expectedForHasTheseConditions[0].length; i++) {
			testOk &= expectedForHasTheseConditions[0][i] == setOfValidTask[i].verifierPredecesseurs(condition);
			System.out.println(setOfValidTask[i].verifierPredecesseurs(condition));
		}
		System.out.println("------------------");
		condition.add(setOfValidTask[0]);
		for (int i = 0; i < expectedForHasTheseConditions[1].length; i++) {
			testOk &= expectedForHasTheseConditions[1][i] == setOfValidTask[i].verifierPredecesseurs(condition);
			System.out.println(setOfValidTask[i].verifierPredecesseurs(condition));
		}
		System.out.println("------------------");
		condition.clear();
		condition.add(setOfValidTask[1]);
		condition.add(setOfValidTask[2]);
		for (int i = 0; i < expectedForHasTheseConditions[2].length; i++) {
			testOk &= expectedForHasTheseConditions[2][i] == setOfValidTask[i].verifierPredecesseurs(condition);
			System.out.println(setOfValidTask[i].verifierPredecesseurs(condition));
		}
		System.out.println("------------------");
		condition.clear();
		condition.add(setOfValidTask[3]);
		for (int i = 0; i < expectedForHasTheseConditions[3].length; i++) {
			testOk &= expectedForHasTheseConditions[3][i] == setOfValidTask[i].verifierPredecesseurs(condition);
			System.out.println(setOfValidTask[i].verifierPredecesseurs(condition));
		}	
		return testOk;
	}


	protected static boolean testCycle() {
		boolean testOk = true;

		Duree[] jeuDureesValides = {
				new Duree(       80),
				new Duree(    80,23),
				new Duree(  40,8,23),
				new Duree(  45,5,12),
				new Duree(  45,5,12)
		};
		Tache[] jeuTachesValides = {
				new Tache("Ecrire","Apprendre a écrire pour MR Barrios",jeuDureesValides[0]),
				new Tache("Test","Faire les jeux de tests pour MR Barrios",jeuDureesValides[1]),
				new Tache("Algo","Faire une partie de l'algo",jeuDureesValides[2]),
				new Tache("Pause","Faire une pause pour pas peter un plomb",jeuDureesValides[3]),
				new Tache("FinirAlgo","Finir l'algo",jeuDureesValides[4]),

		};
		jeuTachesValides[1].addTachePrecedente(jeuTachesValides[0]);
		jeuTachesValides[2].addTachePrecedente(jeuTachesValides[0]);
		jeuTachesValides[4].addTachePrecedente(jeuTachesValides[3]);
		jeuTachesValides[3].addTachePrecedente(jeuTachesValides[1]);
		jeuTachesValides[3].addTachePrecedente(jeuTachesValides[2]);
		//Creation du cycle avec ce lien
		try {
			jeuTachesValides[2].addTachePrecedente(jeuTachesValides[3]);
			testOk = false;
		} catch (Exception e) {
			testOk = true;
		}


		return testOk;
	}
}
