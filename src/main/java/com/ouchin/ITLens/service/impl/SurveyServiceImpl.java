package com.ouchin.ITLens.service.impl;

import com.ouchin.ITLens.Mappers.SurveyMapper;
import com.ouchin.ITLens.dto.survey.SubSubjectResultDto;
import com.ouchin.ITLens.dto.survey.SubjectResultDto;
import com.ouchin.ITLens.dto.survey.SurveyResultDto;
import com.ouchin.ITLens.dto.survey.request.ParticipationRequestDto;
import com.ouchin.ITLens.dto.survey.request.SurveyRequestDto;
import com.ouchin.ITLens.dto.survey.response.SurveyResponseDto;
import com.ouchin.ITLens.entity.*;
import com.ouchin.ITLens.repository.*;
import com.ouchin.ITLens.service.SurveyService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com. ouchin. ITLens. entity. Subject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SurveyServiceImpl implements SurveyService {
    private final SurveyRepository surveyRepository;
    private final OwnerRepository ownerRepository;
    private final SurveyMapper surveyMapper;
    private final AnswerRepository answerRepository;
    private final SurveyEditionRepository surveyEditionRepository;

    @Override
    @Transactional
    public List<SurveyResponseDto> findAll() {
        return surveyRepository.findAll()
                .stream()
                .map(surveyMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public SurveyResponseDto findById(Long id) {
        Survey survey = surveyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Survey not found with id: " + id));
        return surveyMapper.toDto(survey);
    }

    @Override
    @Transactional
    public SurveyResponseDto create(SurveyRequestDto dto) {
        Owner owner = ownerRepository.findById(dto.getOwnerId())
                .orElseThrow(() -> new EntityNotFoundException("Owner not found with id: " + dto.getOwnerId()));

        Survey survey = surveyMapper.toEntity(dto);
        survey.setOwner(owner);

        Survey savedSurvey = surveyRepository.save(survey);
        return surveyMapper.toDto(savedSurvey);
    }

    @Override
    @Transactional
    public SurveyResponseDto update(Long id, SurveyRequestDto dto) {
        Survey existingSurvey = surveyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Survey not found with id: " + id));

        Owner owner = ownerRepository.findById(dto.getOwnerId())
                .orElseThrow(() -> new EntityNotFoundException("Owner not found with id: " + dto.getOwnerId()));

        // Update the existing survey
        existingSurvey.setTitle(dto.getTitle());
        existingSurvey.setDescription(dto.getDescription());
        existingSurvey.setOwner(owner);

        Survey updatedSurvey = surveyRepository.save(existingSurvey);
        return surveyMapper.toDto(updatedSurvey);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!surveyRepository.existsById(id)) {
            throw new EntityNotFoundException("Survey not found with id: " + id);
        }
        surveyRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Page<SurveyResponseDto> getAllSurveys(Pageable pageable) {
        return surveyRepository.findAll(pageable)
                .map(surveyMapper::toDto);
    }

    @Transactional
    public void recordParticipation(Long surveyId, ParticipationRequestDto participationRequest) {
        for (ParticipationRequestDto.UserResponse userResponse : participationRequest.getResponses()) {
            Long questionId = userResponse.getQuestionId();
            List<String> answerIds = userResponse.getAnswerIds();

            // Increment selection count for each selected answer
            for (String answerId : answerIds) {
                Answer answer = answerRepository.findById(Long.parseLong(answerId))
                        .orElseThrow(() -> new EntityNotFoundException("Answer not found"));
                answer.incrementSelectionCount();
                answerRepository.save(answer);
            }

            // Calculate percentage for each answer in the question
            List<Answer> answersForQuestion = answerRepository.findByQuestionId(questionId);
            int totalSelections = answersForQuestion.stream().mapToInt(Answer::getSelectionCount).sum();
            answersForQuestion.forEach(answer -> {
                answer.updatePercentage(totalSelections);
                answerRepository.save(answer);
            });
        }
    }


    public SurveyResultDto getSurveyResults(Long surveyEditionId) {
        SurveyEdition surveyEdition = surveyEditionRepository.findById(surveyEditionId)
                .orElseThrow(() -> new EntityNotFoundException("Survey Edition not found"));

        SurveyResultDto resultDto = new SurveyResultDto();
        resultDto.setSurveyTitle(surveyEdition.getSurvey().getTitle());

        List<SubjectResultDto> subjectResultDtos = new ArrayList<>();
        for (Subject subject : surveyEdition.getSubjects()) {
            SubjectResultDto subjectDto = new SubjectResultDto();
            subjectDto.setTitle(subject.getTitle());

            List<SubSubjectResultDto> subSubjectResultDtos = new ArrayList<>();
            for (Subject subSubject : subject.getSubSubjects()) {
                SubSubjectResultDto subSubjectDto = new SubSubjectResultDto();
                subSubjectDto.setTitle(subSubject.getTitle());

                for (Question question : subSubject.getQuestions()) {
                    subSubjectDto.setQuestion(question.getText());

                    Map<String, Integer> answerCounts = new HashMap<>();
                    int totalAnswers = 0;

                    for (Answer answer : question.getAnswers()) {
                        answerCounts.put(answer.getText(), answer.getSelectionCount());
                        totalAnswers += answer.getSelectionCount();
                    }

                    subSubjectDto.setAnswers(answerCounts);
                    subSubjectDto.setTotalAnswers(totalAnswers);
                }

                subSubjectResultDtos.add(subSubjectDto);
            }
            subjectDto.setSubSubjects(subSubjectResultDtos);
            subjectResultDtos.add(subjectDto);
        }

        resultDto.setSubjects(subjectResultDtos);
        return resultDto;
    }
}