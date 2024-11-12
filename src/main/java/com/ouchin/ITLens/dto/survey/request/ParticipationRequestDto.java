package com.ouchin.ITLens.dto.survey.request;

import lombok.Data;

import java.util.List;

@Data
public class ParticipationRequestDto {
    private List<UserResponse> responses;

    public List<UserResponse> getResponses() {
        return responses;
    }

    public void setResponses(List<UserResponse> responses) {
        this.responses = responses;
    }

    public static class UserResponse {
        private Long questionId;
        private List<String> answerIds;


        public Long getQuestionId() {
            return questionId;
        }

        public void setQuestionId(Long questionId) {
            this.questionId = questionId;
        }

        public List<String> getAnswerIds() {
            return answerIds;
        }

        public void setAnswerIds(List<String> answerIds) {
            this.answerIds = answerIds;
        }
    }
}

