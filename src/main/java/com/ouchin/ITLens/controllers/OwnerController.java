package com.ouchin.ITLens.controllers;

import com.ouchin.ITLens.dto.owner.OwnerRequestDto;
import com.ouchin.ITLens.dto.owner.OwnerResponseDto;
import com.ouchin.ITLens.service.OwnerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/owners")
@Tag(name = "Owner", description = "Owner management APIs")
public class OwnerController {
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @Operation(summary = "Create a new owner", description = "Creates a new owner with the provided information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Owner created successfully",
                    content = @Content(schema = @Schema(implementation = OwnerResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<OwnerResponseDto> createOwner(
            @Parameter(description = "Owner details", required = true)
            @RequestBody OwnerRequestDto ownerRequestDto) {
        OwnerResponseDto ownerResponseDto = ownerService.create(ownerRequestDto);
        return ResponseEntity.ok(ownerResponseDto);
    }

    @Operation(summary = "Get an owner by ID", description = "Returns an owner based on ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Owner found",
                    content = @Content(schema = @Schema(implementation = OwnerResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Owner not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<OwnerResponseDto> getOwner(
            @Parameter(description = "Owner ID", required = true)
            @PathVariable Long id) {
        return ResponseEntity.ok(ownerService.findById(id));
    }

    @Operation(summary = "Get all owners", description = "Returns a list of all owners")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of owners retrieved successfully",
                    content = @Content(schema = @Schema(implementation = OwnerResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<OwnerResponseDto>> getAllOwners() {
        return ResponseEntity.ok(ownerService.findAll());
    }

    @Operation(summary = "Update an owner", description = "Updates an owner's information based on ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Owner updated successfully",
                    content = @Content(schema = @Schema(implementation = OwnerResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Owner not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<OwnerResponseDto> updateOwner(
            @Parameter(description = "Owner ID", required = true)
            @PathVariable Long id,
            @Parameter(description = "Updated owner details", required = true)
            @RequestBody OwnerRequestDto ownerRequestDto) {
        OwnerResponseDto ownerResponseDto = ownerService.update(id, ownerRequestDto);
        return ResponseEntity.ok(ownerResponseDto);
    }

    @Operation(summary = "Delete an owner", description = "Deletes an owner based on ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Owner deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Owner not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwner(
            @Parameter(description = "Owner ID", required = true)
            @PathVariable Long id) {
        ownerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}