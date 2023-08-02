package com.irby.jaden.resturantreview.domain.core.resturant.controller;

import com.irby.jaden.resturantreview.domain.core.BaseController;
import com.irby.jaden.resturantreview.domain.core.exceptions.BadRequestException;
import com.irby.jaden.resturantreview.domain.core.exceptions.ResturantNotFoundException;
import com.irby.jaden.resturantreview.domain.core.resturant.model.Restaurant;
import com.irby.jaden.resturantreview.domain.core.resturant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
@CrossOrigin("*")
public class RestaurantController extends BaseController {

    private RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService){
        this.restaurantService = restaurantService;
    }

    @PostMapping("")
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody @NonNull Restaurant restaurant) throws BadRequestException {
        Restaurant savedRestaurant = restaurantService.createRestaurant(restaurant);
        return new ResponseEntity<>(savedRestaurant, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<Restaurant>> getAll(){
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable String id) throws ResturantNotFoundException, BadRequestException {
        long restaurantId = validateId(id);
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody @NonNull Restaurant restaurant, @PathVariable String id) throws ResturantNotFoundException, BadRequestException {
        long restaurantId = validateId(id);

        Restaurant updatedRestaurant = restaurantService.updateRestaurant(restaurantId, restaurant);
        return new ResponseEntity<>(updatedRestaurant, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteRestaurant(@PathVariable String id) throws ResturantNotFoundException, BadRequestException {
        long restaurantId = validateId(id);
        Boolean result = restaurantService.deleteRestaurant(restaurantId);
        return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
    }




}
