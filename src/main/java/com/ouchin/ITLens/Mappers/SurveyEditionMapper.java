package com.ouchin.ITLens.Mappers;

import com.ouchin.ITLens.dto.survey.request.SurveyEditionRequestDto;
import com.ouchin.ITLens.dto.survey.response.SurveyEditionResponseDto;
import com.ouchin.ITLens.entity.SurveyEdition;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SurveyEditionMapper {
    SurveyEditionMapper INSTANCE = Mappers.getMapper(SurveyEditionMapper.class);

    @Mapping(source = "survey.id", target = "surveyId")
    @Mapping(source = "survey.title", target = "surveyTitle")
    SurveyEditionResponseDto toDto(SurveyEdition surveyEdition);

    @Mapping(source = "surveyId", target = "survey.id")
    SurveyEdition toEntity(SurveyEditionRequestDto dto);
}