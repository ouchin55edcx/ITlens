package com.ouchin.ITLens.service.impl;

import com.ouchin.ITLens.Mappers.SubjectMapper;
import com.ouchin.ITLens.common.exception.ResourceNotFoundException;
import com.ouchin.ITLens.dto.survey.request.ParticipationRequestDto;
import com.ouchin.ITLens.dto.survey.request.SubjectRequestDto;
import com.ouchin.ITLens.dto.survey.response.SubjectResponseDto;
import com.ouchin.ITLens.entity.Answer;
import com.ouchin.ITLens.entity.Subject;
import com.ouchin.ITLens.entity.SurveyEdition;
import com.ouchin.ITLens.repository.AnswerRepository;
import com.ouchin.ITLens.repository.QuestionRepository;
import com.ouchin.ITLens.repository.SubjectRepository;
import com.ouchin.ITLens.repository.SurveyEditionRepository;
import com.ouchin.ITLens.service.SubjectService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final SurveyEditionRepository surveyEditionRepository;
    private final SubjectMapper subjectMapper;


    @Autowired
    public SubjectServiceImpl(SubjectRepository subjectRepository,
                              SurveyEditionRepository surveyEditionRepository,
                              SubjectMapper subjectMapper, AnswerRepository answerRepository, QuestionRepository questionRepository) {
        this.subjectRepository = subjectRepository;
        this.surveyEditionRepository = surveyEditionRepository;
        this.subjectMapper = subjectMapper;
    }

    @Override
    public SubjectResponseDto create(SubjectRequestDto dto) {
        // Convert DTO to entity
        Subject subject = subjectMapper.toEntity(dto);

        // Fetch the SurveyEdition and link it to the Subject
        SurveyEdition surveyEdition = surveyEditionRepository.findById(dto.getSurveyEditionId())
                .orElseThrow(() -> new ResourceNotFoundException("Survey Edition not found with id: " + dto.getSurveyEditionId()));
        subject.setSurveyEdition(surveyEdition);

        // If there's a parent subject, fetch it and set the parent subject
        if (dto.getParentSubjectId() != null) {
            Subject parentSubject = subjectRepository.findById(dto.getParentSubjectId())
                    .orElseThrow(() -> new ResourceNotFoundException("Parent Subject not found with id: " + dto.getParentSubjectId()));
            subject.setParentSubject(parentSubject);
        }

        // Save the Subject and map it to DTO
        Subject savedSubject = subjectRepository.save(subject);
        return subjectMapper.toDto(savedSubject);
    }

    @Override
    public List<SubjectResponseDto> findAll() {
        return subjectRepository.findAll()
                .stream()
                .map(subjectMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public SubjectResponseDto findById(Long id) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with id: " + id));
        return subjectMapper.toDto(subject);
    }

    @Override
    public SubjectResponseDto createSubSubject(Long parentId, SubjectRequestDto dto) {
        // Fetch the parent subject and ensure it's found
        Subject parentSubject = subjectRepository.findById(parentId)
                .orElseThrow(() -> new ResourceNotFoundException("Parent Subject not found with id: " + parentId));

        // Convert DTO to entity and set the parent subject
        Subject subSubject = subjectMapper.toEntity(dto);
        subSubject.setParentSubject(parentSubject);

        // Fetch the SurveyEdition and set it
        SurveyEdition surveyEdition = surveyEditionRepository.findById(dto.getSurveyEditionId())
                .orElseThrow(() -> new ResourceNotFoundException("Survey Edition not found with id: " + dto.getSurveyEditionId()));
        subSubject.setSurveyEdition(surveyEdition);

        // Save and return the subsubject
        Subject savedSubSubject = subjectRepository.save(subSubject);
        return subjectMapper.toDto(savedSubSubject);
    }

    @Override
    public List<SubjectResponseDto> findSubjectsBySurveyEditionId(Long surveyEditionId) {
        List<Subject> subjects = subjectRepository.findBySurveyEditionId(surveyEditionId);
        return subjects.stream()
                .map(subjectMapper::toDto)
                .collect(Collectors.toList());
    }



}
