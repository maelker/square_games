package com.cda25.springboot.square_games.dto.user;

import com.cda25.springboot.square_games.entities_do.UserEntity;

import java.util.Collection;

public record UserDTOs(Collection<UserDTO> userDTOCollection) {
    public static Collection<UserDTO> createUsersDTO(Collection<UserEntity> userEntities) {

        return userEntities == null ? null : userEntities.stream().map(UserDTO::createUserDTO).toList();
    }
}
