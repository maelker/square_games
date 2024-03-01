package com.cda25.springboot.square_games.repositories;


import com.cda25.springboot.square_games.entities_do.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}
