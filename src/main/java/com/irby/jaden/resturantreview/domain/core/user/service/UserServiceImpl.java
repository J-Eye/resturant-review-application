package com.irby.jaden.resturantreview.domain.core.user.service;

import com.irby.jaden.resturantreview.domain.core.exceptions.UserExecption;
import com.irby.jaden.resturantreview.domain.core.user.model.User;
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
    public User createUser(User user) {
        User createdUser = userRepo.save(user);
        return createdUser;
    }

    @Override
    public User getUserById(Long id) throws UserExecption {
        User user = findUser(id);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return  userRepo.findAll();
    }

    @Override
    public User updateUser(Long id, User user) throws UserExecption {
        User saveUser = findUser(id);

        saveUser.setFirstName(user.getFirstName());
        saveUser.setLastName(user.getLastName());
        saveUser.setUserName(user.getUserName());
        saveUser.setEmail(user.getEmail());
        return userRepo.save(saveUser);
    }

    @Override
    public Boolean deleteUser(Long id) throws UserExecption {
        User user = findUser(id);
        userRepo.delete(user);
        return true;
    }

    private User findUser(Long id) throws UserExecption{
        Optional<User> optionalUser = userRepo.findById(id);
        if(optionalUser.isEmpty()){
            log.error("User with id {} does not exist", id);
            throw new UserExecption("User not found");
        };
        return optionalUser.get();
    }
}
