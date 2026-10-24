package com.alves.vetflow.service;

import com.alves.vetflow.domain.RefreshToken;
import com.alves.vetflow.domain.Usuario;
import com.alves.vetflow.repositories.RefreshTokenRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Value("${jwt.expiration.in.days}")
    private long expirationInDays;

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Transactional
    public RefreshToken criar(Usuario usuario) {
        refreshTokenRepository.deleteByUsuario(usuario);

        RefreshToken rt = new RefreshToken();
        rt.setUsuario(usuario);
        rt.setExpiraEm(Instant.now().plus(expirationInDays, ChronoUnit.DAYS));
        rt.setToken(UUID.randomUUID().toString());
        return refreshTokenRepository.save(rt);
    }

    @Transactional
    public RefreshToken validarERotacionar(String token) {
        RefreshToken atual = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Token não encontrado."));

        if (atual.getExpiraEm().isBefore(Instant.now())) {
            refreshTokenRepository.delete(atual);
            throw new RuntimeException("Token expirado.");
        }
        return criar(atual.getUsuario());
    }

    @Transactional
    public void apagarPorToken(String token) {
        refreshTokenRepository.findByToken(token).ifPresent(refreshTokenRepository::delete);
    }
}
