package com.vv.code.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vv.code.model.entity.Ambulancia;

public interface AmbulanciaRepository extends JpaRepository<Ambulancia, Long> {

	@Query(value = "SELECT a FROM Ambulancia a WHERE a.numeroPlaca = :placa")
	Optional<Set<Ambulancia>> findbyNumeroPlaca(@Param("placa") String placa);

}
