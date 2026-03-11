package com.breaze.PrimeraApiCasa.controllers;

import com.breaze.PrimeraApiCasa.dto.LibroCategoriaCreateRequest;
import com.breaze.PrimeraApiCasa.dto.LibroCategoriaResponse;
import com.breaze.PrimeraApiCasa.entities.Categoria;
import com.breaze.PrimeraApiCasa.entities.Libro;
import com.breaze.PrimeraApiCasa.entities.LibroCategoria;
import com.breaze.PrimeraApiCasa.entities.LibroCategoriaId;
import com.breaze.PrimeraApiCasa.services.ILibroCategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libro-categorias")
@RequiredArgsConstructor
public class LibroCategoriaController {

    private final ILibroCategoriaService libroCategoriaService;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/libro/{libroId}")
    public ResponseEntity<List<LibroCategoriaResponse>> findByLibroId(@PathVariable Long libroId) {
        List<LibroCategoriaResponse> response = libroCategoriaService.findByLibroId(libroId).stream()
                .map(this::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<LibroCategoriaResponse>> findByCategoriaId(@PathVariable Long categoriaId) {
        List<LibroCategoriaResponse> response = libroCategoriaService.findByCategoriaId(categoriaId).stream()
                .map(this::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<LibroCategoriaResponse> save(@RequestBody LibroCategoriaCreateRequest request) {
        Libro libro = new Libro();
        libro.setId(request.getLibroId());

        Categoria categoria = new Categoria();
        categoria.setId(request.getCategoriaId());

        LibroCategoria libroCategoria = new LibroCategoria();
        libroCategoria.setLibro(libro);
        libroCategoria.setCategoria(categoria);
        libroCategoria.setPrioridad(request.getPrioridad());
        libroCategoria.setComentario(request.getComentario());

        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(libroCategoriaService.save(libroCategoria)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{libroId}/{categoriaId}")
    public ResponseEntity<Void> delete(@PathVariable Long libroId, @PathVariable Long categoriaId) {
        LibroCategoriaId id = new LibroCategoriaId(libroId, categoriaId);
        return libroCategoriaService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    private LibroCategoriaResponse toResponse(LibroCategoria lc) {
        LibroCategoriaResponse response = new LibroCategoriaResponse();
        response.setPrioridad(lc.getPrioridad());
        response.setAddedAt(lc.getAddedAt());
        response.setComentario(lc.getComentario());
        if (lc.getId() != null) {
            response.setLibroId(lc.getId().getLibroId());
            response.setCategoriaId(lc.getId().getCategoriaId());
        }
        if (lc.getLibro() != null) {
            response.setLibroTitulo(lc.getLibro().getTitulo());
        }
        if (lc.getCategoria() != null) {
            response.setCategoriaNombre(lc.getCategoria().getNombre());
        }
        return response;
    }
}
