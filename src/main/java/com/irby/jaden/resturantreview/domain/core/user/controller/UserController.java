package com.irby.jaden.resturantreview.domain.core.user.controller;

import com.irby.jaden.resturantreview.domain.core.exceptions.UserExecption;
import com.irby.jaden.resturantreview.domain.core.user.model.User;
import com.irby.jaden.resturantreview.domain.core.user.service.UserService;
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
     public ResponseEntity<User> createUser(@RequestBody User user){
         User savedUser = userService.createUser(user);
         return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
     }

    @GetMapping("")
    public ResponseEntity<List<User>> getAll(){
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@RequestParam Long id) throws UserExecption {
         User user = userService.getUserById(id);
         return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @RequestParam Long id) throws UserExecption {
         User updatedUser = userService.updateUser(id, user);
         return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteUser(@RequestParam Long id) throws UserExecption {
         Boolean result = userService.deleteUser(id);
         return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
