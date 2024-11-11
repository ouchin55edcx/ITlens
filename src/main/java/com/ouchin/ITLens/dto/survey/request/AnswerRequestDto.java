package com.ouchin.ITLens.dto.survey.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AnswerRequestDto {
    @NotBlank(message = "Answer text cannot be empty")
    @Size(min = 1, max = 1000, message = "Answer text must be between 1 and 1000 characters")
    private String text;
}