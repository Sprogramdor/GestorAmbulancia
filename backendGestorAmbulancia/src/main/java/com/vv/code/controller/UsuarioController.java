package com.vv.code.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vv.code.model.dto.UsuarioDTO;
import com.vv.code.service.UsuarioService;

@RestController
/**
 * @author Natanael Muñoz
 * @version 1.0 Date: 10/06/2023
 */
public class UsuarioController {

	private final UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {
		super();
		this.usuarioService = usuarioService;
	}

	@GetMapping(path = "/listarUsuarios/{tipo}")
	/**
	 * @param tipo - Tipo de usuario: gerente o secretaria
	 * @return LISTA DE USUARIOS
	 */
	public ResponseEntity<List<UsuarioDTO>> listarUsuario(@PathVariable("tipo") String tipo) {
		return usuarioService.listarUsuarios(tipo);
	}

	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@RequestMapping(path = "/registrarUsuario")
	/**
	 * @param usuarioDTO - OBJECTO EN JSON O XML DEL USUARIO
	 * @return MENSAJE DEL ESTADO DE LA PETICION
	 */
	public ResponseEntity<String> registrarUsuario(@RequestBody UsuarioDTO usuarioDTO) throws ParseException {
		return usuarioService.registrarUsuario(usuarioDTO);
	}

}
