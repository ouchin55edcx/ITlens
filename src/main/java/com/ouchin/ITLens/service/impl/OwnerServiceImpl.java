package com.ouchin.ITLens.service.impl;

import com.ouchin.ITLens.dto.owner.CreateOwnerDTO;
import com.ouchin.ITLens.dto.owner.OwnerDTO;
import com.ouchin.ITLens.dto.owner.UpdateOwnerDTO;
import com.ouchin.ITLens.entity.Owner;
import com.ouchin.ITLens.repository.OwnerRepository;
import com.ouchin.ITLens.service.OwnerService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    public OwnerServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public OwnerDTO createOwner(CreateOwnerDTO createOwnerDTO) {
        Owner owner = new Owner();
        owner.setName(createOwnerDTO.getName());
        Owner savedOwner = ownerRepository.save(owner);
        OwnerDTO ownerDTO = new OwnerDTO();
        BeanUtils.copyProperties(savedOwner, ownerDTO);
        return ownerDTO;
    }

    @Override
    public OwnerDTO getOwnerById(Long id) {
        Owner owner = ownerRepository.findById(id).orElseThrow(()-> new RuntimeException("Owner not found"));
        OwnerDTO ownerDTO = new OwnerDTO();
        BeanUtils.copyProperties(owner, ownerDTO);
        return ownerDTO;
    }

    @Override
    public List<OwnerDTO> getAllOwners() {
        return ownerRepository.findAll().stream()
                .map(owner -> {
                    OwnerDTO dto = new OwnerDTO();
                    BeanUtils.copyProperties(owner, dto);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public OwnerDTO updateOwner(Long id, UpdateOwnerDTO updateOwnerDTO) {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Owner not found "));
        owner.setName(updateOwnerDTO.getName());
        Owner updatedOwner = ownerRepository.save(owner);
        OwnerDTO ownerDTO = new OwnerDTO();
        BeanUtils.copyProperties(updatedOwner, ownerDTO);
        return ownerDTO;
    }

    @Override
    public void deleteOwner(Long id) {
        ownerRepository.deleteById(id);

    }
}
