package com.cda25.springboot.square_games.application.persistance.user.dto;

import com.cda25.springboot.square_games.application.persistance.user.domainobject.UserDomObj;

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

    public static UserDTO createUsersDTO (UserDomObj userDomObj) {

        return userDomObj == null ? null : new UserDTO(
                userDomObj.id(),
                userDomObj.avatar(),
                userDomObj.birthDate(),
                userDomObj.creationDate(),
                userDomObj.favPayment(),
                userDomObj.idParent(),
                userDomObj.login(),
                userDomObj.password(),
                userDomObj.mail(),
                userDomObj.firstName(),
                userDomObj.lastName(),
                userDomObj.city(),
                userDomObj.postalCode(),
                userDomObj.streetName(),
                userDomObj.streetNumber()
        );
    }
}
