package com.breaze.PrimeraApiCasa.services;

import com.breaze.PrimeraApiCasa.entities.UsuarioRol;
import com.breaze.PrimeraApiCasa.entities.UsuarioRolId;

import java.util.List;

public interface IUsuarioRolService {

    List<UsuarioRol> findByUsuarioId(Long usuarioId);

    List<UsuarioRol> findByRolId(Long rolId);

    UsuarioRol save(UsuarioRol usuarioRol);

    boolean delete(UsuarioRolId id);
}
