package com.alves.vetflow.mapper;

import com.alves.vetflow.domain.Medicamento;
import com.alves.vetflow.dto.request.MedicamentoRequest;
import com.alves.vetflow.dto.response.MedicamentoResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MedicamentoMapper {

    public Medicamento toEntity(MedicamentoRequest req) {
        Medicamento medicamento = new Medicamento();
        medicamento.setNome(req.nome());
        return medicamento;
    }

    public MedicamentoResponse toDTO(Medicamento med) {
        return new MedicamentoResponse(
                med.getId(), med.getNome()
        );
    }
}
