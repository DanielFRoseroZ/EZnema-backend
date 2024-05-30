package com.eznema.vb_test.theater.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@Entity
@Table(name = "theater", uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(optional = false)
    private TheaterType theaterType;

}
