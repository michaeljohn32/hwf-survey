package edu.umich.hwf.persistence;

import java.util.HashMap;
import java.util.Map;

public interface GooglePersistence {
    /** Add question-answer pair to the database */
    boolean addQuestionAnswer(String name, String value);

    /* Record user's choice for this question/answer; returns false if no such question/answer exists */
    boolean persistQuestion(String name, String value);

    Map<String, Integer> getQuestionResults(String name);

    Map<String, HashMap<String, Integer>> getFullResults();

}
