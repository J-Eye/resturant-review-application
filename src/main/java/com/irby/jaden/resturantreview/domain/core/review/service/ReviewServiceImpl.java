package com.irby.jaden.resturantreview.domain.core.review.service;

import com.irby.jaden.resturantreview.domain.core.exceptions.ReviewExecption;
import com.irby.jaden.resturantreview.domain.core.review.model.Review;
import com.irby.jaden.resturantreview.domain.core.review.repo.ReviewRepo;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class ReviewServiceImpl implements ReviewService{

    private ReviewRepo reviewRepo;

    @Autowired
    public ReviewServiceImpl(ReviewRepo reviewRepo) {
        this.reviewRepo = reviewRepo;
    }

    @Override
    public Review CreateReview(Review review){
        Review createdReview = reviewRepo.save(review);
        return createdReview;
    }

    @Override
    @Transactional
    public Review UpdateReview(Long reviewId, Review review) throws ReviewExecption {
        Review reviewUpate = findReview(reviewId);

        reviewUpate.setRating(review.getRating());
        reviewUpate.setContent(review.getContent());
        reviewUpate.setTitle(review.getTitle());


        Review savedReview = reviewRepo.save(reviewUpate);

        return savedReview;

    }

    @Override
    @Transactional
    public Boolean deleteReview(Long id) throws ReviewExecption {
        Review review = findReview(id);
        reviewRepo.delete(review);
        return true;

    }


    private Review findReview(Long id) throws ReviewExecption {
        Optional<Review> reviewOptional = reviewRepo.findById(id);
        if(reviewOptional.isEmpty()){
            log.error("Review with id {} does not exist", id);
            throw new ReviewExecption("Review not found");
        };
        return reviewOptional.get();
    }
}
