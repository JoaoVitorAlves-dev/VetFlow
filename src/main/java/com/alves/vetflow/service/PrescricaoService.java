package com.alves.vetflow.service;

import com.alves.vetflow.domain.Consulta;
import com.alves.vetflow.domain.Prescricao;
import com.alves.vetflow.dto.request.PrescricaoRequest;
import com.alves.vetflow.dto.response.PrescricaoResponse;
import com.alves.vetflow.mapper.PrescricaoMapper;
import com.alves.vetflow.repositories.ConsultaRepository;
import com.alves.vetflow.repositories.PrescricaoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PrescricaoService {

    private final PrescricaoRepository prescricaoRepository;
    private final ConsultaRepository consultaRepository;

    public PrescricaoService(PrescricaoRepository prescricaoRepository, ConsultaRepository consultaRepository) {
        this.prescricaoRepository = prescricaoRepository;
        this.consultaRepository = consultaRepository;
    }

    public Page<PrescricaoResponse> listar(Pageable pageable) {
        return prescricaoRepository.findAll(pageable).map(PrescricaoMapper::toDTO);
    }

    public PrescricaoResponse criar(PrescricaoRequest req) {
        Consulta consulta = consultaRepository.findById(req.consultaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Prescricao entity = PrescricaoMapper.toEntity(req, consulta);

        return PrescricaoMapper.toDTO(prescricaoRepository.save(entity));
    }

    public PrescricaoResponse atualizar(Long id, PrescricaoRequest req) {
        Prescricao prescricao = prescricaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        prescricao.setDataEmissao(req.dataEmissao());
        return PrescricaoMapper.toDTO(prescricaoRepository.save(prescricao));
    }

    public void apagar(Long id) {
        prescricaoRepository.findById(id).ifPresent(prescricaoRepository::delete);
    }
}
