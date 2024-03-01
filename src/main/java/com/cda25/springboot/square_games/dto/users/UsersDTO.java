package com.cda25.springboot.square_games.dto.users;

import com.cda25.springboot.square_games.entities_do.UsersEntity;

import java.util.Date;
import java.util.UUID;

public record UsersDTO(
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

    public static UsersDTO createUserDTO(UsersEntity usersEntity) {

        return usersEntity == null ? null : new UsersDTO(
                usersEntity.getId(),
                usersEntity.getAvatar(),
                usersEntity.getBirthDate(),
                usersEntity.getCreationDate(),
                usersEntity.getFavPayment(),
                usersEntity.getIdParent(),
                usersEntity.getLogin(),
                usersEntity.getPassword(),
                usersEntity.getMail(),
                usersEntity.getFirstName(),
                usersEntity.getLastName(),
                usersEntity.getCity(),
                usersEntity.getPostalCode(),
                usersEntity.getStreetName(),
                usersEntity.getStreetNumber()
        );
    }
}
