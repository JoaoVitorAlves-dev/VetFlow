package com.alves.vetflow.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank @Size(min = 8) String username,
        @NotBlank @Size(min = 6) String password
) {
}
