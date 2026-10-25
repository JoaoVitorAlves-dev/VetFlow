package com.alves.vetflow.dto.request;

import com.alves.vetflow.domain.StatusParcela;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ParcelaRequest(
        @NotNull Long consultaId,
        @NotNull BigDecimal valor,
        @NotNull LocalDateTime vencimento,
        StatusParcela status
) {
}
