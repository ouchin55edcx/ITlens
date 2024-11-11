package com.ouchin.ITLens.Mappers;

import com.ouchin.ITLens.dto.owner.OwnerRequestDto;
import com.ouchin.ITLens.dto.owner.OwnerResponseDto;
import com.ouchin.ITLens.entity.Owner;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OwnerMapper {

    Owner toEntity(OwnerRequestDto ownerRequestDto);

    OwnerResponseDto toDto(Owner owner);

    void updateOwnerFromDto(OwnerRequestDto ownerRequestDto, @MappingTarget Owner owner);
}
