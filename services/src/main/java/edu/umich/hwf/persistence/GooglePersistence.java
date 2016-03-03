package edu.umich.hwf.persistence;

import java.util.HashMap;
import java.util.Map;

public interface GooglePersistence {

	boolean persistQuestion(String name, String value);
	Map<String, Integer> getQuestionResults(String name);
	Map<String, HashMap<String, Integer>> getFullResults();
	
}
