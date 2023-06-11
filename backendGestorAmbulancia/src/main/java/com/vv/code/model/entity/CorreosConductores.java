package com.vv.code.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class CorreosConductores {

	@Id
	@SequenceGenerator(name = "correosConductores_id_seq", sequenceName = "correosConductores_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "correosConductores_id_seq")
	private Long id;

	private Conductores conductores;
	private String numeroTelefono;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Conductores getConductores() {
		return conductores;
	}

	public void setConductores(Conductores conductores) {
		this.conductores = conductores;
	}

	public String getNumeroTelefono() {
		return numeroTelefono;
	}

	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}

}
