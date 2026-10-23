package com.alves.vetflow.dto.response;

import java.math.BigDecimal;

public record ConsultaProcedimentoResponse(
        Long id,
        String procedimento,
        Integer quantidade,
        BigDecimal valorCobrado
) {
}
