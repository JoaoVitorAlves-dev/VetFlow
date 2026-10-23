package com.alves.vetflow.dto.response;

public record ClinicaResponse(
        Long id,
        String nome,
        String rua,
        String cidade,
        String estado,
        String cep
) {
}
