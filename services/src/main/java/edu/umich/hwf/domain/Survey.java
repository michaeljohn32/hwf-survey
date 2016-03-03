package edu.umich.hwf.domain;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Survey {

    private List<SurveyQuestion> surveyQuestions;
}
