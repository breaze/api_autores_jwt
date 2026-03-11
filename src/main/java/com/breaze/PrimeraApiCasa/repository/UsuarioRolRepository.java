package com.breaze.PrimeraApiCasa.repository;

import com.breaze.PrimeraApiCasa.entities.UsuarioRol;
import com.breaze.PrimeraApiCasa.entities.UsuarioRolId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRolRepository extends JpaRepository<UsuarioRol, UsuarioRolId> {

    List<UsuarioRol> findByIdUsuarioId(Long usuarioId);

    List<UsuarioRol> findByIdRolId(Long rolId);
}
