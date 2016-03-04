package edu.umich.hwf.controllers;

import edu.umich.hwf.domain.Survey;
import edu.umich.hwf.persistence.MockStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/survey-submission")
public class SurveySubmissionController {

    @Autowired
    private MockStorage mockStorage;

    @RequestMapping
    @ResponseStatus(HttpStatus.OK)
    public void submitSurvey(@RequestBody Survey surveyResponse) {
        Map<String, String> submission = new LinkedHashMap<>();
        surveyResponse.getQuestions().forEach(surveyQuestion -> {
            if (surveyQuestion.getQuestionText() != null && surveyQuestion.getSelectedAnswer() != null) {
                submission.put(surveyQuestion.getQuestionText(), surveyQuestion.getSelectedAnswer());
            }
        });
        mockStorage.persistQuestions(submission);
    }
}
