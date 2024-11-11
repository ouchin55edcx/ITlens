package com.ouchin.ITLens.dto.survey.response;

import lombok.Data;

@Data
public class AnswerResponseDto {
    private Long id;
    private String text;
    private int selectionCount;
}
