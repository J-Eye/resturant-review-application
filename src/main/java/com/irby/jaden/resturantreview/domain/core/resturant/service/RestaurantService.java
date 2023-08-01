package com.irby.jaden.resturantreview.domain.core.resturant.service;

import com.irby.jaden.resturantreview.domain.core.exceptions.ResturantException;
import com.irby.jaden.resturantreview.domain.core.resturant.model.Restaurant;

import java.util.List;

public interface RestaurantService {
    List<Restaurant> getAllRestaurants();
    Restaurant createRestaurant(Restaurant Restaurant);

    Restaurant updateRestaurant(Long id, Restaurant restaurant) throws ResturantException;

    Boolean deleteRestaurant(Long id) throws ResturantException;

    Restaurant getRestaurantById(Long id) throws ResturantException;
}
