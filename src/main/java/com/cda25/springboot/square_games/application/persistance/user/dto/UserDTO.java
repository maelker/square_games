package com.cda25.springboot.square_games.application.persistance.user.dto;

import com.cda25.springboot.square_games.application.persistance.user.UserR;

import java.util.Date;
import java.util.UUID;

public record UserDTO(
        UUID id,
        String avatar,
        Date birthDate,
        Date creationDate,
        String favPayment,
        UUID idParent,
        String login,
        String password,
        String mail,
        String firstName,
        String lastName,
        String city,
        String postalCode,
        String streetName,
        String streetNumber
) {

    public static UserDTO createUsersDTO (UserR userR) {

        return userR == null ? null : new UserDTO(
                userR.userMainR().id(),
                userR.userMainR().avatar(),
                userR.userMainR().birthDate(),
                userR.userMainR().creationDate(),
                userR.userMainR().favPayment(),
                userR.userMainR().idParent(),
                userR.userInformation().login(),
                userR.userInformation().password(),
                userR.userInformation().mail(),
                userR.userInformation().firstName(),
                userR.userInformation().lastName(),
                userR.userAddress().city(),
                userR.userAddress().postalCode(),
                userR.userAddress().streetName(),
                userR.userAddress().streetNumber()
        );
    }
}
