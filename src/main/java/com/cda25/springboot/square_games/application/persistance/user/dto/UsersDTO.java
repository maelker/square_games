package com.cda25.springboot.square_games.application.persistance.user.dto;

import com.cda25.springboot.square_games.application.persistance.user.UserApp;

import java.util.Collection;

public record UsersDTO (Collection<UserDTO> userDTOCollection) {
    public static Collection<UserDTO> createUsersDTO (Collection<UserApp> userApps) {

        return userApps == null ? null : userApps.stream().map(UserDTO::createUsersDTO).toList();
    }
}
