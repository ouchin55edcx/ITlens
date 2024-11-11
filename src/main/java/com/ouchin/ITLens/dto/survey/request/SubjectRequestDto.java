package com.ouchin.ITLens.dto.survey.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectRequestDto {

    @NotBlank(message = "Subject title is required")
    @Size(min = 2, max = 100, message = "Title must be between 2 and 100 characters")
    private String title;

    @NotNull(message = "Survey edition ID is required")
    private Long surveyEditionId;

    private Long parentSubjectId;  // Can be null if it's a parent subject, otherwise should be a valid subject ID
}
