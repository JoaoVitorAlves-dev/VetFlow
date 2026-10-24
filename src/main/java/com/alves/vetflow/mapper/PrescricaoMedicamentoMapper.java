package com.alves.vetflow.mapper;

import com.alves.vetflow.domain.Medicamento;
import com.alves.vetflow.domain.Prescricao;
import com.alves.vetflow.domain.PrescricaoMedicamento;
import com.alves.vetflow.dto.request.PrescricaoMedicamentoRequest;
import com.alves.vetflow.dto.response.PrescricaoMedicamentoResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PrescricaoMedicamentoMapper {

    public PrescricaoMedicamento toEntity(PrescricaoMedicamentoRequest req, Prescricao prescricao, Medicamento medicamento) {
        PrescricaoMedicamento prescricaoMedicamento = new PrescricaoMedicamento();
        prescricaoMedicamento.setPrescricao(prescricao);
        prescricaoMedicamento.setDosagem(req.dosagem());
        prescricaoMedicamento.setDuracaoDias(req.duracaoDias());
        prescricaoMedicamento.setMedicamento(medicamento);
        return prescricaoMedicamento;
    }

    public PrescricaoMedicamentoResponse toDTO(PrescricaoMedicamento pr) {
        return new PrescricaoMedicamentoResponse(
                pr.getId(), pr.getPrescricao().getDataEmissao(), pr.getMedicamento().getNome(), pr.getDosagem(), pr.getDuracaoDias()
        );

    }
}
