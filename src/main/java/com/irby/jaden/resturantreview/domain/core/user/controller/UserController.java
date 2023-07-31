package com.irby.jaden.resturantreview.domain.core.user.controller;

import com.irby.jaden.resturantreview.domain.core.resturant.model.Restaurant;
import com.irby.jaden.resturantreview.domain.core.user.model.UserEntity;
import com.irby.jaden.resturantreview.domain.core.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

     private UserService userService;

     @Autowired
     public UserController(UserService userService){
         this.userService = userService;
     }

     @PostMapping("")
     public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user){
         UserEntity savedUser = userService.create(user);
         return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
     }

    @GetMapping("")
    public ResponseEntity<List<UserEntity>> getAll(){
        List<UserEntity> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
