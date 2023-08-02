package com.irby.jaden.resturantreview.domain.core.review.service;

import com.irby.jaden.resturantreview.domain.core.exceptions.BadRequestException;
import com.irby.jaden.resturantreview.domain.core.exceptions.ReviewNotFoundExecption;
import com.irby.jaden.resturantreview.domain.core.review.model.Review;
import com.irby.jaden.resturantreview.domain.core.review.repo.ReviewRepo;
import com.irby.jaden.resturantreview.domain.core.user.model.User;
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
    public Review createReview(Review review) throws BadRequestException {
        validateReview(review);
        Review createdReview = reviewRepo.save(review);
        return createdReview;
    }

    @Override
    @Transactional
    public Review updateReview(Long reviewId, Review review) throws ReviewNotFoundExecption, BadRequestException {
        validateReview(review);
        Review reviewUpdate = findReview(reviewId);

        reviewUpdate.setRating(review.getRating());
        reviewUpdate.setContent(review.getContent());
        reviewUpdate.setTitle(review.getTitle());


        Review savedReview = reviewRepo.save(reviewUpdate);

        return savedReview;

    }

    @Override
    @Transactional
    public Boolean deleteReview(Long id) throws ReviewNotFoundExecption {
        Review review = findReview(id);
        reviewRepo.delete(review);
        return true;

    }

    @Override
    public Review getReviewById(Long id) throws ReviewNotFoundExecption {
        Review review = findReview(id);
        return review;
    }


    private Review findReview(Long id) throws ReviewNotFoundExecption {
        Optional<Review> reviewOptional = reviewRepo.findById(id);
        if(reviewOptional.isEmpty()){
            log.error("Review with id {} does not exist", id);
            throw new ReviewNotFoundExecption("Review not found");
        };
        return reviewOptional.get();
    }

    private void validateReview(Review review) throws BadRequestException {
        if(review.getUser()== null ||
                review.getRating() == -1 ||
                review.getTitle()== null ||
                review.getContent() == null){
            throw new BadRequestException("Review is missing required fields:"+ review);
        }
    }
}
