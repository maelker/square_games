package com.cda25.springboot.square_games.application.persistance.user.domainobject;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public record UserAddressR(
        @Id UUID id,
        String city,
        String postalCode,
        String streetName,
        String streetNumber
) {
}
