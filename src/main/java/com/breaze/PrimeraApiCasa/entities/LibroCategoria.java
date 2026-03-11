package com.breaze.PrimeraApiCasa.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "libro_categoria")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"libro", "categoria"})
public class LibroCategoria {

    @EmbeddedId
    private LibroCategoriaId id;
    @Column(name = "prioridad")
    private Integer prioridad;

    @Column(name = "added_at", insertable = false, updatable = false)
    private LocalDateTime addedAt;

    @Column(length = 255)
    private String comentario;

    @ManyToOne
    @MapsId("libroId")
    @JoinColumn(name = "libro_id")
    private Libro libro;

    @ManyToOne
    @MapsId("categoriaId")
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
}
