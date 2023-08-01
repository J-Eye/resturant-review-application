package com.irby.jaden.resturantreview.domain.core.resturant.repo;

import com.irby.jaden.resturantreview.domain.core.resturant.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResturantRepo extends JpaRepository<Restaurant, Long> {
}
