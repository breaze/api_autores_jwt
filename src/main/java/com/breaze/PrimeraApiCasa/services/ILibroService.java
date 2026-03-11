package com.breaze.PrimeraApiCasa.services;

import com.breaze.PrimeraApiCasa.entities.Libro;

import java.util.List;
import java.util.Optional;

public interface ILibroService {

    List<Libro> findAll();

    Optional<Libro> findById(Long id);

    List<Libro> findByAutorId(Long autorId);

    Libro save(Libro libro);

    Optional<Libro> update(Long id, Libro libro);

    boolean delete(Long id);
}
