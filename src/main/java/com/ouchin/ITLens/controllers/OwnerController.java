package com.ouchin.ITLens.controllers;


import com.ouchin.ITLens.dto.owner.CreateOwnerDTO;
import com.ouchin.ITLens.dto.owner.OwnerDTO;
import com.ouchin.ITLens.dto.owner.UpdateOwnerDTO;
import com.ouchin.ITLens.service.OwnerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {
    private final OwnerService ownerService;


    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @PostMapping
    public ResponseEntity<OwnerDTO> createOwner(@RequestBody CreateOwnerDTO createOwnerDTO){
        OwnerDTO ownerDTO = ownerService.createOwner(createOwnerDTO);
        return ResponseEntity.ok(ownerDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerDTO> getOwner(@PathVariable Long id) {
        return ResponseEntity.ok(ownerService.getOwnerById(id));
    }

    @GetMapping
    public ResponseEntity<List<OwnerDTO>> getAllOwners() {
        return ResponseEntity.ok(ownerService.getAllOwners());
    }


    @PutMapping("/{id}")
    public ResponseEntity<OwnerDTO> updateOwner(@PathVariable Long id, @RequestBody UpdateOwnerDTO updateOwnerDTO) {
        OwnerDTO ownerDTO = ownerService.updateOwner(id, updateOwnerDTO);
        return ResponseEntity.ok(ownerDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable Long id) {
        ownerService.deleteOwner(id);
        return ResponseEntity.noContent().build();
    }

}
