package com.cda25.springboot.square_games.application.persistance.user.dto;


import com.cda25.springboot.square_games.application.persistance.user.domain_obj.UserDomObj;

import java.util.Collection;

public record UsersDTOs(Collection<UsersDTO> userDTOCollection) {
    public static Collection<UsersDTO> createUsersDTO(Collection<UserDomObj> UserDomObjS) {

        return UserDomObjS == null ? null : UserDomObjS.stream().map(UsersDTO::createUserDTO).toList();
    }
}
