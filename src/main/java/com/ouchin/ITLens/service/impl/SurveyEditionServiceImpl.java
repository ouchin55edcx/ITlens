package com.ouchin.ITLens.service.impl;

import com.ouchin.ITLens.Mappers.SurveyEditionMapper;
import com.ouchin.ITLens.dto.survey.request.SurveyEditionRequestDto;
import com.ouchin.ITLens.dto.survey.response.SurveyEditionResponseDto;
import com.ouchin.ITLens.entity.Survey;
import com.ouchin.ITLens.entity.SurveyEdition;
import com.ouchin.ITLens.repository.SurveyEditionRepository;
import com.ouchin.ITLens.repository.SurveyRepository;
import com.ouchin.ITLens.service.SurveyEditionService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SurveyEditionServiceImpl implements SurveyEditionService {
    private final SurveyEditionRepository surveyEditionRepository;
    private final SurveyRepository surveyRepository;
    private final SurveyEditionMapper surveyEditionMapper;

    @Override
    @Transactional
    public List<SurveyEditionResponseDto> findAll() {
        return surveyEditionRepository.findAll()
                .stream()
                .map(surveyEditionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public SurveyEditionResponseDto findById(Long id) {
        SurveyEdition surveyEdition = surveyEditionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SurveyEdition not found with id: " + id));
        return surveyEditionMapper.toDto(surveyEdition);
    }

    @Override
    @Transactional
    public SurveyEditionResponseDto create(SurveyEditionRequestDto dto) {
        Survey survey = surveyRepository.findById(dto.getSurveyId())
                .orElseThrow(() -> new EntityNotFoundException("Survey not found with id: " + dto.getSurveyId()));

        SurveyEdition surveyEdition = surveyEditionMapper.toEntity(dto);
        surveyEdition.setSurvey(survey);
        surveyEdition.setCreationDate(LocalDate.now());

        SurveyEdition savedSurveyEdition = surveyEditionRepository.save(surveyEdition);
        return surveyEditionMapper.toDto(savedSurveyEdition);
    }

    @Override
    @Transactional
    public SurveyEditionResponseDto update(Long id, SurveyEditionRequestDto dto) {
        SurveyEdition existingSurveyEdition = surveyEditionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SurveyEdition not found with id: " + id));

        Survey survey = surveyRepository.findById(dto.getSurveyId())
                .orElseThrow(() -> new EntityNotFoundException("Survey not found with id: " + dto.getSurveyId()));

        existingSurveyEdition.setStartDate(dto.getStartDate());
        existingSurveyEdition.setYear(dto.getYear());
        existingSurveyEdition.setSurvey(survey);

        SurveyEdition updatedSurveyEdition = surveyEditionRepository.save(existingSurveyEdition);
        return surveyEditionMapper.toDto(updatedSurveyEdition);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!surveyEditionRepository.existsById(id)) {
            throw new EntityNotFoundException("SurveyEdition not found with id: " + id);
        }
        surveyEditionRepository.deleteById(id);
    }
}