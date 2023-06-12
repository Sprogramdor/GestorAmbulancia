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

		try {
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

		} catch (Exception e) {
			return new ResponseEntity<String>("CORREO EXISTENTE", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<String>("EXITOSO", HttpStatus.CREATED);
	}

	public ResponseEntity<String> cambiarConfiguracionDeUsuario(Long id, UsuarioDTO usuarioDTO) {
		if (id <= 0 || id == null || usuarioDTO == null) {
			return new ResponseEntity<String>("ERROR EN LA PETICION", HttpStatus.BAD_REQUEST);
		}

		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if (!usuario.isPresent()) {
			return new ResponseEntity<String>("RECURSO NO ENCONTRADO", HttpStatus.NOT_FOUND);
		}

		usuario.get().setContrasena(usuarioDTO.getContrasena());
		usuarioRepository.save(usuario.get());

		return new ResponseEntity<String>("EXITOSO", HttpStatus.ACCEPTED);
	}

	public ResponseEntity<String> modificarUsuario(Long id, UsuarioDTO usuarioDTO) {
		if (id <= 0 || id == null || usuarioDTO == null) {
			return new ResponseEntity<String>("ERROR EN LA PETICION", HttpStatus.BAD_REQUEST);
		}

		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if (!usuario.isPresent()) {
			return new ResponseEntity<String>("RECURSO NO ENCONTRADO", HttpStatus.NOT_FOUND);
		}

		usuario.get().setNombreUsuario(usuarioDTO.getNombreUsuario());
		usuario.get().setNombres(usuarioDTO.getNombres());
		usuario.get().setApellidos(usuarioDTO.getApellidos());
		usuario.get().setSexo(usuarioDTO.getSexo());
		usuario.get().setCorreo(usuarioDTO.getCorreo());
		usuarioRepository.save(usuario.get());

		return new ResponseEntity<String>("EXITOSO", HttpStatus.ACCEPTED);
	}

	public ResponseEntity<String> eliminarUsuario(Long id) {
		if (id <= 0 || id == null) {
			return new ResponseEntity<String>("ERROR EN LA PETICION", HttpStatus.BAD_REQUEST);
		}

		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if (!usuario.isPresent()) {
			return new ResponseEntity<String>("RECURSO NO ENCONTRADO", HttpStatus.NOT_FOUND);
		}

		usuarioRepository.deleteById(id);

		return new ResponseEntity<String>("EXITOSO", HttpStatus.ACCEPTED);
	}

	public ResponseEntity<UsuarioDTO> ingresarAlSistema(UsuarioDTO usuarioDTO) {
		if (usuarioDTO == null) {
			return new ResponseEntity<UsuarioDTO>(new UsuarioDTO(), HttpStatus.BAD_REQUEST);
		}

		Optional<Usuario> usuario = usuarioRepository.findByNombreUsuario(usuarioDTO.getNombreUsuario());
		if (!usuario.isPresent()) {
			return new ResponseEntity<UsuarioDTO>(new UsuarioDTO(), HttpStatus.NOT_FOUND);
		}

		UsuarioDTO dto = new UsuarioDTO();
		dto.setId(usuario.get().getId());
		dto.setCedula(usuario.get().getCedula());
		dto.setNombres(usuario.get().getNombres());
		dto.setApellidos(usuario.get().getApellidos());
		dto.setCorreo(usuario.get().getCorreo());
		dto.setSexo(usuario.get().getSexo());
		dto.setNombreUsuario(usuario.get().getNombreUsuario());
		dto.setContrasena(usuario.get().getContrasena());

		return new ResponseEntity<UsuarioDTO>(dto, HttpStatus.ACCEPTED);

	}

}
