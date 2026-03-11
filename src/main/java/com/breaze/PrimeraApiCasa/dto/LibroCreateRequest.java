package com.breaze.PrimeraApiCasa.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LibroCreateRequest {

    private String titulo;
    private Integer anioPublicacion;
    private Long autorId;
}
