package com.alves.vetflow.mapper;

import com.alves.vetflow.domain.Consulta;
import com.alves.vetflow.domain.Parcela;
import com.alves.vetflow.dto.request.ParcelaRequest;
import com.alves.vetflow.dto.response.ParcelaResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ParcelaMapper {

    public Parcela toEntity(ParcelaRequest req, Consulta consulta) {
        Parcela parcela = new Parcela();
        parcela.setStatus(req.status());
        parcela.setValor(req.valor());
        parcela.setVencimento(req.vencimento());
        parcela.setConsulta(consulta);
        return parcela;
    }

    public ParcelaResponse toDTO(Parcela parcela) {
        return new ParcelaResponse(
          parcela.getId(), parcela.getConsulta().getDataHora(), parcela.getValor(), parcela.getVencimento(), parcela.getStatus()
        );
    }

}
