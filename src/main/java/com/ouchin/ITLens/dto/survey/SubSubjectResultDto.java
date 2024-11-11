package com.ouchin.ITLens.dto.survey;

import lombok.Data;

import java.util.Map;

@Data
public class SubSubjectResultDto {
    private String title;
    private String question;
    private Map<String, Integer> answers;
    private int totalAnswers;
}
