package com.alves.vetflow.mapper;

import com.alves.vetflow.domain.Clinica;
import com.alves.vetflow.domain.Usuario;
import com.alves.vetflow.domain.VeterinarioClinica;
import com.alves.vetflow.dto.request.VeterinarioClinicaRequest;
import com.alves.vetflow.dto.response.VeterinarioClinicaResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class VeterinarioClinicaMapper {

    public VeterinarioClinica toEntity(VeterinarioClinicaRequest req, Clinica clinica, Usuario veterinario) {
        VeterinarioClinica veterinarioClinica = new VeterinarioClinica();
        veterinarioClinica.setCargaHoraria(req.cargaHoraria());
        veterinarioClinica.setDataInicio(req.dataInicio());
        veterinarioClinica.setClinica(clinica);
        veterinarioClinica.setVeterinario(veterinario);
        return veterinarioClinica;
    }

    public VeterinarioClinicaResponse toDTO(VeterinarioClinica vet) {
        return new VeterinarioClinicaResponse(
                vet.getId(), vet.getVeterinario().getUsername(), vet.getClinica().getNome(), vet.getDataInicio(), vet.getCargaHoraria()
        );
    }
}
