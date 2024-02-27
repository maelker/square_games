package com.cda25.springboot.square_games.application.persistance.user;

import com.cda25.springboot.square_games.application.persistance.user.domain_obj.address.AddressDomObj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<AddressDomObj, UUID> {
}
