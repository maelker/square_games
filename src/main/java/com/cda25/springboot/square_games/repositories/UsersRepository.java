package com.cda25.springboot.square_games.repositories;

import com.cda25.springboot.square_games.entities_do.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, UUID> {
}
