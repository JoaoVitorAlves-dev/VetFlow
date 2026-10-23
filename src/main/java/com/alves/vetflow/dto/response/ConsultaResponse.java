package com.alves.vetflow.dto.response;

import com.alves.vetflow.domain.StatusConsulta;

import java.time.LocalDateTime;

public record ConsultaResponse(
        Long id,
        String nomeAnimal,
        String nomeVeterinario,
        String nomeClinica,
        LocalDateTime dataHora,
        StatusConsulta status,
        String observacao
) {
}
