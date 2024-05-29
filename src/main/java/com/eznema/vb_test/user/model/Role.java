package com.eznema.vb_test.user.model;

/**
 * Se definen los únicos 2 roles que contempla la app ->
 *     - USER: Necesario par comprar tickets.
 *     - ADMIN: Solo este rol puede "crear" las peliculas
 * */

public enum Role {
    USER,
    ADMIN
}
