package edu.umich.hwf.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Survey {

    private List<SurveyQuestion> questions;

    public Survey() {
        questions = new ArrayList<>();
    }
}
