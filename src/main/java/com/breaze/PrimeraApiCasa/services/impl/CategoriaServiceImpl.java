package com.breaze.PrimeraApiCasa.services.impl;

import com.breaze.PrimeraApiCasa.entities.Categoria;
import com.breaze.PrimeraApiCasa.repository.CategoriaRepository;
import com.breaze.PrimeraApiCasa.services.ICategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements ICategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    @Override
    public Optional<Categoria> findById(Long id) {
        return categoriaRepository.findById(id);
    }

    @Override
    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public Optional<Categoria> update(Long id, Categoria categoria) {
        return categoriaRepository.findById(id).map(existing -> {
            existing.setNombre(categoria.getNombre());
            return categoriaRepository.save(existing);
        });
    }

    @Override
    public boolean delete(Long id) {
        if (!categoriaRepository.existsById(id)) return false;
        categoriaRepository.deleteById(id);
        return true;
    }
}
