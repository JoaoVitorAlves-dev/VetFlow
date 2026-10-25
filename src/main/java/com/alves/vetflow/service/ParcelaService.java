package com.alves.vetflow.service;

import com.alves.vetflow.domain.Consulta;
import com.alves.vetflow.domain.Parcela;
import com.alves.vetflow.dto.request.ParcelaRequest;
import com.alves.vetflow.dto.response.ParcelaResponse;
import com.alves.vetflow.mapper.ParcelaMapper;
import com.alves.vetflow.repositories.ConsultaRepository;
import com.alves.vetflow.repositories.ParcelaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ParcelaService {

    private final ParcelaRepository parcelaRepository;
    private final ConsultaRepository consultaRepository;

    public ParcelaService(ParcelaRepository parcelaRepository, ConsultaRepository consultaRepository) {
        this.parcelaRepository = parcelaRepository;
        this.consultaRepository = consultaRepository;
    }

    public Page<ParcelaResponse> listar(Pageable pageable) {
        return parcelaRepository.findAll(pageable).map(ParcelaMapper::toDTO);
    }

    public ParcelaResponse criar(ParcelaRequest req) {
        Consulta consulta = consultaRepository.findById(req.consultaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Parcela entity = ParcelaMapper.toEntity(req, consulta);
        return ParcelaMapper.toDTO(parcelaRepository.save(entity));
    }

    public ParcelaResponse atualizar(Long id, ParcelaRequest req) {
        Parcela parcela = parcelaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        parcela.setStatus(req.status());
        parcela.setVencimento(req.vencimento());
        parcela.setValor(req.valor());
        return ParcelaMapper.toDTO(parcelaRepository.save(parcela));
    }

    public void apagar(Long id) {
        parcelaRepository.findById(id).ifPresent(parcelaRepository::delete);
    }
}
