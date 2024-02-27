package com.cda25.springboot.square_games.application.persistance.user.dto;

import com.cda25.springboot.square_games.application.persistance.user.domain_obj.UserDomObj;
import com.cda25.springboot.square_games.application.persistance.user.domain_obj.address.AddressDomObj;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public record UsersDTO (Collection<UserDTO> userDTOCollection) {
    public static Collection<UserDTO> createUsersDTO (Map<UserDomObj, AddressDomObj> UserDomObjS) {
        Collection<UserDTO> userDTOS = new ArrayList<>();
        UserDomObjS.forEach((userDomObj, addressDomObj) ->  userDTOS.add(UserDTO.createUserDTO(userDomObj, addressDomObj)));
        return userDTOS;
    }
}
