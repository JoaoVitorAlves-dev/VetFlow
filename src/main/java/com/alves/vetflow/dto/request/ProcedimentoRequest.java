package com.alves.vetflow.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProcedimentoRequest(
        @NotBlank String nome,
        @NotNull @Positive BigDecimal valorAtual
) {
}
