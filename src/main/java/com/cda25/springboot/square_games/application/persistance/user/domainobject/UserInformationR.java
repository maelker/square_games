package com.cda25.springboot.square_games.application.persistance.user.domainobject;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public record UserInformationR(
        @Id String login,
        UUID id,
        String password,
        String mail,
        String firstName,
        String lastName
) {
}
