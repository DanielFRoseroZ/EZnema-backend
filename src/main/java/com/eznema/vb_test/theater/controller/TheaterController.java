package com.eznema.vb_test.theater.controller;

import com.eznema.vb_test.theater.model.Theater;
import com.eznema.vb_test.theater.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class TheaterController {

    @Autowired
    private TheaterService theaterService;

    @GetMapping("/theaters")
    @ResponseBody
    public List<Theater> getAllTheaters() {
        return theaterService.getAllTheaters();
    }

    @GetMapping("/theater/{id}")
    @ResponseBody
    public Optional<Theater> getTheaterById(@PathVariable int id) {
        return theaterService.getTheaterById(id);
    }

}
