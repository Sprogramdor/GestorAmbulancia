package com.vv.code.model.dto;

import java.util.List;

public class ClienteDTO {

	private String cedula;
	private String nombres;
	private String apellidos;
	private String correo;
	private String sexo;
	private String fechaNacimiento;
	private List<String> numeroTelefonico;

	public ClienteDTO() {
	}

	public ClienteDTO(String cedula, String nombres, String apellidos, String correo, String sexo,
			String fechaNacimiento) {
		super();
		this.cedula = cedula;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.correo = correo;
		this.sexo = sexo;
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public List<String> getNumeroTelefonico() {
		return numeroTelefonico;
	}

	public void setNumeroTelefonico(List<String> numeroTelefonico) {
		this.numeroTelefonico = numeroTelefonico;
	}

}
