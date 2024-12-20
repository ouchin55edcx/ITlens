package com.ouchin.ITLens.Mappers;

import com.ouchin.ITLens.dto.survey.request.QuestionRequestDto;
import com.ouchin.ITLens.dto.survey.response.QuestionResponseDto;
import com.ouchin.ITLens.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AnswerMapper.class})
public interface QuestionMapper {

    @Mapping(source = "subjectId", target = "subject.id")
    Question toEntity(QuestionRequestDto dto);

    @Mapping(source = "subject.id", target = "subjectId")
    QuestionResponseDto toResponseDto(Question question);
}

