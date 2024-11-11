package com.ouchin.ITLens.dto.survey;

import lombok.Data;

import java.util.List;

@Data
public class SubjectResultDto {
    private String title;
    private List<SubSubjectResultDto> subSubjects;
}
