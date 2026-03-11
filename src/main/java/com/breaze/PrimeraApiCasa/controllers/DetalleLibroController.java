package com.breaze.PrimeraApiCasa.controllers;

import com.breaze.PrimeraApiCasa.dto.DetalleLibroCreateRequest;
import com.breaze.PrimeraApiCasa.dto.DetalleLibroResponse;
import com.breaze.PrimeraApiCasa.dto.DetalleLibroUpdateRequest;
import com.breaze.PrimeraApiCasa.entities.DetalleLibro;
import com.breaze.PrimeraApiCasa.entities.Libro;
import com.breaze.PrimeraApiCasa.services.IDetalleLibroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/detalles")
@RequiredArgsConstructor
public class DetalleLibroController {

    private final IDetalleLibroService detalleLibroService;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<DetalleLibroResponse> findById(@PathVariable Long id) {
        return detalleLibroService.findById(id)
                .map(this::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/libro/{libroId}")
    public ResponseEntity<DetalleLibroResponse> findByLibroId(@PathVariable Long libroId) {
        return detalleLibroService.findByLibroId(libroId)
                .map(this::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<DetalleLibroResponse> save(@RequestBody DetalleLibroCreateRequest request) {
        Libro libro = new Libro();
        libro.setId(request.getLibroId());

        DetalleLibro detalle = new DetalleLibro();
        detalle.setIsbn(request.getIsbn());
        detalle.setNumPaginas(request.getNumPaginas());
        detalle.setIdioma(request.getIdioma());
        detalle.setLibro(libro);

        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(detalleLibroService.save(detalle)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<DetalleLibroResponse> update(@PathVariable Long id, @RequestBody DetalleLibroUpdateRequest request) {
        DetalleLibro detalle = new DetalleLibro();
        detalle.setIsbn(request.getIsbn());
        detalle.setNumPaginas(request.getNumPaginas());
        detalle.setIdioma(request.getIdioma());

        return detalleLibroService.update(id, detalle)
                .map(this::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return detalleLibroService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    private DetalleLibroResponse toResponse(DetalleLibro detalle) {
        DetalleLibroResponse response = new DetalleLibroResponse();
        response.setId(detalle.getId());
        response.setIsbn(detalle.getIsbn());
        response.setNumPaginas(detalle.getNumPaginas());
        response.setIdioma(detalle.getIdioma());
        if (detalle.getLibro() != null) {
            response.setLibroId(detalle.getLibro().getId());
        }
        return response;
    }
}
