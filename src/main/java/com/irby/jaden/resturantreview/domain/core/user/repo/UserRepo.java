package com.irby.jaden.resturantreview.domain.core.user.repo;

import com.irby.jaden.resturantreview.domain.core.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByLastName(String lastName);


}
