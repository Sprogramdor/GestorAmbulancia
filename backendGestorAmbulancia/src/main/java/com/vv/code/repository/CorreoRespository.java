package com.vv.code.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vv.code.model.entity.Correo;

public interface CorreoRespository extends JpaRepository<Correo, Long> {

}
