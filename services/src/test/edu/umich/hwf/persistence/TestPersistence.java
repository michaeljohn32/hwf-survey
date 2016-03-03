package edu.umich.hwf.persistence;

import java.util.Map;
import java.util.Set;

public class TestPersistence {
	static GooglePersistence persistence = new mockStorage();
	
	public static void main(String[] args) {
		
		persistence.persistQuestion("Q1", "a");
		persistence.persistQuestion("Q1", "b");
		persistence.persistQuestion("Q1", "a");
		persistence.persistQuestion("Q1", "b");
		persistence.persistQuestion("Q2", "Yes");
		persistence.persistQuestion("Q2", "No");
		
		printQuestionResults("Q1");
		printQuestionResults("Q2");
	}

	private static void printQuestionResults(String name){
		
		Map<String, Integer> q1 = persistence.getQuestionResults(name);
		Set<String> keys = q1.keySet();
		for (String key : keys ) {
			System.out.println(key + "= " + q1.get(key));
		}
	}
}