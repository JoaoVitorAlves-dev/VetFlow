package com.alves.vetflow.mapper;

import com.alves.vetflow.domain.Role;
import com.alves.vetflow.domain.Usuario;
import com.alves.vetflow.dto.request.RegisterRequest;
import com.alves.vetflow.dto.response.RegisterResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UsuarioMapper {

    public Usuario toEntity(RegisterRequest req) {
        Usuario usuario = new Usuario();
        usuario.setUsername(req.username());
        usuario.setRole(Role.USUARIO);
        return usuario;
    }

    public RegisterResponse toDTO(Usuario usuario) {
        return new RegisterResponse(
                usuario.getId(), usuario.getUsername()
        );
    }

}
