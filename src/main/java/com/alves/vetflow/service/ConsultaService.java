package com.alves.vetflow.service;

import com.alves.vetflow.domain.Animal;
import com.alves.vetflow.domain.Clinica;
import com.alves.vetflow.domain.Consulta;
import com.alves.vetflow.domain.Usuario;
import com.alves.vetflow.dto.request.ConsultaRequest;
import com.alves.vetflow.dto.response.ConsultaResponse;
import com.alves.vetflow.mapper.ConsultaMapper;
import com.alves.vetflow.repositories.AnimalRepository;
import com.alves.vetflow.repositories.ClinicaRepository;
import com.alves.vetflow.repositories.ConsultaRepository;
import com.alves.vetflow.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final ClinicaRepository clinicaRepository;
    private final UsuarioRepository usuarioRepository;
    private final AnimalRepository animalRepository;

    public ConsultaService(ConsultaRepository consultaRepository, ClinicaRepository clinicaRepository, UsuarioRepository usuarioRepository, AnimalRepository animalRepository) {
        this.consultaRepository = consultaRepository;
        this.clinicaRepository = clinicaRepository;
        this.usuarioRepository = usuarioRepository;
        this.animalRepository = animalRepository;
    }

    public Page<ConsultaResponse> listar(Pageable pageable) {
        return consultaRepository.findAll(pageable).map(ConsultaMapper::toDTO);
    }

    @Transactional
    public ConsultaResponse criar(ConsultaRequest request, Authentication auth) {
        Usuario usuario = usuarioRepository.findByUsername(auth.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));

        Animal animal = animalRepository.findByIdAndTutor(request.animalId(), usuario)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Animal não encontrado."));

        Clinica clinica = clinicaRepository.findById(request.clinicaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Clinica não encontrada."));

        Consulta consulta = new Consulta();
        consulta.setAnimal(animal);
        consulta.setClinica(clinica);
        consulta.setDataHora(request.dataHora());
        consulta.setStatus(request.status());
        consulta.setObservacoes(request.observacao());
        consulta.setVeterinario(usuario);

        return ConsultaMapper.toDTO(consultaRepository.save(consulta));
    }

    public ConsultaResponse atualizar(Long id, ConsultaRequest req, Authentication auth) {
        Usuario usuario = usuarioRepository.findByUsername(auth.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));

        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Consulta não encontrada."));

        consulta.setObservacoes(req.observacao());
        consulta.setStatus(req.status());
        consulta.setDataHora(req.dataHora());

        return ConsultaMapper.toDTO(consultaRepository.save(consulta));
    }

    public void apagar(Long id, Authentication auth) {
        Usuario usuario = usuarioRepository.findByUsername(auth.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));

        Consulta consulta = consultaRepository.findByIdAndAnimalTutor(id, usuario)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Consulta não encontrada."));

        consultaRepository.delete(consulta);
    }

}
