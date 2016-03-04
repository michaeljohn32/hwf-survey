package edu.umich.hwf.persistence;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mdemerat on 3/4/16.
 */
public class GoogleStorage implements Persistence {

    public boolean addQuestionAnswer(String name, String value) {
        return false;
    }

    public boolean persistQuestion(String name, String value) {
        return false;
    }
    public void persistQuestions(Map<String, String> responses) {
    }

    public void registerQuestionAndAnswer(String name, String value) {

    }

    public boolean persistQuestion(String name, String value, boolean isFreeformAnswer) {
        return false;
    }

    public Map<String, Integer> getQuestionResults(String name) {
        return null;
    }

    public Map<String, HashMap<String, Integer>> getFullResults() {
        return null;
    }
}
