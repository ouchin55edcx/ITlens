package com.ouchin.ITLens.dto.owner;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateOwnerDTO {
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 20)
    private String name;
}
