package com.breaze.PrimeraApiCasa.services.impl;

import com.breaze.PrimeraApiCasa.entities.DetalleLibro;
import com.breaze.PrimeraApiCasa.repository.DetalleLibroRepository;
import com.breaze.PrimeraApiCasa.services.IDetalleLibroService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DetalleLibroServiceImpl implements IDetalleLibroService {

    private final DetalleLibroRepository detalleLibroRepository;

    @Override
    public Optional<DetalleLibro> findById(Long id) {
        return detalleLibroRepository.findById(id);
    }

    @Override
    public Optional<DetalleLibro> findByLibroId(Long libroId) {
        return detalleLibroRepository.findByLibroId(libroId);
    }

    @Override
    public DetalleLibro save(DetalleLibro detalle) {
        return detalleLibroRepository.save(detalle);
    }

    @Override
    public Optional<DetalleLibro> update(Long id, DetalleLibro detalle) {
        return detalleLibroRepository.findById(id).map(existing -> {
            existing.setIsbn(detalle.getIsbn());
            existing.setNumPaginas(detalle.getNumPaginas());
            existing.setIdioma(detalle.getIdioma());
            if (detalle.getLibro() != null) {
                existing.setLibro(detalle.getLibro());
            }
            return detalleLibroRepository.save(existing);
        });
    }

    @Override
    public boolean delete(Long id) {
        if (!detalleLibroRepository.existsById(id)) return false;
        detalleLibroRepository.deleteById(id);
        return true;
    }
}
