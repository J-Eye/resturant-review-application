package com.irby.jaden.resturantreview.domain.core.review.repo;

import com.irby.jaden.resturantreview.domain.core.review.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Long> {
}
