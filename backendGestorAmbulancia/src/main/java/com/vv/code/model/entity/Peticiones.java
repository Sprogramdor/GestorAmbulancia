package com.vv.code.model.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class Peticiones {

	@Id
	@SequenceGenerator(name = "peticiones_id_seq", sequenceName = "peticiones_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "peticiones_id_seq")
	private Long id;

	private Hospital hospital;

	private Clientes cliente;

	private Ambulancia ambulancia;

	private String puntoOrigen;
	private String puntoDestino;
	private Date fechaRegistro;
	private boolean estado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public Clientes getCliente() {
		return cliente;
	}

	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
	}

	public Ambulancia getAmbulancia() {
		return ambulancia;
	}

	public void setAmbulancia(Ambulancia ambulancia) {
		this.ambulancia = ambulancia;
	}

	public String getPuntoOrigen() {
		return puntoOrigen;
	}

	public void setPuntoOrigen(String puntoOrigen) {
		this.puntoOrigen = puntoOrigen;
	}

	public String getPuntoDestino() {
		return puntoDestino;
	}

	public void setPuntoDestino(String puntoDestino) {
		this.puntoDestino = puntoDestino;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

}
