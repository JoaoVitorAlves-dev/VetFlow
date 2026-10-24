package com.alves.vetflow.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.Duration;
import java.time.LocalDateTime;

public record VeterinarioClinicaRequest(
        @NotNull Long usuarioVeterinarioId,
        @NotNull Long clinicaId,
        @NotNull LocalDateTime dataInicio,
        @NotNull Duration cargaHoraria
) {
}
