package edu.umich.hwf.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SurveyQuestion {

    private Integer questionId;
    private String questionText;
    private String questionAnswer;

    public SurveyQuestion(Integer questionId, String questionText) {
        this.questionId = questionId;
        this.questionText = questionText;
    }
}
