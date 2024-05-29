package com.eznema.vb_test.movie.controller;

import com.eznema.vb_test.movie.model.Categorie;
import com.eznema.vb_test.movie.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CategorieController {

    @Autowired
    CategorieService categorieService;

    @GetMapping("/categories")
    @ResponseBody
    public List<Categorie> listCategories() {
        return categorieService.getAllCategories();
    }

    @PostMapping("/create_categorie")
    @ResponseBody
    public Categorie addCategorie(@RequestBody Categorie categorie) {
        return categorieService.createCategorie(categorie);
    }

    @GetMapping("/categorie/{id}")
    @ResponseBody
    public Optional<Categorie> getCategorie(@PathVariable int id) {
        return categorieService.getCategorieByID(id);
    }

}
