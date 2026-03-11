package com.breaze.PrimeraApiCasa.repository;

import com.breaze.PrimeraApiCasa.entities.DetalleLibro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DetalleLibroRepository extends JpaRepository<DetalleLibro, Long> {

    Optional<DetalleLibro> findByLibroId(Long libroId);
}
