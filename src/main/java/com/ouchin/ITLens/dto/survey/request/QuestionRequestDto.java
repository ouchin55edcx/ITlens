package com.ouchin.ITLens.dto.survey.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class QuestionRequestDto {
    @NotBlank(message = "Question text is required")
    private String text;

    @NotBlank(message = "Question type is required")
    private String type;

    @NotNull(message = "Subject ID is required")
    private Long subjectId;  // Subject ID should point to a SubSubject in the database

    @NotEmpty(message = "At least one answer is required")
    private List<AnswerRequestDto> answers;  // List of answers, assumed to be required
}

