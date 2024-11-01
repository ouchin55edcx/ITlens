package com.ouchin.ITLens.service;

import com.ouchin.ITLens.dto.owner.CreateOwnerDTO;
import com.ouchin.ITLens.dto.owner.OwnerDTO;
import com.ouchin.ITLens.dto.owner.UpdateOwnerDTO;

import java.util.List;

public interface OwnerService {
    OwnerDTO createOwner(CreateOwnerDTO createOwnerDTO);
    OwnerDTO getOwnerById(Long id);
    List<OwnerDTO> getAllOwners();
    OwnerDTO updateOwner(Long id, UpdateOwnerDTO updateOwnerDTO);
    void deleteOwner(Long id);
}
