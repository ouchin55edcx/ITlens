package com.ouchin.ITLens.dto.survey.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SurveyResponseDto {
    private Long id;
    private String title;
    private String description;
    private OwnerDto owner;

    @Data
    @Builder
    public static class OwnerDto {
        private Long id;
        private String name;
    }
}

// To Do : add list of survey editions