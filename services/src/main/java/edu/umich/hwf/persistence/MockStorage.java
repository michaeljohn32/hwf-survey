package edu.umich.hwf.persistence;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MockStorage implements GooglePersistence {
    HashMap<String, HashMap<String, Integer>> surveyResults = new HashMap<String, HashMap<String, Integer>>();

    public boolean addQuestionAnswer(String question, String answer) {
        try {
            if (surveyResults.containsKey(question)) {

                HashMap<String, Integer> questionAnswers = surveyResults.get(question);
                if (!questionAnswers.containsKey(answer)) {
                    questionAnswers.put(answer, 0);
                }

            } else {
                HashMap<String, Integer> questionAnswers = new HashMap<String, Integer>();
                questionAnswers.put(answer, 0);
                surveyResults.put(question, questionAnswers);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean persistQuestion(String question, String answer) {
        try {
            Map<String, Integer> answers=surveyResults.get(question);
            if (answers==null) return false;
            Integer count=answers.get(answer);
            if (count==null) return false;
            answers.put(answer, count+1);
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
