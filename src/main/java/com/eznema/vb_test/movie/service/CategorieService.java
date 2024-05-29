package com.eznema.vb_test.movie.service;

import com.eznema.vb_test.movie.model.Categorie;
import com.eznema.vb_test.movie.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategorieService {

    @Autowired
    CategorieRepository categorieRepository;

    public Categorie createCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }

    public Optional<Categorie> getCategorieByID(int id) {
        if (categorieRepository.existsById(id)) {
            return categorieRepository.findById(id);
        } else  {
            return Optional.empty();
        }
    }

}
