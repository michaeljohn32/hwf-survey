package edu.umich.hwf.persistence;

import java.util.HashMap;
import java.util.Map;

public interface Persistence {

    void registerQuestionAndAnswer(String name, String value);

    boolean persistQuestion(String name, String value, boolean isFreeformAnswer);

    void persistQuestions(Map<String,String> responses);

    Map<String, Integer> getQuestionResults(String name);

    Map<String, HashMap<String, Integer>> getFullResults();
}
