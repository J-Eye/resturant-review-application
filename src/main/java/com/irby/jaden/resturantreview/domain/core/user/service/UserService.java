package com.irby.jaden.resturantreview.domain.core.user.service;

import com.irby.jaden.resturantreview.domain.core.exceptions.BadRequestException;
import com.irby.jaden.resturantreview.domain.core.exceptions.UserNotFoundException;
import com.irby.jaden.resturantreview.domain.core.user.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user) throws BadRequestException;
    User getUserById(Long id) throws UserNotFoundException;
    List<User> getAllUsers();
    User updateUser(Long id, User user) throws UserNotFoundException, BadRequestException;
    Boolean deleteUser(Long id) throws UserNotFoundException;

}
