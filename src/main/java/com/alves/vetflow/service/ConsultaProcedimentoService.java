package com.alves.vetflow.service;

import com.alves.vetflow.domain.Consulta;
import com.alves.vetflow.domain.ConsultaProcedimento;
import com.alves.vetflow.domain.Procedimento;
import com.alves.vetflow.dto.request.ConsultaProcedimentoRequest;
import com.alves.vetflow.dto.response.ConsultaProcedimentoResponse;
import com.alves.vetflow.mapper.ConsultaProcedimentoMapper;
import com.alves.vetflow.repositories.ConsultaProcedimentoRepository;
import com.alves.vetflow.repositories.ConsultaRepository;
import com.alves.vetflow.repositories.ProcedimentoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ConsultaProcedimentoService {

    private final ConsultaProcedimentoRepository consultaProcedimentoRepository;
    private final ProcedimentoRepository procedimentoRepository;
    private final ConsultaRepository consultaRepository;

    public ConsultaProcedimentoService(ConsultaProcedimentoRepository consultaProcedimentoRepository, ProcedimentoRepository procedimentoRepository, ConsultaRepository consultaRepository) {
        this.consultaProcedimentoRepository = consultaProcedimentoRepository;
        this.procedimentoRepository = procedimentoRepository;
        this.consultaRepository = consultaRepository;
    }

    public Page<ConsultaProcedimentoResponse> listar(Pageable pageable) {
        return consultaProcedimentoRepository.findAll(pageable).map(ConsultaProcedimentoMapper::toDTO);
    }

    public ConsultaProcedimentoResponse criar(ConsultaProcedimentoRequest req) {
        Consulta consulta = consultaRepository.findById(req.consultaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Procedimento procedimento = procedimentoRepository.findById(req.procedimentoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        ConsultaProcedimento entity = ConsultaProcedimentoMapper.toEntity(req, consulta, procedimento);
        return ConsultaProcedimentoMapper.toDTO(consultaProcedimentoRepository.save(entity));
    }

    public ConsultaProcedimentoResponse atualizar(Long id, ConsultaProcedimentoRequest req) {
        ConsultaProcedimento consultaProcedimento = consultaProcedimentoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        consultaProcedimento.setQuantidade(req.quantidade());
        consultaProcedimento.setValorCobrado(req.valorCobrado());
        return ConsultaProcedimentoMapper.toDTO(consultaProcedimentoRepository.save(consultaProcedimento));
    }

    public void apagar(Long id) {
        consultaProcedimentoRepository.findById(id).ifPresent(consultaProcedimentoRepository::delete);
    }

}
