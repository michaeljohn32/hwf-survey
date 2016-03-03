package edu.umich.hwf.persistence;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MockStorage implements GooglePersistence {
    HashMap<String, HashMap<String, Integer>> surveyResults = new HashMap<>();

    public boolean persistQuestion(String name, String value) {
        try {
            if (surveyResults.containsKey(name)) {

                HashMap<String, Integer> questionValues = surveyResults.get(name);
                if (questionValues.containsKey(value)) {
                    Integer count = questionValues.get(value);
                    questionValues.put(value, count + 1);
                } else {
                    questionValues.put(value, 1);
                }

            } else {
                HashMap<String, Integer> question = new HashMap<>();
                question.put(value, 1);
                surveyResults.put(name, question);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Map<String, Integer> getQuestionResults(String name) {
        if (surveyResults.containsKey(name)) {
            return surveyResults.get(name);
        } else {
            return null;
        }
    }

    public HashMap<String, HashMap<String, Integer>> getFullResults() {
        return surveyResults;
    }
}
