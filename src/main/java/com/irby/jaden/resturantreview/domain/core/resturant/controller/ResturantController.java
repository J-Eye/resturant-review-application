package com.irby.jaden.resturantreview.domain.core.resturant.controller;

import com.irby.jaden.resturantreview.domain.core.exceptions.ResturantException;
import com.irby.jaden.resturantreview.domain.core.resturant.model.Restaurant;
import com.irby.jaden.resturantreview.domain.core.resturant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
@CrossOrigin("*")
public class ResturantController {

    private RestaurantService restaurantService;

    @Autowired
    public ResturantController(RestaurantService restaurantService){
        this.restaurantService = restaurantService;
    }

    @PostMapping("")
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant){
        Restaurant savedRestaurant = restaurantService.createRestaurant(restaurant);
        return new ResponseEntity<>(savedRestaurant, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<Restaurant>> getAll(){
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@RequestParam Long id) throws ResturantException {
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody Restaurant restaurant, @RequestParam Long id) throws ResturantException {
        Restaurant updatedRestaurant = restaurantService.updateRestaurant(id, restaurant);
        return new ResponseEntity<>(updatedRestaurant, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteRestaurant(@RequestParam Long id) throws ResturantException {
        Boolean result = restaurantService.deleteRestaurant(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }




}
