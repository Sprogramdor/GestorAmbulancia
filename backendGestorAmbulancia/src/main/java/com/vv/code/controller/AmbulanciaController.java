package com.vv.code.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vv.code.model.dto.AmbulanciaDTO;
import com.vv.code.service.AmbulanciaService;

@RestController
/**
 * @author Natanael Muñoz
 * @version 1.0 Date: 09/06/2023
 */
public class AmbulanciaController {

	@Autowired
	private AmbulanciaService ambulanciaService;

	@GetMapping
	@RequestMapping(path = "/listarAmbulancias")
	public List<ResponseEntity<AmbulanciaDTO>> listarAmbulancias() {
		return ambulanciaService.listarAmbulancias();
	}

}
