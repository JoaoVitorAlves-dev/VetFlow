package com.alves.vetflow.dto.request;

import java.time.LocalDateTime;

public record PrescricaoRequest(
        Long consultaId,
        LocalDateTime dataEmissao
) {
}
