package com.breaze.PrimeraApiCasa.repository;

import com.breaze.PrimeraApiCasa.entities.LibroCategoria;
import com.breaze.PrimeraApiCasa.entities.LibroCategoriaId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibroCategoriaRepository extends JpaRepository<LibroCategoria, LibroCategoriaId> {

    List<LibroCategoria> findByIdLibroId(Long libroId);

    List<LibroCategoria> findByIdCategoriaId(Long categoriaId);
}
