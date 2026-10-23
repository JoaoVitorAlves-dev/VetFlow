package com.alves.vetflow.service;

import com.alves.vetflow.domain.Usuario;
import com.alves.vetflow.repositories.UsuarioRepository;
import lombok.NonNull;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username não encontrado."));

        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles(usuario.getRole().name())
                .build();
    }
}
