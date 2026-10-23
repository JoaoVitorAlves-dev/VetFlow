package com.alves.vetflow.dto.response;

import java.time.Duration;
import java.time.LocalDateTime;

public record VeterinarioClinicaResponse(
        Long id,
        String nomeVeterinario,
        String nomeClinica,
        LocalDateTime dataInicio,
        Duration cargaHoraria
) {
}
