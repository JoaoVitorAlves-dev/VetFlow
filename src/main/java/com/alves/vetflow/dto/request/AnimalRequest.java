package com.alves.vetflow.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record AnimalRequest(
        @NotNull Long usuarioId,
        @NotBlank String nome,
        @NotBlank String especie,
        @NotBlank String raca,
        @NotNull LocalDateTime dataNascimento,
        @NotNull @Positive Long peso
) {
}
