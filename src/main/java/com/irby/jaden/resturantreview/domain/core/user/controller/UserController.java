package com.irby.jaden.resturantreview.domain.core.user.controller;

import com.irby.jaden.resturantreview.domain.core.BaseController;
import com.irby.jaden.resturantreview.domain.core.exceptions.BadRequestException;
import com.irby.jaden.resturantreview.domain.core.exceptions.UserNotFoundException;
import com.irby.jaden.resturantreview.domain.core.user.model.User;
import com.irby.jaden.resturantreview.domain.core.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController extends BaseController {

     private UserService userService;

     @Autowired
     public UserController(UserService userService){
         this.userService = userService;
     }

     @PostMapping("")
     public ResponseEntity<User> createUser(@RequestBody @NonNull User user) throws BadRequestException {
         User savedUser = userService.createUser(user);
         return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
     }

    @GetMapping("")
    public ResponseEntity<List<User>> getAll(){
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) throws UserNotFoundException, BadRequestException {
         long userId = validateId(id);

         User user = userService.getUserById(userId);
         return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@RequestBody @NonNull User user, @PathVariable String id) throws UserNotFoundException, BadRequestException {
        long userId = validateId(id);
         User updatedUser = userService.updateUser(userId, user);
         return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable String id) throws UserNotFoundException, BadRequestException {
        long userId = validateId(id);
         Boolean result = userService.deleteUser(userId);
         return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
    }
}
