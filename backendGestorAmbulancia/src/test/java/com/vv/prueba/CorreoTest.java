package com.vv.prueba;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.vv.code.utils.Utils;

public class CorreoTest {

	private final Utils utils = new Utils();

	@Test
	public void enviarCoreo() {
		String correoRemitente = "";
		String contrasena = "";
		String correoDestinatario = "";
		String subject = "RECUPERACION DE CUENTA";
		String message = "Hola";
		boolean bandera = utils.enviarCorreo(correoRemitente, contrasena, correoDestinatario, subject, message);
		assertTrue(bandera);
	}

}
