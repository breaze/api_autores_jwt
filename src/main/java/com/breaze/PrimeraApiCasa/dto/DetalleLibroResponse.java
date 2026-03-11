package com.breaze.PrimeraApiCasa.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DetalleLibroResponse {

    private Long id;
    private String isbn;
    private Integer numPaginas;
    private String idioma;
    private Long libroId;
}
