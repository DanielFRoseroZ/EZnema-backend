package com.eznema.vb_test.theater.service;

import com.eznema.vb_test.theater.model.Theater;
import com.eznema.vb_test.theater.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TheaterService {
    @Autowired
    private TheaterRepository theaterRepository;

    public List<Theater> getAllTheaters() {
        return theaterRepository.findAll();
    }

    public Optional<Theater> getTheaterById(int theaterId) {
        return theaterRepository.findById(theaterId);
    }

    public Theater createTheater(Theater theater) {
        return theaterRepository.save(theater);
    }
}
