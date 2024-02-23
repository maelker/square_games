package com.cda25.springboot.square_games.application.persistance.user.dto;

import com.cda25.springboot.square_games.application.persistance.user.UserR;

import java.util.Collection;

public record UsersDTO (Collection<UserDTO> userDTOCollection) {
    public static Collection<UserDTO> createUsersDTO (Collection<UserR> userRS) {

        return userRS == null ? null : userRS.stream().map(UserDTO::createUsersDTO).toList();
    }
}
