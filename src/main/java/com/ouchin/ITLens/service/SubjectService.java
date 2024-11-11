package com.ouchin.ITLens.service;

import com.ouchin.ITLens.dto.survey.request.SubjectRequestDto;
import com.ouchin.ITLens.dto.survey.response.SubjectResponseDto;

import java.util.List;

public interface SubjectService {

    SubjectResponseDto create(SubjectRequestDto dto);

    List<SubjectResponseDto> findAll();

    SubjectResponseDto findById(Long id);

    SubjectResponseDto createSubSubject(Long parentId, SubjectRequestDto dto);

    List<SubjectResponseDto> findSubjectsBySurveyEditionId(Long surveyEditionId);
}

