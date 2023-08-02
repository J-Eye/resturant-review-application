package com.irby.jaden.resturantreview.domain.core.review.service;

import com.irby.jaden.resturantreview.domain.core.exceptions.BadRequestException;
import com.irby.jaden.resturantreview.domain.core.exceptions.ResturantNotFoundException;
import com.irby.jaden.resturantreview.domain.core.exceptions.ReviewNotFoundExecption;
import com.irby.jaden.resturantreview.domain.core.exceptions.UserNotFoundException;
import com.irby.jaden.resturantreview.domain.core.review.model.Review;
import com.irby.jaden.resturantreview.domain.core.user.model.User;

import java.util.List;

public interface ReviewService {

    Review createReview(Review review) throws BadRequestException;
    Review updateReview(Long reviewId, Review review) throws ReviewNotFoundExecption, BadRequestException;
    Boolean deleteReview(Long id) throws ReviewNotFoundExecption;
}
