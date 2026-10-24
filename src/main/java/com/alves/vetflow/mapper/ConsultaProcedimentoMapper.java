package com.alves.vetflow.mapper;

import com.alves.vetflow.domain.Consulta;
import com.alves.vetflow.domain.ConsultaProcedimento;
import com.alves.vetflow.domain.Procedimento;
import com.alves.vetflow.dto.request.ConsultaProcedimentoRequest;
import com.alves.vetflow.dto.response.ConsultaProcedimentoResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ConsultaProcedimentoMapper {

    public ConsultaProcedimento toEntity(ConsultaProcedimentoRequest req, Consulta consulta, Procedimento procedimento) {
        ConsultaProcedimento consultaProcedimento = new ConsultaProcedimento();
        consultaProcedimento.setConsulta(consulta);
        consultaProcedimento.setProcedimento(procedimento);
        consultaProcedimento.setQuantidade(req.quantidade());
        consultaProcedimento.setValorCobrado(req.valorCobrado());
        return consultaProcedimento;
    }

    public ConsultaProcedimentoResponse toDTO(ConsultaProcedimento consulta) {
        return new ConsultaProcedimentoResponse(
                consulta.getId(), consulta.getProcedimento().getNome(), consulta.getQuantidade(), consulta.getValorCobrado()
        );
    }

}
