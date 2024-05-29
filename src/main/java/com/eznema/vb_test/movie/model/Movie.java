package com.eznema.vb_test.movie.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@Entity
@Table(name = "movie", uniqueConstraints = {@UniqueConstraint(columnNames = "title")})
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "original_title")
    private String originalTitle;

    @ManyToMany
    @JoinTable(
            name = "categorie_movie",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "categorie_id")
    )
    private List<Categorie> categories;

    @Column(name = "release_date")
    private String releaseDate;

    @Column(name = "age_restriction")
    private String ageRestriction;

    @Column(name = "duration")
    private String duration;

    @Column(name = "director")
    private String director;

    @Column(name = "cast")
    private String cast;

    @Column(name = "points")
    private String points;

    @Column(name = "synopsis")
    private String synopsis;

    @Column(name = "lenguage")
    private String language;

    @Column(name = "poster_url")
    private String posterUrl;

    @Column(name = "trailer_url")
    private String trailerUrl;

    @Column(name = "created_at")
    private String createdAt;
}
