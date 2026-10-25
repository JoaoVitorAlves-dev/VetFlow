package com.alves.vetflow.repositories;

import com.alves.vetflow.domain.Consulta;
import com.alves.vetflow.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    Optional<Consulta> findByIdAndAnimalTutor(Long id, Usuario tutor);

}
