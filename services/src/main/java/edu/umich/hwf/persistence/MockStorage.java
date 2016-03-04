package edu.umich.hwf.persistence;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class MockStorage implements Persistence {

    private HashMap<String, HashMap<String, Integer>> surveyResults = new LinkedHashMap<>();

    public void registerQuestionAndAnswer(String question, String answer) {
        if (surveyResults.containsKey(question)) {
            HashMap<String, Integer> questionAnswers = surveyResults.get(question);
            if (!questionAnswers.containsKey(answer)) {
                questionAnswers.put(answer, 0);
            }
        } else {
            HashMap<String, Integer> questionAnswers = new LinkedHashMap<>();
            questionAnswers.put(answer, 0);
            surveyResults.put(question, questionAnswers);
        }
    }

    public boolean persistQuestion(String question, String answer, boolean isFreeformAnswer) {
        Map<String, Integer> answers = surveyResults.get(question);
        boolean questionIsRegistered = answers != null;
        if (!questionIsRegistered) {
            if (!isFreeformAnswer) {
                return false;
            } else {
                registerQuestionAndAnswer(question, answer);
                answers = surveyResults.get(question);
            }
        }

        Integer count = answers.get(answer);
        boolean answerAlreadyRegistered = count != null;
        if (!answerAlreadyRegistered) {
            if (!isFreeformAnswer) {
                return false;
            } else {
                registerQuestionAndAnswer(question, answer);
                answers = surveyResults.get(question);
                count = 0;
            }
        }

        answers.put(answer, count+1);
        return true;
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
