package edu.umich.hwf.persistence;

import java.util.HashMap;
import java.util.Map;

public interface Persistence {

    void registerQuestionAndAnswer(String name, String value);

    boolean persistQuestion(String name, String value, boolean isFreeformAnswer);

    Map<String, Integer> getQuestionResults(String name);

    Map<String, HashMap<String, Integer>> getFullResults();
}
