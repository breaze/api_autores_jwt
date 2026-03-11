package com.breaze.PrimeraApiCasa.controllers;

import com.breaze.PrimeraApiCasa.dto.AutorCreateRequest;
import com.breaze.PrimeraApiCasa.dto.AutorResponse;
import com.breaze.PrimeraApiCasa.dto.AutorUpdateRequest;
import com.breaze.PrimeraApiCasa.entities.Autor;
import com.breaze.PrimeraApiCasa.services.IAutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autores")
@RequiredArgsConstructor
public class AutorController {

    private final IAutorService autorService;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public ResponseEntity<List<AutorResponse>> findAll() {
        List<AutorResponse> response = autorService.findAll().stream()
                .map(this::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<AutorResponse> findById(@PathVariable Long id) {
        return autorService.findById(id)
                .map(this::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<AutorResponse> save(@RequestBody AutorCreateRequest request) {
        Autor autor = new Autor();
        autor.setNombre(request.getNombre());
        autor.setNacionalidad(request.getNacionalidad());

        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(autorService.save(autor)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<AutorResponse> update(@PathVariable Long id, @RequestBody AutorUpdateRequest request) {
        Autor autor = new Autor();
        autor.setNombre(request.getNombre());
        autor.setNacionalidad(request.getNacionalidad());

        return autorService.update(id, autor)
                .map(this::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return autorService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    private AutorResponse toResponse(Autor autor) {
        AutorResponse response = new AutorResponse();
        response.setId(autor.getId());
        response.setNombre(autor.getNombre());
        response.setNacionalidad(autor.getNacionalidad());
        response.setCreatedAt(autor.getCreatedAt());
        response.setUpdatedAt(autor.getUpdatedAt());
        return response;
    }
}
