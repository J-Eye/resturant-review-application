package com.irby.jaden.resturantreview.domain.core.user.service;

import com.irby.jaden.resturantreview.domain.core.exceptions.UserExecption;
import com.irby.jaden.resturantreview.domain.core.user.model.UserEntity;

import java.util.List;

public interface UserService {
    UserEntity create(UserEntity user);
    UserEntity getUserById(Integer id) throws UserExecption;
    List<UserEntity> getAllUsers();
    UserEntity updateUser(Integer id, UserEntity user) throws UserExecption;
    Boolean deleteUser(Integer id) throws UserExecption;

}
