package com.irby.jaden.resturantreview.domain.core.review.service;

import com.irby.jaden.resturantreview.domain.core.exceptions.ResturantException;
import com.irby.jaden.resturantreview.domain.core.exceptions.ReviewExecption;
import com.irby.jaden.resturantreview.domain.core.exceptions.UserExecption;
import com.irby.jaden.resturantreview.domain.core.review.model.Review;
import com.irby.jaden.resturantreview.domain.core.review.repo.ReviewRepo;

public interface ReviewService {

    Review CreateReview(Integer userId, Integer restaurantId, Review review) throws UserExecption, ResturantException;
    Review UpdateReview(Integer reviewId, Review review) throws UserExecption, ResturantException, ReviewExecption;
    Boolean deleteReview(Integer id) throws ResturantException, ReviewExecption;
}
