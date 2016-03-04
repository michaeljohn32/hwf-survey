package edu.umich.hwf.controllers;

import edu.umich.hwf.domain.AnswerType;
import edu.umich.hwf.domain.Survey;
import edu.umich.hwf.persistence.MockStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/survey-submission")
public class SurveySubmissionController {

    @Autowired
    private MockStorage mockStorage;

    @RequestMapping
    @ResponseStatus(HttpStatus.OK)
    public void submitSurvey(@RequestBody Survey surveyResponse) {
        surveyResponse.getQuestions().forEach(surveyQuestion -> {
            if (surveyQuestion.getQuestionText() != null && surveyQuestion.getSelectedAnswer() != null) {
                mockStorage.persistQuestion(surveyQuestion.getQuestionText(), surveyQuestion.getSelectedAnswer(),
                        AnswerType.TEXTAREA.equals(surveyQuestion.getAnswerType()));
            }
        });
    }
}
