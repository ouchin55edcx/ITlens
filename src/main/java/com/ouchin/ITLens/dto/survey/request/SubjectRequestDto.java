package com.ouchin.ITLens.dto.survey.request;

import com.ouchin.ITLens.common.annotation.existe.Exists;
import com.ouchin.ITLens.common.annotation.unique.Unique;
import com.ouchin.ITLens.entity.SurveyEdition;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.security.auth.Subject;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectRequestDto {

    @NotBlank(message = "Subject title is required")
    @Size(min = 2, max = 100, message = "Title must be between 2 and 100 characters")
    @Unique(entity = Subject.class, fieldName = "title", message = "Subject title must be unique")
    private String title;

    @NotNull(message = "Survey edition ID is required")
    @Exists(entity = SurveyEdition.class, fieldName = "id", message = "SurveyEdition ID must exist")
    private Long surveyEditionId;

    private Long parentSubjectId;
}
