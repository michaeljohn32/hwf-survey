package edu.umich.hwf.controllers;

import edu.umich.hwf.domain.AnswerType;
import edu.umich.hwf.domain.SurveyQuestion;
import edu.umich.hwf.persistence.MockStorage;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/survey-results")
public class SurveyResultsController {

    @Autowired
    private MockStorage mockStorage;

    @RequestMapping
    @ResponseStatus(HttpStatus.OK)
    public List<QuestionResult> getSurveyResults() {
        Map<String, HashMap<String, Integer>> results = mockStorage.getFullResults();
        List<QuestionResult> questionResults = new ArrayList<>();
        results.forEach((questionText, answerMap) -> {
            QuestionResult questionResult = new QuestionResult();
            questionResult.setQuestionText(questionText);
            questionResult.setFreeformAnswer(isQuestionFreeformAnswer(questionText));
            answerMap.forEach((answer, count) -> {
                questionResult.getAnswers().add(answer);
                questionResult.getCounts().add(count);
            });
            questionResults.add(questionResult);
        });
        return questionResults;
    }

    private boolean isQuestionFreeformAnswer(String questionText) {
        for (SurveyQuestion question : SurveyDefinitionController.survey.getQuestions()) {
            if (questionText!= null && questionText.equals(question.getQuestionText())) {
                return AnswerType.TEXTAREA.equals(question.getAnswerType());
            }
        }
        return false;
    }

    @Getter
    @Setter
    private class QuestionResult {

        private String questionText;
        private boolean isFreeformAnswer;
        private List<String> answers = new ArrayList<>();
        private List<Integer> counts = new ArrayList<>();
    }
}
