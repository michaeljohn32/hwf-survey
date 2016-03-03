package edu.umich.hwf.controllers;

import edu.umich.hwf.domain.Survey;
import edu.umich.hwf.domain.SurveyQuestion;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/survey-definition")
public class SurveySubmissionController {

    @ResponseStatus(HttpStatus.OK)
    public Survey getSurveyDefinition() {
        Survey survey = new Survey();
        survey.getSurveyQuestions().add(new SurveyQuestion(1, "How do you rate your Hack With Friends experience?"));
        return survey;
    }
}
