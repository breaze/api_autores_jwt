package com.breaze.PrimeraApiCasa.services;

import com.breaze.PrimeraApiCasa.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    List<Usuario> findAll();

    Optional<Usuario> findById(Long id);

    Optional<Usuario> findByUsername(String username);

    Usuario save(Usuario usuario);

    Optional<Usuario> update(Long id, Usuario usuario);

    boolean delete(Long id);
}
