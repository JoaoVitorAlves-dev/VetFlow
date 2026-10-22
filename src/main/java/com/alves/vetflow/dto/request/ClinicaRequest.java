package com.alves.vetflow.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ClinicaRequest(
        @NotBlank String nome,
        @NotBlank String rua,
        @NotBlank String cidade,
        @NotBlank String estado,
        @NotBlank String cep
) {
}
