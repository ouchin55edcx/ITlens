package com.ouchin.ITLens.service;

import com.ouchin.ITLens.dto.survey.request.QuestionRequestDto;
import com.ouchin.ITLens.dto.survey.response.QuestionResponseDto;

import java.util.List;

public interface QuestionService {
    QuestionResponseDto createQuestion(QuestionRequestDto dto);
    QuestionResponseDto updateQuestion(Long id, QuestionRequestDto dto);
    void deleteQuestion(Long id);
    QuestionResponseDto addAnswer(Long questionId, String answerText);

    List<QuestionResponseDto> findQuestionsBySubjectId(Long subjectId);


}
