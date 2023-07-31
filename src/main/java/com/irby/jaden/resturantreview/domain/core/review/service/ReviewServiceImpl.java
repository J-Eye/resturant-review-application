package com.irby.jaden.resturantreview.domain.core.review.service;

import com.irby.jaden.resturantreview.domain.core.exceptions.ResturantException;
import com.irby.jaden.resturantreview.domain.core.exceptions.ReviewExecption;
import com.irby.jaden.resturantreview.domain.core.exceptions.UserExecption;
import com.irby.jaden.resturantreview.domain.core.resturant.model.Restaurant;
import com.irby.jaden.resturantreview.domain.core.resturant.repo.ResturantRepo;
import com.irby.jaden.resturantreview.domain.core.review.model.Review;
import com.irby.jaden.resturantreview.domain.core.review.repo.ReviewRepo;
import com.irby.jaden.resturantreview.domain.core.user.model.UserEntity;
import com.irby.jaden.resturantreview.domain.core.user.repo.UserRepo;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ReviewServiceImpl implements ReviewService{

    private ReviewRepo reviewRepo;
    private ResturantRepo resturantRepo;
    private UserRepo userRepo;

    @Autowired
    public ReviewServiceImpl(ReviewRepo reviewRepo, ResturantRepo resturantRepo, UserRepo userRepo) {
        this.reviewRepo = reviewRepo;
        this.resturantRepo = resturantRepo;
        this.userRepo = userRepo;
    }

    @Override
    @Transactional
    public Review CreateReview(Integer userId, Integer restaurantId, Review review) throws UserExecption, ResturantException {
        Optional<UserEntity> userOptional = userRepo.findById(userId);
        Optional<Restaurant> restaurantOptional = resturantRepo.findById(restaurantId);

        if(userOptional.isEmpty()){
            log.error("User with id {} does not exist", userId);
            throw new UserExecption("User not found");
        }

        if(restaurantOptional.isEmpty()){
            log.error("Restaurant with id {} does not exist", restaurantId);
            throw new ResturantException("Restaurant not found");
        }
        UserEntity user = userOptional.get();
        Restaurant restaurant = restaurantOptional.get();

        review.setUserId(user.getId());
        review.setRestaurnatId(restaurant.getId());

        user.getReviews().add(review);
        restaurant.getReviews().add(review);

        userRepo.save(user);
        resturantRepo.save(restaurant);

        Review createdReview = reviewRepo.save(review);

        return createdReview;
    }

    @Override
    @Transactional
    public Review UpdateReview(Integer reviewId, Review review) throws ReviewExecption {
        Review reviewUpate = findReview(reviewId);

        reviewUpate.setRating(review.getRating());
        reviewUpate.setContent(review.getContent());
        reviewUpate.setTitle(review.getTitle());

        Review savedReview = reviewRepo.save(reviewUpate);

        return savedReview;

    }

    @Override
    @Transactional
    public Boolean deleteReview(Integer id) throws ReviewExecption {
        Review review = findReview(id);
        reviewRepo.delete(review);
        return true;

    }

    private Review findReview(Integer id) throws ReviewExecption {
        Optional<Review> reviewOptional = reviewRepo.findById(id);
        if(reviewOptional.isEmpty()){
            log.error("Review with id {} does not exist", id);
            throw new ReviewExecption("Review not found");
        };
        return reviewOptional.get();
    }
}
