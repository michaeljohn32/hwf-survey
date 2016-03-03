package edu.umich.hwf.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SurveyQuestion {

    public static final String ANSWER_YES = "Yes";
    public static final String ANSWER_NO = "No";
    public static final String ANSWER_EXTREMELY_LOW = "Extremely Low";
    public static final String ANSWER_LOW = "Low";
    public static final String ANSWER_AVERAGE = "Average";
    public static final String ANSWER_ABOVE_AVERAGE = "Above Average";
    public static final String ANSWER_EXTREMELY_HIGH = "Extremely High";

    private String questionText;
    private AnswerType answerType;
    private String freeformAnswer;
    private List<String> availableAnswers;

    public SurveyQuestion(String questionText, AnswerType answerType) {
        this(questionText, answerType, null);
    }

    public SurveyQuestion(String questionText, AnswerType answerType, List<String> availableAnswers) {
        this.questionText = questionText;
        this.answerType = answerType;
        this.availableAnswers = availableAnswers;
    }
}
