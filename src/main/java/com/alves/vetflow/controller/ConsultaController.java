package com.alves.vetflow.controller;

import com.alves.vetflow.dto.request.ConsultaRequest;
import com.alves.vetflow.dto.response.ConsultaResponse;
import com.alves.vetflow.service.ConsultaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<ConsultaResponse>> listar(Pageable pageable) {
        return ResponseEntity.ok(consultaService.listar(pageable));
    }

    @PostMapping("/criar")
    public ResponseEntity<ConsultaResponse> criar(@RequestBody ConsultaRequest request, Authentication auth) {
        return ResponseEntity.status(HttpStatus.CREATED).body(consultaService.criar(request, auth));
    }

    @PostMapping("/atualizar/{id}")
    public ResponseEntity<ConsultaResponse> atualizar(@PathVariable Long id, @RequestBody @Valid ConsultaRequest req, Authentication auth) {
        return ResponseEntity.ok(consultaService.atualizar(id, req, auth));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> apagar(@PathVariable Long id, Authentication auth) {
        consultaService.apagar(id, auth);
        return ResponseEntity.noContent().build();
    }
}
