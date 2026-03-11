package com.breaze.PrimeraApiCasa.services;

import com.breaze.PrimeraApiCasa.entities.Categoria;

import java.util.List;
import java.util.Optional;

public interface ICategoriaService {

    List<Categoria> findAll();

    Optional<Categoria> findById(Long id);

    Categoria save(Categoria categoria);

    Optional<Categoria> update(Long id, Categoria categoria);

    boolean delete(Long id);
}
