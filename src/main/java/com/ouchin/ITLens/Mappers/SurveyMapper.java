package com.ouchin.ITLens.Mappers;

import com.ouchin.ITLens.dto.survey.request.SurveyRequestDto;
import com.ouchin.ITLens.dto.survey.response.SurveyResponseDto;
import com.ouchin.ITLens.entity.Survey;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SurveyMapper {
    SurveyMapper INSTANCE = Mappers.getMapper(SurveyMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "owner", ignore = true)
    Survey toEntity(SurveyRequestDto dto);

    @Mapping(target = "owner.id", source = "owner.id")
    @Mapping(target = "owner.name", source = "owner.name")
    SurveyResponseDto toDto(Survey survey);
}