package com.breaze.PrimeraApiCasa.services;

import com.breaze.PrimeraApiCasa.entities.LibroCategoria;
import com.breaze.PrimeraApiCasa.entities.LibroCategoriaId;

import java.util.List;

public interface ILibroCategoriaService {

    List<LibroCategoria> findByLibroId(Long libroId);

    List<LibroCategoria> findByCategoriaId(Long categoriaId);

    LibroCategoria save(LibroCategoria libroCategoria);

    boolean delete(LibroCategoriaId id);
}
