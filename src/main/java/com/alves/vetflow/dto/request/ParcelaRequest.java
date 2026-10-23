package com.alves.vetflow.dto.request;

import com.alves.vetflow.domain.StatusParcela;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ParcelaRequest(
        Long consultaId,
        BigDecimal valor,
        LocalDateTime vencimento,
        StatusParcela status
) {
}
