package com.breaze.PrimeraApiCasa.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LibroCategoriaCreateRequest {

    private Long libroId;
    private Long categoriaId;
    private Integer prioridad;
    private String comentario;
}
