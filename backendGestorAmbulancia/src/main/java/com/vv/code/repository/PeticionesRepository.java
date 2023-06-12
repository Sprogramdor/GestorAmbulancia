package com.vv.code.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vv.code.model.entity.Peticion;

@Repository
public interface PeticionesRepository extends JpaRepository<Peticion, Long> {

	@Query(value = "SELECT p FROM peticiones p INNER JOIN ambulancia a ON p.id = a.id", nativeQuery = true)
	Peticion findByUser(@Param("id_usuario") Long id);

//	@Query(value = "SELECT p.punto_origen, p.punto_destino, a.numero_placa, c.cedula " + "FROM peticion p "
//			+ "INNER JOIN ambulancia a ON p.id = a.peticion_fk "
//			+ "INNER JOIN conductor c ON a.conductor = c.id ", nativeQuery = true)
//	List<Reporte> findByReportServices();

}
