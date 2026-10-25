package com.alves.vetflow.controller;

import com.alves.vetflow.dto.request.ConsultaProcedimentoRequest;
import com.alves.vetflow.dto.response.ConsultaProcedimentoResponse;
import com.alves.vetflow.service.ConsultaProcedimentoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultaprocedimento")
public class ConsultaProcedimentoController {

    private final ConsultaProcedimentoService consultaProcedimentoService;

    public ConsultaProcedimentoController(ConsultaProcedimentoService consultaProcedimentoService) {
        this.consultaProcedimentoService = consultaProcedimentoService;
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<ConsultaProcedimentoResponse>> listar(Pageable pageable) {
        return ResponseEntity.ok(consultaProcedimentoService.listar(pageable));
    }

    @PostMapping("/criar")
    public ResponseEntity<ConsultaProcedimentoResponse> criar(@Valid @RequestBody ConsultaProcedimentoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(consultaProcedimentoService.criar(request));
    }

    @PostMapping("/atualizar/{id}")
    public ResponseEntity<ConsultaProcedimentoResponse> atualizar(@PathVariable Long id, @Valid @RequestBody ConsultaProcedimentoRequest req) {
        return ResponseEntity.ok(consultaProcedimentoService.atualizar(id, req));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> apagar(@PathVariable Long id) {
        consultaProcedimentoService.apagar(id);
        return ResponseEntity.noContent().build();
    }
}
