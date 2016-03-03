package edu.umich.hwf.controllers;

import edu.umich.hwf.persistence.MockStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/survey-results")
public class SurveyResultsController {

    @Autowired
    private MockStorage mockStorage;

    @RequestMapping
    @ResponseStatus(HttpStatus.OK)
    public Map<String, HashMap<String, Integer>> getSurveyResults() {
        return mockStorage.getFullResults();
    }
}
