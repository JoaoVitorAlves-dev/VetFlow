package com.alves.vetflow.dto.response;

import com.alves.vetflow.domain.StatusParcela;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ParcelaResponse(
        Long id,
        LocalDateTime consulta,
        BigDecimal valor,
        LocalDateTime vencimento,
        StatusParcela statusParcela
) {
}
