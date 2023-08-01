package com.irby.jaden.resturantreview.domain.core.resturant.service;

import com.irby.jaden.resturantreview.domain.core.exceptions.ResturantException;
import com.irby.jaden.resturantreview.domain.core.resturant.model.Restaurant;
import com.irby.jaden.resturantreview.domain.core.resturant.repo.ResturantRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RestaurantServiceImpl implements RestaurantService {

    private ResturantRepo resturantRepo;

    @Autowired
    public RestaurantServiceImpl(ResturantRepo resturantRepo) {
        this.resturantRepo = resturantRepo;
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return resturantRepo.findAll();
    }


    @Override
    public Restaurant createRestaurant(Restaurant restaurant) {
        Restaurant createdRestruant = resturantRepo.save(restaurant);
        return createdRestruant;
    }

    @Override
    public Restaurant updateRestaurant(Long id, Restaurant restaurant) throws ResturantException {
        Restaurant restaurantUpdate = findRestaurant(id);
        restaurantUpdate.setAddress(restaurant.getAddress());
        restaurantUpdate.setName(restaurant.getName());
        System.out.println("SPRINGGGGGGG");
        return resturantRepo.save(restaurantUpdate);
    }

    @Override
    public Boolean deleteRestaurant(Long id) throws ResturantException {
        Restaurant restaurant = findRestaurant(id);
        resturantRepo.delete(restaurant);
        return true;

    }

    @Override
    public Restaurant getRestaurantById(Long id) throws ResturantException {
        Restaurant restaurant = findRestaurant(id);
        return restaurant;
    }

    private Restaurant findRestaurant(Long id) throws ResturantException {
        Optional<Restaurant> restaurantOptional = resturantRepo.findById(id);
        if(restaurantOptional.isEmpty()){
            log.error("Review with id {} does not exist", id);
            throw new ResturantException("Review not found");
        }
        return restaurantOptional.get();
    }
}
