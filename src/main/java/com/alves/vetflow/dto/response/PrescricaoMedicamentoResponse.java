package com.alves.vetflow.dto.response;

import java.time.LocalDateTime;

public record PrescricaoMedicamentoResponse(
        Long id,
        LocalDateTime dataPrescricao,
        String nomeMedicamento,
        String dosagem,
        Integer duracaoDias
) {
}
