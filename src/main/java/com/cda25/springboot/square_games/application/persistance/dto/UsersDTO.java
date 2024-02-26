package com.cda25.springboot.square_games.application.persistance.dto;

import com.cda25.springboot.square_games.application.persistance.domainobject.UserDomObj;

import java.util.Collection;

public record UsersDTO (Collection<UserDTO> userDTOCollection) {
    public static Collection<UserDTO> createUsersDTO (Collection<UserDomObj> UserDomObjS) {

        return UserDomObjS == null ? null : UserDomObjS.stream().map(UserDTO::createUserDTO).toList();
    }
}
