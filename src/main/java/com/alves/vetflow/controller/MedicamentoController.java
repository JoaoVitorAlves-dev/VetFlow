package com.alves.vetflow.controller;

import com.alves.vetflow.dto.request.MedicamentoRequest;
import com.alves.vetflow.dto.response.MedicamentoResponse;
import com.alves.vetflow.service.MedicamentoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicamento")
public class MedicamentoController {

    private final MedicamentoService medicamentoService;

    public MedicamentoController(MedicamentoService medicamentoService) {
        this.medicamentoService = medicamentoService;
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<MedicamentoResponse>> listar(Pageable pageable) {
        return ResponseEntity.ok(medicamentoService.listar(pageable));
    }

    @PostMapping("/criar")
    public ResponseEntity<MedicamentoResponse> criar(@Valid @RequestBody MedicamentoRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(medicamentoService.criar(req));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<MedicamentoResponse> atualizar(@PathVariable Long id, @Valid @RequestBody MedicamentoRequest req) {
        return ResponseEntity.ok(medicamentoService.atualizar(id, req));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> apagar(@PathVariable Long id) {
        medicamentoService.apagar(id);
        return ResponseEntity.noContent().build();
    }

}
