package com.vv.code.service;

import java.io.ByteArrayInputStream;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vv.code.utils.Utils;

@Service
public class ReporteService {
  
	private final Utils utils;

	public ReporteService(Utils utils) {
		super();
		this.utils = utils;
	}

	public ResponseEntity<InputStreamResource> generarReporteServicio() {
		ByteArrayInputStream stream = utils.generarReporteServicios();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=reporteServicios.xlsx");

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
	}

}
