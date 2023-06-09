package com.vv.code.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.vv.code.utils.Utils;

@Service
public class ContrasenaService {

	private final Utils util = new Utils();

	public ResponseEntity<String> recuperarContrasena(@RequestParam String contrasena) {

		// TODO: FALTA IMPLEMENTAR LA LLAMADA A LA DB

		// boolean bandera = util.enviarCorreo(contrasena, contrasena);
		boolean bandera = true;

		return (bandera) ? new ResponseEntity<>("Exitoso", HttpStatus.ACCEPTED)
				: new ResponseEntity<>("Fallido", HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
