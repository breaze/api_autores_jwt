package com.breaze.PrimeraApiCasa.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "detalle_libro")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString(exclude = "libro")
public class DetalleLibro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 20)
    private String isbn;

    @Column(name = "num_paginas")
    private Integer numPaginas;

    @Column(length = 50)
    private String idioma;

    @OneToOne
    @JoinColumn(name = "libro_id", nullable = false, unique = true)
    private Libro libro;
}
