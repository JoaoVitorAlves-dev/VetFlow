package com.alves.vetflow.dto.request;

import com.alves.vetflow.domain.StatusConsulta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ConsultaRequest(
        @NotNull Long animalId,
        @NotNull Long usuarioId,
        @NotNull Long clinicaId,
        @NotNull LocalDateTime dataHora,
        @NotNull StatusConsulta status,
        @NotBlank String observacao
) {
}
