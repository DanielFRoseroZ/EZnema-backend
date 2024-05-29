package com.eznema.vb_test.theater.service;

import com.eznema.vb_test.theater.model.TheaterType;
import com.eznema.vb_test.theater.repository.TheaterTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TheaterTypeService {
    @Autowired
    private TheaterTypeRepository theaterTypeRepository;

    public List<TheaterType> getAllTheaterTypes() {
        return theaterTypeRepository.findAll();
    }

    public Optional<TheaterType> getTheaterTypeById(int theaterTypeId) {
        if(theaterTypeRepository.existsById(theaterTypeId)) {
            return theaterTypeRepository.findById(theaterTypeId);
        }
        return Optional.empty();
    }

    public TheaterType saveTheaterType(TheaterType theaterType) {
        return theaterTypeRepository.save(theaterType);
    }
}
