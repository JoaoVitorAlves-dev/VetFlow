package com.alves.vetflow.controller;

import com.alves.vetflow.dto.request.ClinicaRequest;
import com.alves.vetflow.dto.response.ClinicaResponse;
import com.alves.vetflow.service.ClinicaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clinica")
public class ClinicaController {

    private final ClinicaService clinicaService;

    public ClinicaController(ClinicaService clinicaService) {
        this.clinicaService = clinicaService;
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<ClinicaResponse>> listar(Pageable pageable) {
        return ResponseEntity.ok(clinicaService.listar(pageable));
    }

    @PostMapping("/criar")
    public ResponseEntity<ClinicaResponse> criar(@Valid @RequestBody ClinicaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clinicaService.criar(request));
    }

    @PostMapping("/atualizar/{id}")
    public ResponseEntity<ClinicaResponse> atualizar(@PathVariable Long id, @Valid @RequestBody ClinicaRequest clinicaRequest) {
        return ResponseEntity.ok(clinicaService.atualizar(id, clinicaRequest));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        clinicaService.apagar(id);
        return ResponseEntity.noContent().build();
    }

}
