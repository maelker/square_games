package com.cda25.springboot.square_games.application.persistance;

import com.cda25.springboot.square_games.application.persistance.domainobject.UserDomObj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository <UserDomObj, UUID> {
}
