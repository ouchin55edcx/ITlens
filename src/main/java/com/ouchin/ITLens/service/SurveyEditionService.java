package com.ouchin.ITLens.service;

import com.ouchin.ITLens.common.service.CrudService;
import com.ouchin.ITLens.dto.survey.request.SurveyEditionRequestDto;
import com.ouchin.ITLens.dto.survey.response.SurveyEditionResponseDto;

import java.util.List;

public interface SurveyEditionService {
    List<SurveyEditionResponseDto> findAll();
    SurveyEditionResponseDto findById(Long id);
    SurveyEditionResponseDto create(SurveyEditionRequestDto dto);
    SurveyEditionResponseDto update(Long id, SurveyEditionRequestDto dto);
    void delete(Long id);
}