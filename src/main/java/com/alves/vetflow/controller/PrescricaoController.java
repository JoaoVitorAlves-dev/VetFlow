package com.alves.vetflow.controller;

import com.alves.vetflow.dto.request.PrescricaoRequest;
import com.alves.vetflow.dto.response.PrescricaoResponse;
import com.alves.vetflow.service.PrescricaoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prescricao")
public class PrescricaoController {

    private final PrescricaoService prescricaoService;

    public PrescricaoController(PrescricaoService prescricaoService) {
        this.prescricaoService = prescricaoService;
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<PrescricaoResponse>> listar(Pageable pageable) {
        return ResponseEntity.ok(prescricaoService.listar(pageable));
    }

    @PostMapping("/criar")
    public ResponseEntity<PrescricaoResponse> criar(@Valid @RequestBody PrescricaoRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(prescricaoService.criar(req));
    }

    @PostMapping("/atualizar/{id}")
    public ResponseEntity<PrescricaoResponse> atualizar(@PathVariable Long id, @Valid @RequestBody PrescricaoRequest req) {
        return ResponseEntity.ok(prescricaoService.atualizar(id, req));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        prescricaoService.apagar(id);
        return ResponseEntity.noContent().build();
    }

}
