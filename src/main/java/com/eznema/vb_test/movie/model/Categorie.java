package com.eznema.vb_test.movie.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Entidad Categorie: Representa una catgoria de peliculas en la aplicación.
 * */

@Getter @Setter
@Entity
@Table(name = "categories", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class Categorie {

    //Identificador de la categoria de peliculas
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    //Nombre de la categoria de peliculas (Ejemplo: Comedia, Accion, Drama)
    @Column(name = "name", nullable = false)
    private String name;
}
