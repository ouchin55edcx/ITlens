package com.ouchin.ITLens.dto.survey.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SurveyEditionResponseDto {
    @NotNull
    @Positive
    private Long id;

    @NotNull
    private LocalDate creationDate;

    @NotNull
    private LocalDate startDate;

    @NotNull
    @Positive
    private Integer year;

    @NotNull
    @Positive
    private Long surveyId;

    @NotBlank
    private String surveyTitle;
}

// TO DO : list of subjects

//Annotation : unique for : duplicate name , titile
// existe :verify existe id ,