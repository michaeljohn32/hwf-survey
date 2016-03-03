package edu.umich.hwf.domain;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Survey {

    private List<SurveyQuestion> questions;

    public Survey() {
        questions = new ArrayList<>();
    }
}
