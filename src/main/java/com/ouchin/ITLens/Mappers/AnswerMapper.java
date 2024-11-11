package com.ouchin.ITLens.Mappers;

import com.ouchin.ITLens.dto.survey.request.AnswerRequestDto;
import com.ouchin.ITLens.dto.survey.response.AnswerResponseDto;
import com.ouchin.ITLens.entity.Answer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    Answer toEntity(AnswerRequestDto dto);
    AnswerResponseDto toResponseDto(Answer answer);
}
