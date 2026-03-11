package com.breaze.PrimeraApiCasa.services;

import com.breaze.PrimeraApiCasa.entities.DetalleLibro;

import java.util.Optional;

public interface IDetalleLibroService {

    Optional<DetalleLibro> findById(Long id);

    Optional<DetalleLibro> findByLibroId(Long libroId);

    DetalleLibro save(DetalleLibro detalle);

    Optional<DetalleLibro> update(Long id, DetalleLibro detalle);

    boolean delete(Long id);
}
