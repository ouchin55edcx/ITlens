package com.ouchin.ITLens.dto.survey.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectResponseDto {
    private Long id;
    private String title;
    private Long surveyEditionId;
    private Long parentSubjectId;
    private List<SubjectResponseDto> subSubjects = new ArrayList<>();
    private List<QuestionResponseDto> questions = new ArrayList<>();
}
