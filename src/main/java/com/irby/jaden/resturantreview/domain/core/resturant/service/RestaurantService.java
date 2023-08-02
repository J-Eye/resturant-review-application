package com.irby.jaden.resturantreview.domain.core.resturant.service;

import com.irby.jaden.resturantreview.domain.core.exceptions.BadRequestException;
import com.irby.jaden.resturantreview.domain.core.exceptions.ResturantNotFoundException;
import com.irby.jaden.resturantreview.domain.core.resturant.model.Restaurant;

import java.util.List;

public interface RestaurantService {
    List<Restaurant> getAllRestaurants();
    Restaurant createRestaurant(Restaurant Restaurant) throws BadRequestException;

    Restaurant updateRestaurant(Long id, Restaurant restaurant) throws ResturantNotFoundException, BadRequestException;

    Boolean deleteRestaurant(Long id) throws ResturantNotFoundException;

    Restaurant getRestaurantById(Long id) throws ResturantNotFoundException;
}
