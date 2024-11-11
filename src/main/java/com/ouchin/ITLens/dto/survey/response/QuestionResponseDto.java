package com.ouchin.ITLens.dto.survey.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class QuestionResponseDto {
    private Long id;
    private String text;
    private String type;
    private Long subjectId;  // ID of the SubSubject where the question is located
    private List<AnswerResponseDto> answers;  // List of answers for this question
}
