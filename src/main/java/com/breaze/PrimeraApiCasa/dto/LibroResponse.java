package com.breaze.PrimeraApiCasa.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class LibroResponse {

    private Long id;
    private String titulo;
    private Integer anioPublicacion;
    private Long autorId;
    private String autorNombre;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
