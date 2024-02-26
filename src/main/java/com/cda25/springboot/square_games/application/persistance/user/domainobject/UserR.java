package com.cda25.springboot.square_games.application.persistance.user.domainobject;

import com.cda25.springboot.square_games.application.persistance.user.dto.UserDTO;

import java.util.Date;
import java.util.UUID;

public record UserR(
        UserMainR userMainR,
        UserInformationR userInformation,
        UserAddressR userAddress
){
    public UserR {
    }

    public static UserR createUserImpl(
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
        return new UserR(
                new UserMainR(
                    UUID.randomUUID(),
                    avatar,
                    birthDate,
                    creationDate,
                    favPayment,
                    idParent
                ),
                new UserInformationR(login,
                        id, password,
                        mail,
                        firstName,
                        lastName
                ),
                new UserAddressR(id,
                        city,
                        postalCode,
                        streetName,
                        streetNumber
                )
        );
    }
    public static UserR createUserImpl(UserDTO userDTO) {
        return new UserR(
                new UserMainR(
                        userDTO.id() == null ? UUID.randomUUID() : userDTO.id(),
                        userDTO.avatar(),
                        userDTO.birthDate(),
                        userDTO.creationDate(),
                        userDTO.favPayment(),
                        userDTO.idParent()
                ),
                new UserInformationR(
                        userDTO.login(),
                        userDTO.id(),
                        userDTO.password(),
                        userDTO.mail(),
                        userDTO.firstName(),
                        userDTO.lastName()
                ),
                new UserAddressR(
                        userDTO.id(),
                        userDTO.city(),
                        userDTO.postalCode(),
                        userDTO.streetName(),
                        userDTO.streetNumber()
                )
        );
    }

    public static UserR createUserImpl(UserMainR userMainR, UserInformationR userInformations, UserAddressR userAddress) {
        return new UserR(userMainR, userInformations, userAddress);
    }

}
