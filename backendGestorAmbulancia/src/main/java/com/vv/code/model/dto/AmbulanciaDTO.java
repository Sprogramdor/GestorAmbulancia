package com.vv.code.model.dto;

import java.util.List;

/**
 * @author Natanael Muñoz
 * @version 1.0 Date: 09/06/2023
 */
public class AmbulanciaDTO {

	private String numeroPlaca;
	private String modelo;
	private String tipo;
	private boolean estado;
	private List<String> observaciones;

	public String getNumeroPlaca() {
		return numeroPlaca;
	}

	public void setNumeroPlaca(String numeroPlaca) {
		this.numeroPlaca = numeroPlaca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public List<String> getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(List<String> observaciones) {
		this.observaciones = observaciones;
	}
}
