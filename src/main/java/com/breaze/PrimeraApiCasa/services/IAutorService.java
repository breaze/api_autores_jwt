package com.breaze.PrimeraApiCasa.services;

import com.breaze.PrimeraApiCasa.entities.Autor;

import java.util.List;
import java.util.Optional;

public interface IAutorService {

    List<Autor> findAll();

    Optional<Autor> findById(Long id);

    Autor save(Autor autor);

    Optional<Autor> update(Long id, Autor autor);

    boolean delete(Long id);
}
