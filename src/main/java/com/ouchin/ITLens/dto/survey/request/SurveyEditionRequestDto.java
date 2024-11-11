package com.ouchin.ITLens.dto.survey.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SurveyEditionRequestDto {
    @NotNull
    private LocalDate startDate;

    @NotNull
    @Positive
    private Integer year;

    @NotNull
    @Positive
    private Long surveyId;
}