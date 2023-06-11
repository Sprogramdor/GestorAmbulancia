package com.vv.code.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vv.code.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query("SELECT u FROM Usuario u WHERE u.correo = :correo")
	Usuario findByEmail(@Param("correo") String correoElectronico);

	@Query("SELECT u FROM Usuario u INNER JOIN Roles r WHERE r.rol = :rol")
	List<Usuario> findByRol(@Param("rol") String rol);

	@Query(value = "SELECT u FROM usuarios u, roles r ON u.rol_fk = r.id WHERE r.rol = :rol", nativeQuery = true)
	List<Usuario> findByRolName(@Param("rol") String rol);

}
