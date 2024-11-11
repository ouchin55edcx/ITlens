package com.ouchin.ITLens.controllers;


import com.ouchin.ITLens.dto.owner.OwnerRequestDto;
import com.ouchin.ITLens.dto.owner.OwnerResponseDto;
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
    public ResponseEntity<OwnerResponseDto> createOwner(@RequestBody OwnerRequestDto ownerRequestDto) {
        OwnerResponseDto ownerResponseDto = ownerService.create(ownerRequestDto);
        return ResponseEntity.ok(ownerResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerResponseDto> getOwner(@PathVariable Long id) {
        return ResponseEntity.ok(ownerService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<OwnerResponseDto>> getAllOwners() {
        return ResponseEntity.ok(ownerService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<OwnerResponseDto> updateOwner(@PathVariable Long id, @RequestBody OwnerRequestDto ownerRequestDto) {
        OwnerResponseDto ownerResponseDto = ownerService.update(id, ownerRequestDto);
        return ResponseEntity.ok(ownerResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable Long id) {
        ownerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
