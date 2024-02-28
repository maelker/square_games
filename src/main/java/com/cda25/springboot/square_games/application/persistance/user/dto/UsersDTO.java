package com.cda25.springboot.square_games.application.persistance.user.dto;


import com.cda25.springboot.square_games.application.persistance.user.domain_obj.UserDomObj;

import java.util.Collection;

public record UsersDTO (Collection<UserDTO> userDTOCollection) {
    public static Collection<UserDTO> createUsersDTO (Collection<UserDomObj> UserDomObjS) {

        return UserDomObjS == null ? null : UserDomObjS.stream().map(UserDTO::createUserDTO).toList();
    }
}
