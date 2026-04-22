package com.breaze.PrimeraApiCasa.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "autor")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "libros")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name="nacionalidad", length = 50)
    private String nacionalidad;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "autor")
    private List<Libro> libros;


}
