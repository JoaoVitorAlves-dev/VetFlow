package com.alves.vetflow.service;

import com.alves.vetflow.domain.Medicamento;
import com.alves.vetflow.dto.request.MedicamentoRequest;
import com.alves.vetflow.dto.response.MedicamentoResponse;
import com.alves.vetflow.mapper.MedicamentoMapper;
import com.alves.vetflow.repositories.MedicamentoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MedicamentoService {

    private final MedicamentoRepository medicamentoRepository;

    public MedicamentoService(MedicamentoRepository medicamentoRepository) {
        this.medicamentoRepository = medicamentoRepository;
    }

    public Page<MedicamentoResponse> listar(Pageable pageable) {
        return medicamentoRepository.findAll(pageable).map(MedicamentoMapper::toDTO);
    }

    public MedicamentoResponse criar(MedicamentoRequest medicamentoRequest) {
        Medicamento entity = MedicamentoMapper.toEntity(medicamentoRequest);
        Medicamento save = medicamentoRepository.save(entity);
        return MedicamentoMapper.toDTO(save);
    }

    public MedicamentoResponse atualizar(Long id, MedicamentoRequest medicamentoRequest) {
        Medicamento medicamento = medicamentoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        medicamento.setNome(medicamentoRequest.nome());
        return MedicamentoMapper.toDTO(medicamentoRepository.save(medicamento));
    }

    public void apagar(Long id) {
        medicamentoRepository.findById(id).ifPresent(medicamentoRepository::delete);
    }
}
