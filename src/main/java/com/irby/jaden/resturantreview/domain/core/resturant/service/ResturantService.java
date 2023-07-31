package com.irby.jaden.resturantreview.domain.core.resturant.service;

import com.irby.jaden.resturantreview.domain.core.exceptions.ResturantException;
import com.irby.jaden.resturantreview.domain.core.resturant.model.Restaurant;
import com.irby.jaden.resturantreview.domain.core.review.model.Review;

import java.util.List;

public interface ResturantService {
    List<Restaurant> getAllResturants();
    List<Review> getAllReviews(Integer id) throws ResturantException;
    Restaurant CreateRestaurant(Restaurant Restaurant);
    Restaurant UpdateRestaurant(Integer id, Restaurant Restaurant) throws ResturantException;
    Boolean deleteRestaurant(Integer id) throws ResturantException;
}
