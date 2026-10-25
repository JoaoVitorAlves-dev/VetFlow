package com.alves.vetflow.service;

import com.alves.vetflow.domain.Animal;
import com.alves.vetflow.domain.Usuario;
import com.alves.vetflow.dto.request.AnimalRequest;
import com.alves.vetflow.dto.response.AnimalResponse;
import com.alves.vetflow.mapper.AnimalMapper;
import com.alves.vetflow.repositories.AnimalRepository;
import com.alves.vetflow.repositories.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final UsuarioRepository usuarioRepository;

    public AnimalService(AnimalRepository animalRepository, UsuarioRepository usuarioRepository) {
        this.animalRepository = animalRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Page<AnimalResponse> buscar(Pageable pageable) {
        return animalRepository.findAllBy(pageable);
    }

    public AnimalResponse criar(AnimalRequest req, Authentication auth) {
        Usuario tutor = usuarioRepository.findByUsername(auth.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));

        Animal animal = AnimalMapper.toEntity(req, tutor);
        return AnimalMapper.toDTO(animalRepository.save(animal));
    }

    @Transactional
    public AnimalResponse atualizar(Long id, AnimalRequest req, Authentication auth) {
        Usuario usuario = usuarioRepository.findByUsername(auth.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));

        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (!animal.getTutor().getId().equals(usuario.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        animal.setNome(req.nome());
        animal.setEspecie(req.especie());
        animal.setRaca(req.raca());
        animal.setDataNascimento(req.dataNascimento());
        animal.setPeso(req.peso());

        Animal salvo = animalRepository.save(animal);
        return AnimalMapper.toDTO(salvo);
    }

    public void deletar(Long id, Authentication auth) {
        Usuario usuario = usuarioRepository.findByUsername(auth.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));

        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (!animal.getTutor().getId().equals(usuario.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        animalRepository.delete(animal);
    }
}
