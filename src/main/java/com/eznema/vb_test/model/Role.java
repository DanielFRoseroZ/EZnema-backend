package com.eznema.vb_test.model;

/**
 * Se definen los únicos 2 roles que contempla la app ->
 * <p>
 *     USER: Necesario par comprar tickets.
 *     ADMIN: Solo este rol puede "crear" las peliculas
 * </p>
 * */

public enum Role {
    USER,
    ADMIN
}
