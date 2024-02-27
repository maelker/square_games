package com.cda25.springboot.square_games.application.persistance.user.dto;

import com.cda25.springboot.square_games.application.persistance.user.domain_obj.UserDomObj;
import com.cda25.springboot.square_games.application.persistance.user.domain_obj.address.AddressDomObj;
import com.cda25.springboot.square_games.application.persistance.user.dto.address.AddressDTO;

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
        AddressDTO address
) {

    public static UserDTO createUserDTO(UserDomObj userDomObj, AddressDomObj addressDomObj) {

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
                AddressDTO.createAddressDTO(addressDomObj)
        );
    }
}
