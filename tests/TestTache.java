package tests;

import objets.Duree;
import objets.Tache;
import static tests.Tests.setOfValidDuree;
import static tests.Tests.setOfValidTask;

import java.util.ArrayList;

public class TestTache {
	
	
	
	protected static void InitPriorities() {
		setOfValidTask[1].addPreliminaryTask(setOfValidTask[0]);
		setOfValidTask[2].addPreliminaryTask(setOfValidTask[0]);
		setOfValidTask[4].addPreliminaryTask(setOfValidTask[3]);
		setOfValidTask[3].addPreliminaryTask(setOfValidTask[1]);
		setOfValidTask[3].addPreliminaryTask(setOfValidTask[2]);
		
		
	}
	protected static int[] exceptedEarliestDate= {
			0,80,80,7015,14707
	};
	protected static boolean testFindEarliestDate() {
		boolean testOk = true;
		for (int i = 0; i < exceptedEarliestDate.length; i++) {
			setOfValidTask[i].FindEarliestDate();
			testOk &= setOfValidTask[i].getEarliestDate().getHours() == exceptedEarliestDate[i];

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
			testOk &= expectedForHasTheseConditions[0][i] == setOfValidTask[i].hasTheseConditions(condition);
			
		}
		condition.add(setOfValidTask[0]);
		for (int i = 0; i < expectedForHasTheseConditions[1].length; i++) {
			testOk &= expectedForHasTheseConditions[1][i] == setOfValidTask[i].hasTheseConditions(condition);
		}
		condition.clear();
		condition.add(setOfValidTask[1]);
		condition.add(setOfValidTask[2]);
		for (int i = 0; i < expectedForHasTheseConditions[2].length; i++) {
			testOk &= expectedForHasTheseConditions[2][i] == setOfValidTask[i].hasTheseConditions(condition);
		}
		condition.clear();
		condition.add(setOfValidTask[3]);
		for (int i = 0; i < expectedForHasTheseConditions[2].length; i++) {
			testOk &= expectedForHasTheseConditions[3][i] == setOfValidTask[i].hasTheseConditions(condition);
		}	
		return testOk;
	}
}
