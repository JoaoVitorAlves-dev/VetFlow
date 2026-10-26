package com.alves.vetflow.controller;

import com.alves.vetflow.dto.request.ParcelaRequest;
import com.alves.vetflow.dto.response.ParcelaResponse;
import com.alves.vetflow.service.ParcelaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parcela")
public class ParcelaController {

    private final ParcelaService parcelaService;

    public ParcelaController(ParcelaService parcelaService) {
        this.parcelaService = parcelaService;
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<ParcelaResponse>> listar(Pageable pageable) {
        return ResponseEntity.ok(parcelaService.listar(pageable));
    }

    @PostMapping("/criar")
    public ResponseEntity<ParcelaResponse> criar(@Valid @RequestBody ParcelaRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(parcelaService.criar(req));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ParcelaResponse> atualizar(@PathVariable Long id, @Valid @RequestBody ParcelaRequest req) {
        return ResponseEntity.ok(parcelaService.atualizar(id, req));
    }

    @DeleteMapping("/apagar/{id}")
    public ResponseEntity<Void> apagar(@PathVariable Long id) {
        parcelaService.apagar(id);
        return ResponseEntity.noContent().build();
    }
}
