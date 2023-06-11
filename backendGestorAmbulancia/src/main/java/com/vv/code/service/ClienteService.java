package com.vv.code.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vv.code.mapper.ClienteMapper;
import com.vv.code.model.dto.ClienteDTO;
import com.vv.code.repository.ClienteRepository;

@Service
public class ClienteService {

	private final ClienteRepository clienteRepository;
	private final ClienteMapper clienteMapper;

	public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
		super();
		this.clienteRepository = clienteRepository;
		this.clienteMapper = clienteMapper;
	}

	public ResponseEntity<List<ClienteDTO>> listarClientes() {
		// TODO Auto-generated method stub
		return null;
	}

}
