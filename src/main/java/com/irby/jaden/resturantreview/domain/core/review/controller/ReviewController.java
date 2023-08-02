package com.irby.jaden.resturantreview.domain.core.review.controller;

import com.irby.jaden.resturantreview.domain.core.BaseController;
import com.irby.jaden.resturantreview.domain.core.exceptions.BadRequestException;
import com.irby.jaden.resturantreview.domain.core.exceptions.ResturantNotFoundException;
import com.irby.jaden.resturantreview.domain.core.exceptions.ReviewNotFoundExecption;
import com.irby.jaden.resturantreview.domain.core.exceptions.UserNotFoundException;
import com.irby.jaden.resturantreview.domain.core.review.model.Review;
import com.irby.jaden.resturantreview.domain.core.review.service.ReviewService;
import com.irby.jaden.resturantreview.domain.core.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
@CrossOrigin("*")
public class ReviewController extends BaseController {

    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    @PostMapping("")
    public ResponseEntity<Review> createReview(@RequestBody @NonNull Review review) throws BadRequestException {
        Review createdReview  = reviewService.createReview(review);
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Review> updateReview(@RequestBody @NonNull Review review, @PathVariable String id) throws BadRequestException, ReviewNotFoundExecption {
        long reviewId = validateId(id);
        Review updateReview = reviewService.updateReview(reviewId, review);
        return new ResponseEntity<>(updateReview, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteReview(@PathVariable String id) throws BadRequestException, ReviewNotFoundExecption {
        long reviewId = validateId(id);
        Boolean result = reviewService.deleteReview(reviewId);
        return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
    }





}
