package com.alves.vetflow.dto.request;

import jakarta.validation.constraints.NotBlank;

public record MedicamentoRequest(
        @NotBlank String nome
) {
}
