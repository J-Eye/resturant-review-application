package com.irby.jaden.resturantreview.domain.core.review.service;

import com.irby.jaden.resturantreview.domain.core.exceptions.ResturantException;
import com.irby.jaden.resturantreview.domain.core.exceptions.ReviewExecption;
import com.irby.jaden.resturantreview.domain.core.exceptions.UserExecption;
import com.irby.jaden.resturantreview.domain.core.review.model.Review;

public interface ReviewService {

    Review CreateReview(Review review);
    Review UpdateReview(Long reviewId, Review review) throws UserExecption, ResturantException, ReviewExecption;
    Boolean deleteReview(Long id) throws ResturantException, ReviewExecption;
}
