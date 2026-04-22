package com.breaze.PrimeraApiCasa.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Manejador global de excepciones para toda la aplicación.
 *
 * {@code @RestControllerAdvice} intercepta las excepciones lanzadas en cualquier
 * controlador y las convierte en respuestas HTTP estandarizadas, evitando que
 * Spring devuelva páginas de error por defecto o stack traces al cliente.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Captura {@link AccessDeniedException}, que Spring Security lanza cuando
     * un usuario autenticado intenta acceder a un recurso para el que no tiene
     * el rol requerido (falla un {@code @PreAuthorize}).
     *
     * @return 403 Forbidden sin cuerpo
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Void> handleAccessDenied() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    /**
     * Captura {@link AuthenticationException}, que Spring Security lanza cuando
     * las credenciales son inválidas o el token JWT está ausente/expirado.
     *
     * @return 401 Unauthorized sin cuerpo
     */
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Void> handleUnauthorized() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
