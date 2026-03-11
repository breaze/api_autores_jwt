package com.breaze.PrimeraApiCasa.services.impl;

import com.breaze.PrimeraApiCasa.entities.Autor;
import com.breaze.PrimeraApiCasa.repository.AutorRepository;
import com.breaze.PrimeraApiCasa.services.IAutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AutorServiceImpl implements IAutorService {

    private final AutorRepository autorRepository;

    @Override
    public List<Autor> findAll() {
        return autorRepository.findAll();
    }

    @Override
    public Optional<Autor> findById(Long id) {
        return autorRepository.findById(id);
    }

    @Override
    public Autor save(Autor autor) {
        return autorRepository.save(autor);
    }

    @Override
    public Optional<Autor> update(Long id, Autor autor) {
        return autorRepository.findById(id).map(existing -> {
            existing.setNombre(autor.getNombre());
            existing.setNacionalidad(autor.getNacionalidad());
            return autorRepository.save(existing);
        });
    }

    @Override
    public boolean delete(Long id) {
        if (!autorRepository.existsById(id)) return false;
        autorRepository.deleteById(id);
        return true;
    }
}
