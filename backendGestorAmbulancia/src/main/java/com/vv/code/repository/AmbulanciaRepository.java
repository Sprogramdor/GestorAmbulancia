package com.vv.code.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vv.code.model.entity.Ambulancia;

public interface AmbulanciaRepository extends JpaRepository<Ambulancia, Long> {

}
