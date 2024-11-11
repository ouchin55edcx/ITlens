package com.ouchin.ITLens.Mappers;

import com.ouchin.ITLens.dto.survey.request.SubjectRequestDto;
import com.ouchin.ITLens.dto.survey.response.SubjectResponseDto;
import com.ouchin.ITLens.entity.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    @Mapping(source = "surveyEdition.id", target = "surveyEditionId")
    @Mapping(source = "parentSubject.id", target = "parentSubjectId")
    SubjectResponseDto toDto(Subject entity);

    @Mapping(source = "surveyEditionId", target = "surveyEdition.id")
    @Mapping(source = "parentSubjectId", target = "parentSubject.id")
    Subject toEntity(SubjectRequestDto dto);

    List<SubjectResponseDto> toDtoList(List<Subject> entities);

    @Mapping(source = "surveyEditionId", target = "surveyEdition.id")
    @Mapping(source = "parentSubjectId", target = "parentSubject.id")
    void updateSubjectFromDto(SubjectRequestDto dto, @MappingTarget Subject entity);
}



