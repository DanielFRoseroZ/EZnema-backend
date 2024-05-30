package com.eznema.vb_test.movie.controller;

import com.eznema.vb_test.movie.model.Movie;
import com.eznema.vb_test.movie.service.MovieService;
import com.eznema.vb_test.movie.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
public class MovieController {

    @Autowired
    MovieService movieService;

    @Value("${aws.s3.bucket}")
    private String bucketName;
    @Autowired
    private S3Service s3Service;


    @GetMapping("/movies")
    @ResponseBody
    public List<Movie> getMovies() {
        return movieService.getAllMovies();
    }

    @PostMapping(path="/create_movie", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseBody
    public Movie createMovie(@RequestParam("file") MultipartFile file,
                             @RequestParam("title") String title,
                             @RequestParam("originalTitle") String originalTitle,
                             @RequestParam("releaseDate") String releaseDate,
                             @RequestParam("ageRestriction") int ageRestriction,
                             @RequestParam("duration") String duration,
                             @RequestParam("director") String director,
                             @RequestParam("cast") String cast,
                             @RequestParam("points") String points,
                             @RequestParam("synopsis") String synopsis,
                             @RequestParam("language") String language,
                             @RequestParam("trailerUrl") String trailerUrl) {
        try {
            String imageUrl = s3Service.uploadFile(file,bucketName);

            Movie movie = new Movie();
            movie.setTitle(title);
            movie.setOriginalTitle(originalTitle);
            movie.setReleaseDate(releaseDate);
            movie.setAgeRestriction(ageRestriction);
            movie.setDuration(duration);
            movie.setDirector(director);
            movie.setCast(cast);
            movie.setPoints(points);
            movie.setSynopsis(synopsis);
            movie.setLanguage(language);
            movie.setPosterUrl(imageUrl);
            movie.setTrailerUrl(trailerUrl);
            movie.setCreatedAt(String.valueOf(System.currentTimeMillis()));

            Movie savedMovie = movieService.createMovie(movie);

            return ResponseEntity.ok(savedMovie).getBody();

        } catch (IOException e) {
            return (Movie) ResponseEntity.status(500);
        }
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
