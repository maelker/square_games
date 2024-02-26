package com.cda25.springboot.square_games.application.persistance.user.domainobject;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;
import java.util.UUID;

@Entity
public record UserMainR(
        @Id UUID id,
        String avatar,
        Date birthDate,
        Date creationDate,
        String favPayment,
        UUID idParent
) {
}
