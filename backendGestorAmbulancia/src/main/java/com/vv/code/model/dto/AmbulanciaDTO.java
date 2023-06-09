package com.vv.code.model.dto;

/**
 * @author Natanael Muñoz
 * @version 1.0 Date: 09/06/2023
 */
public class AmbulanciaDTO {

	private String numeroPlaca;
	private String modelo;
	private String tipo;
	private boolean estado;
	private String observaciones;

	public AmbulanciaDTO(String numeroPlaca, String modelo, String tipo, boolean estado, String observaciones) {
		super();
		this.numeroPlaca = numeroPlaca;
		this.modelo = modelo;
		this.tipo = tipo;
		this.estado = estado;
		this.observaciones = observaciones;
	}

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

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
}
