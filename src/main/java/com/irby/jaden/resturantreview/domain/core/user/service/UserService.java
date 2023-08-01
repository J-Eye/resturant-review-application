package com.irby.jaden.resturantreview.domain.core.user.service;

import com.irby.jaden.resturantreview.domain.core.exceptions.UserExecption;
import com.irby.jaden.resturantreview.domain.core.user.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User getUserById(Long id) throws UserExecption;
    List<User> getAllUsers();
    User updateUser(Long id, User user) throws UserExecption;
    Boolean deleteUser(Long id) throws UserExecption;

}
