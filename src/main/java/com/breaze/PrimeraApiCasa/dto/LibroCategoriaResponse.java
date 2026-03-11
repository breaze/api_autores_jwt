package com.breaze.PrimeraApiCasa.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class LibroCategoriaResponse {

    private Long libroId;
    private String libroTitulo;
    private Long categoriaId;
    private String categoriaNombre;
    private Integer prioridad;
    private LocalDateTime addedAt;
    private String comentario;
}
