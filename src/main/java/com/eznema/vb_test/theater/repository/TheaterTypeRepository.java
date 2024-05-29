package com.eznema.vb_test.theater.repository;

import com.eznema.vb_test.theater.model.TheaterType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterTypeRepository extends JpaRepository<TheaterType, Integer> {
}
