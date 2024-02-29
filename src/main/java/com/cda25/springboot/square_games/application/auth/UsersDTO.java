package com.cda25.springboot.square_games.application.auth;

import java.util.Collection;

public record UsersDTO(Collection<UserDTO> userDTOCollection) {
    public static Collection<UserDTO> createUsersDTO(Collection<UserEntity> userEntities) {

        return userEntities == null ? null : userEntities.stream().map(UserDTO::createUserDTO).toList();
    }
}
