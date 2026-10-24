package com.alves.vetflow.mapper;

import com.alves.vetflow.domain.Procedimento;
import com.alves.vetflow.dto.request.ProcedimentoRequest;
import com.alves.vetflow.dto.response.ProcedimentoResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProcedimentoMapper {

    public Procedimento toEntity(ProcedimentoRequest req) {
        Procedimento procedimento = new Procedimento();
        procedimento.setNome(req.nome());
        procedimento.setValorAtual(req.valorAtual());
        return procedimento;
    }

    public ProcedimentoResponse toDTO(Procedimento procedimento) {
        return new ProcedimentoResponse(
                procedimento.getId(), procedimento.getNome(), procedimento.getValorAtual()
        );
    }

}
