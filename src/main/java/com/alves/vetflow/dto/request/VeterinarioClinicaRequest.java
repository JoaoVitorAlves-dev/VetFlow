package com.alves.vetflow.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.Duration;
import java.time.LocalDate;

public record VeterinarioClinicaRequest(
        @NotNull Long usuarioVeterinarioId,
        @NotNull Long clinicaId,
        @NotNull LocalDate dataInicio,
        @NotNull Duration cargaHoraria
) {
}
