package com.ouchin.ITLens.controllers;

import com.ouchin.ITLens.dto.survey.request.AnswerRequestDto;
import com.ouchin.ITLens.dto.survey.request.QuestionRequestDto;
import com.ouchin.ITLens.dto.survey.response.QuestionResponseDto;
import com.ouchin.ITLens.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/questions")
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping
    public ResponseEntity<QuestionResponseDto> createQuestion(@RequestBody QuestionRequestDto dto) {
        return new ResponseEntity<>(questionService.createQuestion(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuestionResponseDto> updateQuestion(@PathVariable Long id, @RequestBody QuestionRequestDto dto) {
        return ResponseEntity.ok(questionService.updateQuestion(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/answers")
    public ResponseEntity<QuestionResponseDto> addAnswer(@PathVariable Long id, @RequestBody AnswerRequestDto answerText) {
        return ResponseEntity.ok(questionService.addAnswer(id, answerText.getText()));
    }

    @GetMapping("/{id}/questions")
    public ResponseEntity<List<QuestionResponseDto>> getQuestionsBySubjectId(@PathVariable Long id) {
        List<QuestionResponseDto> questions = questionService.findQuestionsBySubjectId(id);
        return ResponseEntity.ok(questions);
    }

}
