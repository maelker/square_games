package com.cda25.springboot.square_games.application.persistance.domainobject;

import com.cda25.springboot.square_games.application.persistance.dto.UserDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;
import java.util.UUID;

@Entity
public record UserDomObj(
        @Id UUID id,
        String avatar,
        Date birthDate,
        Date creationDate,
        UUID idParent,
        String login,
        String password,
        String mail,
        String firstName,
        String lastName,
        String favPayment,
        String city,
        String postalCode,
        String streetName,
        String streetNumber
) {
    public static UserDomObj createUserDomObj(UUID id,
                                              String avatar,
                                              Date birthDate,
                                              Date creationDate,
                                              UUID idParent,
                                              String login,
                                              String password,
                                              String mail,
                                              String firstName,
                                              String lastName,
                                              String favPayment,
                                              String city,
                                              String postalCode,
                                              String streetName,
                                              String streetNumber) {
        return new UserDomObj(id,
        avatar,
        birthDate,
        creationDate,
        idParent,
        login,
        password,
        mail,
        firstName,
        lastName,
        favPayment,
        city,
        postalCode,
        streetName,
        streetNumber);
    }

    public static UserDomObj createUserDomObj(UserDTO userDTO) {
        return new UserDomObj(
                userDTO.id() == null ? UUID.randomUUID() : userDTO.id(),
                userDTO.avatar(),
                userDTO.birthDate(),
                userDTO.creationDate(),
                userDTO.idParent(),
                userDTO.login(),
                userDTO.password(),
                userDTO.mail(),
                userDTO.firstName(),
                userDTO.lastName(),
                userDTO.favPayment(),
                userDTO.city(),
                userDTO.postalCode(),
                userDTO.streetName(),
                userDTO.streetNumber()
        );
    }
}
