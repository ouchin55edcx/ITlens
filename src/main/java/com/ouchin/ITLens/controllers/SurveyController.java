package com.ouchin.ITLens.controllers;

import com.ouchin.ITLens.dto.survey.SurveyResultDto;
import com.ouchin.ITLens.dto.survey.request.ParticipationRequestDto;
import com.ouchin.ITLens.dto.survey.request.SurveyRequestDto;
import com.ouchin.ITLens.dto.survey.response.SurveyResponseDto;
import com.ouchin.ITLens.service.SurveyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/surveys")
@RequiredArgsConstructor
public class SurveyController {
    private final SurveyService surveyService;

    @PostMapping
    public ResponseEntity<SurveyResponseDto> createSurvey(@Valid @RequestBody SurveyRequestDto requestDto) {
        return new ResponseEntity<>(surveyService.create(requestDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SurveyResponseDto> getSurveyById(@PathVariable Long id) {
        return ResponseEntity.ok(surveyService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<SurveyResponseDto>> getAllSurveys(Pageable pageable) {
        return ResponseEntity.ok(surveyService.getAllSurveys(pageable));
    }

    @PostMapping("/{surveyId}/participate")
    public ResponseEntity<String> participateInSurvey(
            @PathVariable Long surveyId,
            @RequestBody ParticipationRequestDto requestDto) {
        surveyService.recordParticipation(surveyId, requestDto);
        return ResponseEntity.ok("Participation recorded successfully.");
    }

    @GetMapping("/{surveyEditionId}/results")
    public ResponseEntity<SurveyResultDto> getSurveyResults(@PathVariable Long surveyEditionId) {
        SurveyResultDto surveyResultDto = surveyService.getSurveyResults(surveyEditionId);
        return ResponseEntity.ok(surveyResultDto);
    }

}
