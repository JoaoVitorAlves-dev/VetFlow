package com.alves.vetflow.dto.response;

import java.math.BigDecimal;

public record ProcedimentoResponse(
        Long id,
        String nome,
        BigDecimal valorAtual
) {
}
