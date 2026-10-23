package com.alves.vetflow.dto.request;

public record PrescricaoMedicamentoRequest(
        Long prescricaoId,
        Long medicamentoId,
        String dosagem,
        Integer duracaoDias
) {
}
