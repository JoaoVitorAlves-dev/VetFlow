package com.alves.vetflow.repositories;

import com.alves.vetflow.domain.RefreshToken;
import com.alves.vetflow.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    void deleteByUsuario(Usuario usuario);

    Optional<RefreshToken> findByToken(String token);
}
