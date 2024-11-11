package com.ouchin.ITLens.service;

import com.ouchin.ITLens.common.service.CrudService;
import com.ouchin.ITLens.dto.survey.SurveyResultDto;
import com.ouchin.ITLens.dto.survey.request.ParticipationRequestDto;
import com.ouchin.ITLens.dto.survey.request.SurveyRequestDto;
import com.ouchin.ITLens.dto.survey.response.SurveyResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SurveyService extends CrudService<Long, SurveyRequestDto, SurveyResponseDto> {
    Page<SurveyResponseDto> getAllSurveys(Pageable pageable);
    void recordParticipation(Long surveyId, ParticipationRequestDto participationRequest);
    SurveyResultDto getSurveyResults(Long surveyEditionId);
}
