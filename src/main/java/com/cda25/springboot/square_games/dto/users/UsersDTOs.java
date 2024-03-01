package com.cda25.springboot.square_games.dto.users;


import com.cda25.springboot.square_games.entities_do.UsersEntity;

import java.util.Collection;

public record UsersDTOs(Collection<UsersDTO> userDTOCollection) {
    public static Collection<UsersDTO> createUsersDTO(Collection<UsersEntity> usersEntities) {

        return usersEntities == null ? null : usersEntities.stream().map(UsersDTO::createUserDTO).toList();
    }
}
