package com.alves.vetflow.dto.response;

import java.time.LocalDateTime;

public record PrescricaoResponse(
        Long id,
        LocalDateTime dataConsulta,
        LocalDateTime dataEmissao
) {
}
