package com.ouchin.ITLens.controllers;


import com.ouchin.ITLens.dto.survey.request.SurveyEditionRequestDto;
import com.ouchin.ITLens.dto.survey.response.SurveyEditionResponseDto;
import com.ouchin.ITLens.service.SurveyEditionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/surveys/{surveyId}/editions")
@RequiredArgsConstructor
public class SurveyEditionController {
    private final SurveyEditionService surveyEditionService;

    @GetMapping
    public ResponseEntity<List<SurveyEditionResponseDto>> getAllEditions() {
        return ResponseEntity.ok(surveyEditionService.findAll());
    }

    @GetMapping("/{editionId}")
    public ResponseEntity<SurveyEditionResponseDto> getEditionById(@PathVariable Long editionId) {
        return ResponseEntity.ok(surveyEditionService.findById(editionId));
    }

    @PostMapping
    public ResponseEntity<SurveyEditionResponseDto> createEdition(
            @PathVariable Long surveyId,
            @Valid @RequestBody SurveyEditionRequestDto requestDto) {
        requestDto.setSurveyId(surveyId);  // Set surveyId from path
        return new ResponseEntity<>(surveyEditionService.create(requestDto), HttpStatus.CREATED);
    }

    @PutMapping("/{editionId}")
    public ResponseEntity<SurveyEditionResponseDto> updateEdition(
            @PathVariable Long editionId,
            @Valid @RequestBody SurveyEditionRequestDto requestDto) {
        return ResponseEntity.ok(surveyEditionService.update(editionId, requestDto));
    }

    @DeleteMapping("/{editionId}")
    public ResponseEntity<Void> deleteEdition(@PathVariable Long editionId) {
        surveyEditionService.delete(editionId);
        return ResponseEntity.noContent().build();
    }
}
