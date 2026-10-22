package com.alves.vetflow.dto.request;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ConsultaProcedimentoRequest(
        @NotNull Long consultaId,
        @NotNull Long procedimentoId,
        @NotNull Integer quantidade,
        @NotNull BigDecimal valorCobrado
) {
}
