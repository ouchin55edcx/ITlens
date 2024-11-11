package com.ouchin.ITLens.dto.survey.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SurveyRequestDto {
    @NotBlank(message = "Survey title is required")
    private String title;

    @NotBlank(message = "Survey description is required")
    private String description;

    @NotNull(message = "Owner ID is required")
    private Long ownerId;
}
