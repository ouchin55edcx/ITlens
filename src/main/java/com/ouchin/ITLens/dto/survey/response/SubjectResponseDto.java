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
    private Long parentSubjectId;  // Can be null for top-level subjects
    private List<SubjectResponseDto> subSubjects = new ArrayList<>();  // Only child subjects
    private List<QuestionResponseDto> questions = new ArrayList<>();  // Questions are only tied to SubSubjects

    // You might want to add constructors or methods for initializing lists or more advanced use cases.
}
