package com.breaze.PrimeraApiCasa.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "categoria")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString(exclude = "librosCategorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String nombre;

    @OneToMany(mappedBy = "categoria")
    private List<LibroCategoria> librosCategorias;
}
