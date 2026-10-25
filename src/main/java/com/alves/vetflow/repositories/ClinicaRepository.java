package com.alves.vetflow.repositories;

import com.alves.vetflow.domain.Clinica;
import com.alves.vetflow.dto.response.ClinicaResponse;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.internal.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public interface ClinicaRepository extends JpaRepository<Clinica, Long> {
    Page<ClinicaResponse> findAllBy(Pageable pageable);

    Boolean existsByNome(@NotBlank String nome);
}
