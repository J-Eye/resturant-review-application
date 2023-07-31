package com.irby.jaden.resturantreview.domain.core.user.service;

import com.irby.jaden.resturantreview.domain.core.exceptions.UserExecption;
import com.irby.jaden.resturantreview.domain.core.user.model.UserEntity;
import com.irby.jaden.resturantreview.domain.core.user.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

    private UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public UserEntity create(UserEntity user) {
        UserEntity createdUser = userRepo.save(user);
        return createdUser;
    }

    @Override
    public UserEntity getUserById(Integer id) throws UserExecption {
        UserEntity user = findUser(id);
        return user;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return  userRepo.findAll();
    }

    @Override
    public UserEntity updateUser(Integer id, UserEntity user) throws UserExecption {
        UserEntity saveUser = findUser(id);

        saveUser.setFirstName(user.getFirstName());
        saveUser.setLastName(user.getLastName());
        saveUser.setUserName(user.getUserName());
        saveUser.setEmail(user.getEmail());
        return userRepo.save(saveUser);
    }

    @Override
    public Boolean deleteUser(Integer id) throws UserExecption {
        UserEntity user = findUser(id);
        userRepo.delete(user);
        return true;
    }

    private UserEntity findUser(Integer id) throws UserExecption{
        Optional<UserEntity> optionalUser = userRepo.findById(id);
        if(optionalUser.isEmpty()){
            log.error("User with id {} does not exist", id);
            throw new UserExecption("User not found");
        };
        return optionalUser.get();
    }
}
