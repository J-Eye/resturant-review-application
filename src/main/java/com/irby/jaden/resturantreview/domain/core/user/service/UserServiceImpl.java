package com.irby.jaden.resturantreview.domain.core.user.service;

import com.irby.jaden.resturantreview.domain.core.exceptions.BadRequestException;
import com.irby.jaden.resturantreview.domain.core.exceptions.UserNotFoundException;
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
    public User createUser(User user) throws BadRequestException {
        validateUser(user);
        User createdUser = userRepo.save(user);
        return createdUser;
    }

    @Override
    public User getUserById(Long id) throws UserNotFoundException {
        User user = findUser(id);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return  userRepo.findAll();
    }

    @Override
    public User updateUser(Long id, User user) throws UserNotFoundException, BadRequestException {
        User saveUser = findUser(id);
        validateUser(user);
        saveUser.setFirstName(user.getFirstName());
        saveUser.setLastName(user.getLastName());
        saveUser.setUserName(user.getUserName());
        saveUser.setEmail(user.getEmail());
        return userRepo.save(saveUser);
    }

    @Override
    public Boolean deleteUser(Long id) throws UserNotFoundException {
        User user = findUser(id);
        userRepo.delete(user);
        return true;
    }

    private User findUser(Long id) throws UserNotFoundException {
        Optional<User> optionalUser = userRepo.findById(id);

        if(optionalUser.isEmpty()){
            log.error("User with id {} does not exist", id);
            throw new UserNotFoundException("User not found");
        };
        return optionalUser.get();
    }

    private void validateUser(User user) throws BadRequestException {
        if(user.getUserName() == null ||
                user.getFirstName() == null ||
                user.getLastName()== null ||
                user.getEmail() == null ||
                user.getCreatedAt() == null){
            throw new BadRequestException("User is missing required fields:"+ user);
        }
    }
}
