package com.breaze.PrimeraApiCasa.entities;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode
public class LibroCategoriaId implements Serializable {

    private Long libroId;
    private Long categoriaId;
}
