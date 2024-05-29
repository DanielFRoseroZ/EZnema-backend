package com.eznema.vb_test.movie.controller;

import com.eznema.vb_test.movie.model.Movie;
import com.eznema.vb_test.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MovieController {

    @Autowired
    MovieService movieService;

    @GetMapping("/movies")
    @ResponseBody
    public List<Movie> getMovies() {
        return movieService.getAllMovies();
    }

    @PostMapping("/create_movie")
    @ResponseBody
    public Movie createMovie(@RequestBody Movie movie) {
        return movieService.createMovie(movie);
    }

    @GetMapping("/movie/{id}")
    @ResponseBody
    public Optional<Movie> getMovie(@PathVariable int id) {
        return movieService.getMovieById(id);
    }

    @GetMapping("/movie/{title}")
    public ResponseEntity<Movie> getMovieByTitle(@PathVariable String title) {
        Movie movie = movieService.findMovieByTitle(title);

        if (movie == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(movie);
    }
}
