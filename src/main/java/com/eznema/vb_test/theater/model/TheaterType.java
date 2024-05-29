package com.eznema.vb_test.theater.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "theater_type", uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
public class TheaterType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;
}
