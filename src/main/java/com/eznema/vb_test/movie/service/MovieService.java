package com.eznema.vb_test.movie.service;

import com.eznema.vb_test.movie.model.Movie;
import com.eznema.vb_test.movie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

  public Optional<Movie> getMovieById(int id) {
        return movieRepository.findById(id);
  }

  public Movie findMovieByTitle(String title) {
        return movieRepository.findByTitle(title);
  }

}
