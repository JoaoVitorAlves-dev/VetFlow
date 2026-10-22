package com.alves.vetflow.repositories;

import com.alves.vetflow.domain.PrescricaoMedicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescricaoMedicamentoRepository extends JpaRepository<PrescricaoMedicamento, Long> {
}
