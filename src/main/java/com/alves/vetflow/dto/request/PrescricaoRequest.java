package com.alves.vetflow.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record PrescricaoRequest(
        @NotNull Long consultaId,
        @NotNull LocalDateTime dataEmissao
) {
}
