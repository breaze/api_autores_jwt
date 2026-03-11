package com.breaze.PrimeraApiCasa.repository;

import com.breaze.PrimeraApiCasa.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
