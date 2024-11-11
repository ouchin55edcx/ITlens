package com.ouchin.ITLens.controllers;

import com.ouchin.ITLens.dto.survey.request.QuestionRequestDto;
import com.ouchin.ITLens.dto.survey.request.SubjectRequestDto;
import com.ouchin.ITLens.dto.survey.response.QuestionResponseDto;
import com.ouchin.ITLens.dto.survey.response.SubjectResponseDto;
import com.ouchin.ITLens.service.QuestionService;
import com.ouchin.ITLens.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subjects")
public class SubjectController {

    private final SubjectService subjectService;
    private final QuestionService questionService; // Injecting QuestionService

    @Autowired
    public SubjectController(SubjectService subjectService, QuestionService questionService) {
        this.subjectService = subjectService;
        this.questionService = questionService;
    }

    @GetMapping
    public ResponseEntity<List<SubjectResponseDto>> getAllSubjects() {
        return ResponseEntity.ok(subjectService.findAll());
    }

    @PostMapping
    public ResponseEntity<SubjectResponseDto> createSubject(@RequestBody SubjectRequestDto dto) {
        return ResponseEntity.ok(subjectService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectResponseDto> getSubjectById(@PathVariable Long id) {
        return ResponseEntity.ok(subjectService.findById(id));
    }

    @PostMapping("/{id}/sub-subjects")
    public ResponseEntity<SubjectResponseDto> createSubSubject(@PathVariable Long id, @RequestBody SubjectRequestDto dto) {
        return ResponseEntity.ok(subjectService.createSubSubject(id, dto));
    }

    @GetMapping("/{id}/questions")
    public ResponseEntity<List<QuestionResponseDto>> getQuestionsBySubjectId(@PathVariable Long id) {
        // You would need to implement the corresponding method in QuestionService to retrieve questions by subject ID
        return ResponseEntity.ok(questionService.findQuestionsBySubjectId(id));
    }

}
