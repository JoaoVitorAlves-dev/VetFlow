package com.alves.vetflow.mapper;

import com.alves.vetflow.domain.Role;
import com.alves.vetflow.domain.Usuario;
import com.alves.vetflow.dto.request.UsuarioRequest;
import com.alves.vetflow.dto.response.UsuarioResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UsuarioMapper {

    public Usuario toEntity(UsuarioRequest req) {
        Usuario usuario = new Usuario();
        usuario.setUsername(req.username());
        usuario.setPassword(req.password());
        usuario.setRole(Role.USUARIO);
        return usuario;
    }

    public UsuarioResponse toDTO(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(), usuario.getUsername()
        );
    }

}
