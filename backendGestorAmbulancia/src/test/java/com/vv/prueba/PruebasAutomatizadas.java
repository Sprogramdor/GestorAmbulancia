package com.vv.prueba;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.vv.code.controller.ClienteController;
import com.vv.code.model.dto.ClienteDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.vv.code.utils.Utils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayInputStream;
import java.util.List;

/**
 * @author Natanael Muñoz
 * @version 1.0 Date: 09/06/2023
 */
public class PruebasAutomatizadas {

	private final Utils utils = new Utils();
	private final ClienteController clienteController;

	public PruebasAutomatizadas(ClienteController clienteController) {
		this.clienteController = clienteController;
	}


	@Test
	@Disabled
	/**
	 * @param remitente          - correo de donde se envia
	 * @param contrasena         - contraseña del remitente
	 * @param correoDestinatario - correo de donde se va a enviar
	 * @param subject            - El titulo del correo
	 * @param message            - Mensaje del correo
	 */
	public void enviarCoreo() {
		String correoRemitente = "natanael.munoz.s@gmail.com";
		String contrasena = "tdlpqbuulwzjilkq";
		String correoDestinatario = "natanael.munoz.s@gmail.com";
		String subject = "RECUPERACION DE CUENTA";
		String message = "Hola";
		boolean bandera = utils.enviarCorreo(correoRemitente, contrasena, correoDestinatario, subject, message);
		assertTrue(bandera);
	}

	@Test
	@Disabled
	public void generarInforme(){
		ByteArrayInputStream informe = utils.generarReporteServicios();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=reporteServicios.xlsx");

		Assertions.assertNotNull(ResponseEntity.ok().headers(headers).body(new InputStreamResource(informe)));
	}

	@Test
	@Disabled
	public void listarClientes() {
		ResponseEntity<List<ClienteDTO>> lstCliente = clienteController.listarClientes();
		lstCliente.getBody().stream().forEach(System.out::println);
		Assertions.assertNotNull(lstCliente);
	}
}
