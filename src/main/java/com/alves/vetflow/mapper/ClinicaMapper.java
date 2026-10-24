package com.alves.vetflow.mapper;

import com.alves.vetflow.domain.Clinica;
import com.alves.vetflow.dto.request.ClinicaRequest;
import com.alves.vetflow.dto.response.ClinicaResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ClinicaMapper {

    public Clinica toEntity(ClinicaRequest request) {
        Clinica clinica = new Clinica();
        clinica.setNome(request.nome());
        clinica.setCep(request.cep());
        clinica.setCidade(request.cidade());
        clinica.setEstado(request.estado());
        clinica.setRua(request.rua());
        return clinica;
    }

    public ClinicaResponse toDTO(Clinica clinica) {
        return new ClinicaResponse(
                clinica.getId(), clinica.getNome(), clinica.getRua(), clinica.getCidade(), clinica.getEstado(), clinica.getCep()
        );
    }

}
