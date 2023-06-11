package com.vv.code.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vv.code.mapper.UsuarioMapper;
import com.vv.code.model.dto.UsuarioDTO;
import com.vv.code.model.entity.Roles;
import com.vv.code.model.entity.Usuario;
import com.vv.code.repository.RolesRepository;
import com.vv.code.repository.UsuarioRepository;

@Service
/**
 * @author Natanael Muñoz
 * @version 1.0 Date: 10/06/2023
 */
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;
	private final RolesRepository rolesRepository;
	private final UsuarioMapper usuarioMapper;

	public UsuarioService(UsuarioRepository usuarioRepository, RolesRepository rolesRepository,
			UsuarioMapper usuarioMapper) {
		super();
		this.usuarioRepository = usuarioRepository;
		this.rolesRepository = rolesRepository;
		this.usuarioMapper = usuarioMapper;
	}

	public ResponseEntity<List<UsuarioDTO>> listarUsuarios(String tipo) {

		if (tipo == null) {
			return new ResponseEntity<List<UsuarioDTO>>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
		}

		List<Usuario> listaUsuarios = usuarioRepository.findByRolName(tipo);

		List<UsuarioDTO> listaUsuariosDTO = listaUsuarios.stream().map(usuarioMapper).collect(Collectors.toList());

		return new ResponseEntity<List<UsuarioDTO>>(listaUsuariosDTO, HttpStatus.OK);

	}

	public ResponseEntity<String> registrarUsuario(UsuarioDTO usuarioDTO) throws ParseException {
		if (usuarioDTO == null) {
			return new ResponseEntity<String>("ERROR EN LA PETICION", HttpStatus.BAD_REQUEST);
		}

		Usuario usuario = new Usuario();
		usuario.setCedula(usuarioDTO.getCedula());
		usuario.setNombres(usuarioDTO.getNombres());
		usuario.setApellidos(usuarioDTO.getApellidos());
		usuario.setSexo(usuarioDTO.getSexo());
		usuario.setCorreo(usuarioDTO.getCorreo());
//
		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
		Date fechaNacimiento = formato.parse(usuarioDTO.getFechaNacimiento());

		usuario.setFechaNacimiento(fechaNacimiento);
		usuario.setFechaContrato(new Date());

		usuario.setNombreUsuario(usuarioDTO.getNombreUsuario());
		usuario.setContrasena(usuarioDTO.getContrasena());

		usuario.setEstado(true);

		Optional<Roles> rol = Optional.ofNullable(rolesRepository.findByName(usuarioDTO.getTipo()));
		if (!rol.isPresent()) {
			return new ResponseEntity<String>("NO EXISTE ROL", HttpStatus.NOT_FOUND);
		}

		usuario.setRol(rol.get());

		usuarioRepository.save(usuario);

		return new ResponseEntity<String>("EXITOSO", HttpStatus.CREATED);
	}

}
