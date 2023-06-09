package com.vv.code.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vv.code.exceptions.BadRequestException;
import com.vv.code.service.ContrasenaService;

@RestController
public class ContrasenaController {

	@Autowired
	private ContrasenaService contrasenaService;

	@PostMapping
	@RequestMapping(path = "/recuperarContrasena")
	public ResponseEntity<String> recuperarContrasena(@RequestParam String contrasena) {

		if (contrasena.isEmpty()) {
			throw new BadRequestException("SGAR-1", "El campo no puede ser vacio");
		}

		return contrasenaService.recuperarContrasena(contrasena);
	}

}
