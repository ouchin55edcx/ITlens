package com.ouchin.ITLens.service.impl;

import com.ouchin.ITLens.Mappers.QuestionMapper;
import com.ouchin.ITLens.dto.survey.request.QuestionRequestDto;
import com.ouchin.ITLens.dto.survey.response.QuestionResponseDto;
import com.ouchin.ITLens.entity.Answer;
import com.ouchin.ITLens.entity.Question;
import com.ouchin.ITLens.repository.AnswerRepository;
import com.ouchin.ITLens.repository.QuestionRepository;
import com.ouchin.ITLens.service.QuestionService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final QuestionMapper questionMapper;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository,
                               AnswerRepository answerRepository,
                               QuestionMapper questionMapper) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.questionMapper = questionMapper;
    }

    @Override
    @Transactional
    public QuestionResponseDto createQuestion(QuestionRequestDto dto) {
        // Convert DTO to Question entity
        Question question = questionMapper.toEntity(dto);

        // Save the Question entity and return the response DTO
        question = questionRepository.save(question);
        return questionMapper.toResponseDto(question);
    }

    @Override
    @Transactional
    public QuestionResponseDto updateQuestion(Long id, QuestionRequestDto dto) {
        // Find the Question by ID
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Question not found"));

        // Update fields in the question
        question.setText(dto.getText());
        question.setType(dto.getType());

        // Save the updated Question and return the response DTO
        return questionMapper.toResponseDto(questionRepository.save(question));
    }

    @Override
    public void deleteQuestion(Long id) {
        // Delete the Question by ID
        questionRepository.deleteById(id);
    }

    @Override
    @Transactional
    public QuestionResponseDto addAnswer(Long questionId, String answerText) {
        // Find the Question by ID
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new EntityNotFoundException("Question not found"));

        // Create and link the new Answer to the Question
        Answer answer = new Answer();
        answer.setText(answerText);
        answer.setQuestion(question);
        answerRepository.save(answer);

        // Return the updated Question DTO with the added answer
        return questionMapper.toResponseDto(question);
    }

    @Override
    public List<QuestionResponseDto> findQuestionsBySubjectId(Long subjectId) {
        // Fetch questions by subject ID from the repository
        List<Question> questions = questionRepository.findBySubjectId(subjectId);
        return questions.stream()
                .map(questionMapper::toResponseDto)
                .collect(Collectors.toList());
    }


}
