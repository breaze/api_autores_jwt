package com.breaze.PrimeraApiCasa.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class AutorResponse {

    private Long id;
    private String nombre;
    private String nacionalidad;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
