package com.cda25.springboot.square_games.application.persistance.dto;

import com.cda25.springboot.square_games.application.persistance.domainobject.UserDomObj;

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

    public static UserDTO createUserDTO(UserDomObj userDomObj) {

        return userDomObj == null ? null : new UserDTO(
                userDomObj.getId(),
                userDomObj.getAvatar(),
                userDomObj.getBirthDate(),
                userDomObj.getCreationDate(),
                userDomObj.getFavPayment(),
                userDomObj.getIdParent(),
                userDomObj.getLogin(),
                userDomObj.getPassword(),
                userDomObj.getMail(),
                userDomObj.getFirstName(),
                userDomObj.getLastName(),
                userDomObj.getCity(),
                userDomObj.getPostalCode(),
                userDomObj.getStreetName(),
                userDomObj.getStreetNumber()
        );
    }
}
