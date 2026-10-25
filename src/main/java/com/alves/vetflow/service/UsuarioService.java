package com.alves.vetflow.service;

import com.alves.vetflow.domain.RefreshToken;
import com.alves.vetflow.domain.Usuario;
import com.alves.vetflow.dto.request.LoginRequest;
import com.alves.vetflow.dto.request.RegisterRequest;
import com.alves.vetflow.dto.response.RefreshTokenResponse;
import com.alves.vetflow.mapper.UsuarioMapper;
import com.alves.vetflow.repositories.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RefreshTokenService refreshTokenService;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, RefreshTokenService refreshTokenService, TokenService tokenService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.refreshTokenService = refreshTokenService;
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    public RefreshTokenResponse refresh(String token) {
        RefreshToken refreshToken = refreshTokenService.validarERotacionar(token);

        String access = tokenService.gerarToken(refreshToken.getUsuario().getUsername());

        return new RefreshTokenResponse(access, refreshToken.getToken());
    }

    public void registrar(RegisterRequest registerRequest) {
        if (usuarioRepository.existsByUsername(registerRequest.username())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username já existe.");
        }

        Usuario usuario = UsuarioMapper.toEntity(registerRequest);
        usuario.setUsername(registerRequest.username());
        usuario.setPassword(passwordEncoder.encode(registerRequest.password()));
        usuarioRepository.save(usuario);
    }

    public RefreshTokenResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));

        Usuario usuario = usuarioRepository.findByUsername(loginRequest.username()).orElseThrow();

        String access = tokenService.gerarToken(usuario.getUsername());
        String refresh = refreshTokenService.validarERotacionar(access).getToken();

        return new RefreshTokenResponse(access, refresh);
    }

    public void apagarPorToken(String token) {
        refreshTokenService.apagarPorToken(token);
    }
}
