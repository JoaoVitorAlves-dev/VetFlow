package com.alves.vetflow.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PrescricaoMedicamentoRequest(
        @NotNull Long prescricaoId,
        @NotNull Long medicamentoId,
        @NotBlank String dosagem,
        @NotNull Integer duracaoDias
) {
}
