package com.alves.vetflow.repositories;

import com.alves.vetflow.domain.Animal;
import com.alves.vetflow.domain.Usuario;
import com.alves.vetflow.dto.response.AnimalResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    Page<AnimalResponse> findAllBy(Pageable pageable);

    Optional<Animal> findByIdAndTutor(@NotNull Long aLong, Usuario usuario);
}
