package com.breaze.PrimeraApiCasa.controllers;

import com.breaze.PrimeraApiCasa.dto.LibroCreateRequest;
import com.breaze.PrimeraApiCasa.dto.LibroResponse;
import com.breaze.PrimeraApiCasa.dto.LibroUpdateRequest;
import com.breaze.PrimeraApiCasa.entities.Autor;
import com.breaze.PrimeraApiCasa.entities.Libro;
import com.breaze.PrimeraApiCasa.services.ILibroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libros")
@RequiredArgsConstructor
public class LibroController {

    private final ILibroService libroService;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public ResponseEntity<List<LibroResponse>> findAll() {
        List<LibroResponse> response = libroService.findAll().stream()
                .map(this::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<LibroResponse> findById(@PathVariable Long id) {
        return libroService.findById(id)
                .map(this::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/autor/{autorId}")
    public ResponseEntity<List<LibroResponse>> findByAutorId(@PathVariable Long autorId) {
        List<LibroResponse> response = libroService.findByAutorId(autorId).stream()
                .map(this::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<LibroResponse> save(@RequestBody LibroCreateRequest request) {
        Autor autor = new Autor();
        autor.setId(request.getAutorId());

        Libro libro = new Libro();
        libro.setTitulo(request.getTitulo());
        libro.setAnioPublicacion(request.getAnioPublicacion());
        libro.setAutor(autor);

        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(libroService.save(libro)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<LibroResponse> update(@PathVariable Long id, @RequestBody LibroUpdateRequest request) {
        Autor autor = new Autor();
        autor.setId(request.getAutorId());

        Libro libro = new Libro();
        libro.setTitulo(request.getTitulo());
        libro.setAnioPublicacion(request.getAnioPublicacion());
        libro.setAutor(autor);

        return libroService.update(id, libro)
                .map(this::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return libroService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    private LibroResponse toResponse(Libro libro) {
        LibroResponse response = new LibroResponse();
        response.setId(libro.getId());
        response.setTitulo(libro.getTitulo());
        response.setAnioPublicacion(libro.getAnioPublicacion());
        response.setCreatedAt(libro.getCreatedAt());
        response.setUpdatedAt(libro.getUpdatedAt());
        if (libro.getAutor() != null) {
            response.setAutorId(libro.getAutor().getId());
            response.setAutorNombre(libro.getAutor().getNombre());
        }
        return response;
    }
}
