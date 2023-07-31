package com.irby.jaden.resturantreview.domain.core.resturant.service;

import com.irby.jaden.resturantreview.domain.core.exceptions.ResturantException;
import com.irby.jaden.resturantreview.domain.core.exceptions.ReviewExecption;
import com.irby.jaden.resturantreview.domain.core.resturant.model.Restaurant;
import com.irby.jaden.resturantreview.domain.core.resturant.repo.ResturantRepo;
import com.irby.jaden.resturantreview.domain.core.review.model.Review;
import com.irby.jaden.resturantreview.domain.core.review.repo.ReviewRepo;
import com.irby.jaden.resturantreview.domain.core.user.repo.UserRepo;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ResturantServiceImpl implements ResturantService{

    private ResturantRepo resturantRepo;

    @Autowired
    public ResturantServiceImpl(ResturantRepo resturantRepo) {
        this.resturantRepo = resturantRepo;
    }

    @Override
    public List<Restaurant> getAllResturants() {
        return resturantRepo.findAll();
    }

    @Override
    public List<Review> getAllReviews(Integer id) throws ResturantException {
        Restaurant restaurant = findRestaurant(id);
        return restaurant.getReviews();
    }

    @Override
    public Restaurant CreateRestaurant(Restaurant restaurant) {
        Restaurant createdRestruant = resturantRepo.save(restaurant);
        return createdRestruant;
    }

    @Override
    public Restaurant UpdateRestaurant(Integer id, Restaurant restaurant) throws ResturantException {
        Restaurant restaurantUpdate = findRestaurant(id);
        restaurantUpdate.setAddress(restaurant.getAddress());
        restaurantUpdate.setName(restaurant.getName());

        return resturantRepo.save(restaurantUpdate);
    }

    @Override
    public Boolean deleteRestaurant(Integer id) throws ResturantException {
        Restaurant restaurant = findRestaurant(id);
        resturantRepo.delete(restaurant);
        return true;

    }

    private Restaurant findRestaurant(Integer id) throws ResturantException {
        Optional<Restaurant> restaurantOptional = resturantRepo.findById(id);
        if(restaurantOptional.isEmpty()){
            log.error("Review with id {} does not exist", id);
            throw new ResturantException("Review not found");
        }
        return restaurantOptional.get();
    }
}
