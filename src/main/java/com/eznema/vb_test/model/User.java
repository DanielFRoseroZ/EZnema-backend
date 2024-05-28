package com.eznema.vb_test.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Entidad User: Representa una entidad de usuario en la aplicación.
 *
 * <p>La entidad Usuario almacena la información básica de un usuario
 * como el identificador, nombre y correo electrónico.
 * </p>
 *
 * <Esta clase está mapeada a una tabla en la base de datos usando JPA.
 */

@Entity
@Table(name = "user")
public class User implements UserDetails {

    /**
     * Identificador único del usuario.
     * Se genera automáticamente
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;


    /**
     * Nombre del usuario
     * */
    @Column(name = "first_name")
    private String firstName;


    /**
     * Apellido del usuario
     * */
    @Column(name = "last_name")
    private String lastName;


    /**
     * Telefono del usuario
     * */
    @Column(name = "phone")
    private String phone;


    /**
     * Correo electrónico del usuario
     * */
    @Column(name = "username")
    private String username;


    /**
     * Contraseña del usuario
     * */
    @Column(name = "password")
    private String password;


    /**
     * Rol del usuario ->
     * <p>
     *    Solo puede ser USUARIO o ADMIN (Definidos en la clase Role)
     * <p>
     * */
    @Enumerated(value = EnumType.STRING)
    private Role role;


    /**
     * -----------------------------------------------------------------
     * <h1>
     *     MÉTODOS GET AND SET -> GENERADOS POR SPRING
     * </h1>
     * -----------------------------------------------------------------
     * */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    /**
     * -----------------------------------------------------------------
     * <h1>
     *     MÉTODOS NECESARIOS PARA LA IMPLEMENTACIÓN DE UserDetails
     * </h1>
     * -----------------------------------------------------------------
     * */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
}
