package com.alves.vetflow.dto.response;

import java.time.LocalDateTime;

public record AnimalResponse(
        Long id,
        String nomeTutor,
        String nomeAnimal,
        String especie,
        String raca,
        LocalDateTime dataNascimento,
        Long peso
) {
}
