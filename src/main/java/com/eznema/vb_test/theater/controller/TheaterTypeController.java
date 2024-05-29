package com.eznema.vb_test.theater.controller;

import com.eznema.vb_test.theater.model.TheaterType;
import com.eznema.vb_test.theater.service.TheaterTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TheaterTypeController {

    @Autowired
    private TheaterTypeService theaterTypeService;

    @GetMapping("/theater_types")
    @ResponseBody
    public List<TheaterType> getAllTheaterTypes() {
        return theaterTypeService.getAllTheaterTypes();
    }

    @PostMapping("/create_th_type")
    @ResponseBody
    public TheaterType createTheaterType(@RequestBody TheaterType theaterType) {
        return theaterTypeService.saveTheaterType(theaterType);
    }
}
