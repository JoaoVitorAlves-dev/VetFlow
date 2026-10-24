package com.alves.vetflow.mapper;

import com.alves.vetflow.domain.*;
import com.alves.vetflow.dto.request.ConsultaRequest;
import com.alves.vetflow.dto.response.ConsultaResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ConsultaMapper {

    public Consulta toEntity(ConsultaRequest req, Animal animal, Clinica clinica, Usuario veterinario) {
        Consulta consulta = new Consulta();
        consulta.setAnimal(animal);
        consulta.setClinica(clinica);
        consulta.setDataHora(req.dataHora());
        consulta.setObservacoes(req.observacao());
        consulta.setStatus(req.status());
        if (veterinario.getRole() == Role.VETERINARIO) {
            consulta.setVeterinario(veterinario);
        }
        return consulta;
    }

    public ConsultaResponse toDTO(Consulta consulta) {
        return new ConsultaResponse(
                consulta.getId(), consulta.getAnimal().getNome(), consulta.getVeterinario().getUsername(), consulta.getClinica().getNome(),
                consulta.getDataHora(), consulta.getStatus(), consulta.getObservacoes()
        );
    }
}
