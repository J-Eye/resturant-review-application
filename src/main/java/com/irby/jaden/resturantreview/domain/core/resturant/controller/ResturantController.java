package com.irby.jaden.resturantreview.domain.core.resturant.controller;

import com.irby.jaden.resturantreview.domain.core.resturant.model.Restaurant;
import com.irby.jaden.resturantreview.domain.core.resturant.service.ResturantService;
import com.irby.jaden.resturantreview.domain.core.review.model.Review;
import com.irby.jaden.resturantreview.domain.core.user.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
@CrossOrigin("*")
public class ResturantController {

    private ResturantService resturantService;

    @Autowired
    public ResturantController(ResturantService resturantService){
        this.resturantService = resturantService;
    }

    @PostMapping("")
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant){
        Restaurant savedRestaurant = resturantService.CreateRestaurant(restaurant);
        return new ResponseEntity<>(savedRestaurant, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<Restaurant>> getAll(){
        List<Restaurant> restaurants = resturantService.getAllResturants();
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }
}
