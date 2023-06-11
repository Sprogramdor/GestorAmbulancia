package com.vv.code.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.vv.code.model.dto.ClienteDTO;
import com.vv.code.model.entity.Cliente;

@Component
public class ClienteMapper implements Function<Cliente, ClienteDTO> {

	@Override
	public ClienteDTO apply(Cliente t) {
		// TODO Auto-generated method stub
		return null;
	}

}
