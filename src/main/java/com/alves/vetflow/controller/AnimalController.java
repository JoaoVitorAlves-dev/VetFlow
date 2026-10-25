package com.alves.vetflow.controller;

import com.alves.vetflow.dto.request.AnimalRequest;
import com.alves.vetflow.dto.response.AnimalResponse;
import com.alves.vetflow.service.AnimalService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/animal")
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<AnimalResponse>> listar(Pageable pageable) {
        return ResponseEntity.ok(animalService.buscar(pageable));
    }

    @PostMapping("/criar")
    public ResponseEntity<AnimalResponse> criar(@Valid @RequestBody AnimalRequest request, Authentication auth) {
        return ResponseEntity.status(HttpStatus.CREATED).body(animalService.criar(request, auth));
    }

    @PostMapping("/atualizar/{id}")
    public ResponseEntity<AnimalResponse> atualizar(@PathVariable Long id, @Valid @RequestBody AnimalRequest request, Authentication auth) {
        return ResponseEntity.ok(animalService.atualizar(id, request, auth));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id, Authentication auth) {
        animalService.deletar(id, auth);
        return ResponseEntity.noContent().build();
    }
}
