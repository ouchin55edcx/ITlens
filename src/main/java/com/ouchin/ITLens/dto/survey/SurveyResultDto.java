package com.ouchin.ITLens.dto.survey;

import lombok.Data;

import java.util.List;

@Data
public class SurveyResultDto {
    private String surveyTitle;
    private List<SubjectResultDto> subjects;
}
