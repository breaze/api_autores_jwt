package com.breaze.PrimeraApiCasa.controllers;

import com.breaze.PrimeraApiCasa.dto.CategoriaCreateRequest;
import com.breaze.PrimeraApiCasa.dto.CategoriaResponse;
import com.breaze.PrimeraApiCasa.dto.CategoriaUpdateRequest;
import com.breaze.PrimeraApiCasa.entities.Categoria;
import com.breaze.PrimeraApiCasa.services.ICategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final ICategoriaService categoriaService;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public ResponseEntity<List<CategoriaResponse>> findAll() {
        List<CategoriaResponse> response = categoriaService.findAll().stream()
                .map(this::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponse> findById(@PathVariable Long id) {
        return categoriaService.findById(id)
                .map(this::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CategoriaResponse> save(@RequestBody CategoriaCreateRequest request) {
        Categoria categoria = new Categoria();
        categoria.setNombre(request.getNombre());

        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(categoriaService.save(categoria)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponse> update(@PathVariable Long id, @RequestBody CategoriaUpdateRequest request) {
        Categoria categoria = new Categoria();
        categoria.setNombre(request.getNombre());

        return categoriaService.update(id, categoria)
                .map(this::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return categoriaService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    private CategoriaResponse toResponse(Categoria categoria) {
        CategoriaResponse response = new CategoriaResponse();
        response.setId(categoria.getId());
        response.setNombre(categoria.getNombre());
        return response;
    }
}
