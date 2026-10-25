package com.alves.vetflow.controller;

import com.alves.vetflow.dto.request.LoginRequest;
import com.alves.vetflow.dto.request.RegisterRequest;
import com.alves.vetflow.dto.request.TokenRequest;
import com.alves.vetflow.dto.response.RefreshTokenResponse;
import com.alves.vetflow.dto.response.RegisterResponse;
import com.alves.vetflow.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshTokenResponse> refresh(@RequestBody @Valid TokenRequest req) {
        return ResponseEntity.ok(usuarioService.refresh(req.accessToken()));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> registrar(@Valid @RequestBody RegisterRequest register) {
        usuarioService.registrar(register);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<RefreshTokenResponse> login(@Valid @RequestBody LoginRequest login) {
        return ResponseEntity.ok().body(usuarioService.login(login));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody @Valid TokenRequest req) {
        usuarioService.apagarPorToken(req.accessToken());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
