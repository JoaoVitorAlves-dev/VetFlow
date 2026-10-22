package com.alves.vetflow.repositories;

import com.alves.vetflow.domain.VeterinarioClinica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeterinarioClinicaRepository extends JpaRepository<VeterinarioClinica, Long> {
}
