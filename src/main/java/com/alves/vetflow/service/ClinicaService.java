package com.alves.vetflow.service;

import com.alves.vetflow.domain.Clinica;
import com.alves.vetflow.dto.request.ClinicaRequest;
import com.alves.vetflow.dto.response.ClinicaResponse;
import com.alves.vetflow.mapper.ClinicaMapper;
import com.alves.vetflow.repositories.ClinicaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClinicaService {

    private final ClinicaRepository clinicaRepository;

    public ClinicaService(ClinicaRepository clinicaRepository) {
        this.clinicaRepository = clinicaRepository;
    }

    public Page<ClinicaResponse> listar(Pageable pageable) {
        return clinicaRepository.findAll(pageable).map(ClinicaMapper::toDTO);
    }

    public ClinicaResponse criar(ClinicaRequest req) {
        if (clinicaRepository.existsByNome(req.nome())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Nome da clinica já existe.");
        }

        Clinica entity = ClinicaMapper.toEntity(req);
        Clinica save = clinicaRepository.save(entity);
        return ClinicaMapper.toDTO(save);
    }

    public ClinicaResponse atualizar(Long id, ClinicaRequest req) {
        Clinica clinica = clinicaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (clinicaRepository.existsByNome(req.nome())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        clinica.setId(id);
        clinica.setRua(req.rua());
        clinica.setEstado(req.estado());
        clinica.setCep(req.cep());
        clinica.setCidade(req.cidade());
        clinica.setNome(req.nome());

        return ClinicaMapper.toDTO(clinicaRepository.save(clinica));
    }

    public void apagar(Long id) {
        clinicaRepository.findById(id).ifPresent(clinicaRepository::delete);
    }
}
