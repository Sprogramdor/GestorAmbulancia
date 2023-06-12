package com.vv.code.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.vv.code.model.dto.UsuarioDTO;
import com.vv.code.model.entity.Usuario;

@Component
public class UsuarioMapper implements Function<Usuario, UsuarioDTO> {

	@Override
	public UsuarioDTO apply(Usuario usuario) {
		return new UsuarioDTO(usuario.getId(), usuario.getCedula(), usuario.getNombres(), usuario.getApellidos(),
				usuario.getSexo(), usuario.getCorreo(), String.valueOf(usuario.getFechaNacimiento()),
				usuario.getFechaContrato(), usuario.getNombreUsuario());
	}

}
