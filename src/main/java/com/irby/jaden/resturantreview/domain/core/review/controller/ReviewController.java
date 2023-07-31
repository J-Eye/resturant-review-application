package com.irby.jaden.resturantreview.domain.core.review.controller;

import com.irby.jaden.resturantreview.domain.core.exceptions.ResturantException;
import com.irby.jaden.resturantreview.domain.core.exceptions.UserExecption;
import com.irby.jaden.resturantreview.domain.core.review.model.Review;
import com.irby.jaden.resturantreview.domain.core.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
@CrossOrigin("*")
public class ReviewController {

    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    @PostMapping("")
    public ResponseEntity<Review> createReview(@RequestBody Review review, @RequestParam Integer userId, @RequestParam Integer restaurantId) throws UserExecption, ResturantException {
        Review createdReview  = reviewService.CreateReview(userId,restaurantId,review);
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }


}
