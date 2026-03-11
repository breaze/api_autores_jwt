package com.breaze.PrimeraApiCasa.services.impl;

import com.breaze.PrimeraApiCasa.entities.Libro;
import com.breaze.PrimeraApiCasa.repository.LibroRepository;
import com.breaze.PrimeraApiCasa.services.ILibroService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LibroServiceImpl implements ILibroService {

    private final LibroRepository libroRepository;

    @Override
    public List<Libro> findAll() {
        return libroRepository.findAll();
    }

    @Override
    public Optional<Libro> findById(Long id) {
        return libroRepository.findById(id);
    }

    @Override
    public List<Libro> findByAutorId(Long autorId) {
        return libroRepository.findByAutorId(autorId);
    }

    @Override
    public Libro save(Libro libro) {
        return libroRepository.save(libro);
    }

    @Override
    public Optional<Libro> update(Long id, Libro libro) {
        return libroRepository.findById(id).map(existing -> {
            existing.setTitulo(libro.getTitulo());
            existing.setAnioPublicacion(libro.getAnioPublicacion());
            if (libro.getAutor() != null) {
                existing.setAutor(libro.getAutor());
            }
            return libroRepository.save(existing);
        });
    }

    @Override
    public boolean delete(Long id) {
        if (!libroRepository.existsById(id)) return false;
        libroRepository.deleteById(id);
        return true;
    }
}
