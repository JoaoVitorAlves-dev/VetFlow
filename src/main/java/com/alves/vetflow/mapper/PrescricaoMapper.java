package com.alves.vetflow.mapper;

import com.alves.vetflow.domain.Consulta;
import com.alves.vetflow.domain.Prescricao;
import com.alves.vetflow.dto.request.PrescricaoRequest;
import com.alves.vetflow.dto.response.PrescricaoResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PrescricaoMapper {

    public Prescricao toEntity(PrescricaoRequest req, Consulta consulta) {
        Prescricao prescricao = new Prescricao();
        prescricao.setDataEmissao(req.dataEmissao());
        prescricao.setConsulta(consulta);
        return prescricao;
    }

    public PrescricaoResponse toDTO(Prescricao prescricao) {
        return new PrescricaoResponse(
                prescricao.getId(), prescricao.getConsulta().getDataHora(), prescricao.getDataEmissao()
        );
    }

}
