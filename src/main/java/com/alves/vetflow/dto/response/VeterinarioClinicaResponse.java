package com.alves.vetflow.dto.response;

import java.time.Duration;
import java.time.LocalDate;

public record VeterinarioClinicaResponse(
        Long id,
        String nomeVeterinario,
        String nomeClinica,
        LocalDate dataInicio,
        Duration cargaHoraria
) {
}
