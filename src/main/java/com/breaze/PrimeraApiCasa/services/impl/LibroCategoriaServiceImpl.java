package com.breaze.PrimeraApiCasa.services.impl;

import com.breaze.PrimeraApiCasa.entities.LibroCategoria;
import com.breaze.PrimeraApiCasa.entities.LibroCategoriaId;
import com.breaze.PrimeraApiCasa.repository.LibroCategoriaRepository;
import com.breaze.PrimeraApiCasa.services.ILibroCategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibroCategoriaServiceImpl implements ILibroCategoriaService {

    private final LibroCategoriaRepository libroCategoriaRepository;

    @Override
    public List<LibroCategoria> findByLibroId(Long libroId) {
        return libroCategoriaRepository.findByIdLibroId(libroId);
    }

    @Override
    public List<LibroCategoria> findByCategoriaId(Long categoriaId) {
        return libroCategoriaRepository.findByIdCategoriaId(categoriaId);
    }

    @Override
    public LibroCategoria save(LibroCategoria libroCategoria) {
        LibroCategoriaId id = new LibroCategoriaId(
                libroCategoria.getLibro().getId(),
                libroCategoria.getCategoria().getId()
        );
        libroCategoria.setId(id);
        return libroCategoriaRepository.save(libroCategoria);
    }

    @Override
    public boolean delete(LibroCategoriaId id) {
        if (!libroCategoriaRepository.existsById(id)) return false;
        libroCategoriaRepository.deleteById(id);
        return true;
    }
}
