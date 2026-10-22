package com.alves.vetflow.repositories;

import com.alves.vetflow.domain.ConsultaProcedimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaProcedimentoRepository extends JpaRepository<ConsultaProcedimento, Long> {
}
