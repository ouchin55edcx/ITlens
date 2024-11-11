package com.ouchin.ITLens.service.impl;

import com.ouchin.ITLens.Mappers.OwnerMapper;
import com.ouchin.ITLens.dto.owner.OwnerRequestDto;
import com.ouchin.ITLens.dto.owner.OwnerResponseDto;
import com.ouchin.ITLens.entity.Owner;
import com.ouchin.ITLens.repository.OwnerRepository;
import com.ouchin.ITLens.service.OwnerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final OwnerMapper ownerMapper;

    public OwnerServiceImpl(OwnerRepository ownerRepository, OwnerMapper ownerMapper) {
        this.ownerRepository = ownerRepository;
        this.ownerMapper = ownerMapper;
    }

    @Override
    public List<OwnerResponseDto> findAll() {
        return ownerRepository.findAll().stream()
                .map(ownerMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public OwnerResponseDto findById(Long id) {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Owner not found"));
        return ownerMapper.toDto(owner);
    }

    @Override
    public OwnerResponseDto create(OwnerRequestDto ownerRequestDto) {
        Owner owner = ownerMapper.toEntity(ownerRequestDto);
        Owner savedOwner = ownerRepository.save(owner);
        return ownerMapper.toDto(savedOwner);
    }

    @Override
    public OwnerResponseDto update(Long id, OwnerRequestDto ownerRequestDto) {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Owner not found"));
        ownerMapper.updateOwnerFromDto(ownerRequestDto, owner);
        Owner updatedOwner = ownerRepository.save(owner);
        return ownerMapper.toDto(updatedOwner);
    }

    @Override
    public void delete(Long id) {
        ownerRepository.deleteById(id);
    }
}
