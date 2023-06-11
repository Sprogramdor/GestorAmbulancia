package com.vv.code.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vv.code.model.dto.ClienteDTO;
import com.vv.code.service.ClienteService;

@RestController
public class ClienteController {

	private final ClienteService clienteService;

	public ClienteController(ClienteService clienteService) {
		super();
		this.clienteService = clienteService;
	}

	@GetMapping
	@RequestMapping(path = "/listarCliente")
	public ResponseEntity<List<ClienteDTO>> listarClientes() {
		return clienteService.listarClientes();
	}

	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@RequestMapping(path = "/registrarCliente")
	public ResponseEntity<String> registrarCliente(@RequestBody ClienteDTO clienteDTO) {
		return null;
	}

	@PutMapping
	@RequestMapping(path = "/actualizarCliente")
	public ResponseEntity<String> modificarCliente() {
		return null;
	}

	@DeleteMapping
	@RequestMapping(path = "/eliminarCliente")
	public ResponseEntity<String> eliminarCliente() {
		return null;
	}

}
