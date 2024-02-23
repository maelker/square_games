package com.cda25.springboot.square_games.application.persistance.user.dto;

import com.cda25.springboot.square_games.application.persistance.user.UserApp;

public record UserDTO(String id, String firstName, String lastName) {
    public static UserDTO createUsersDTO (UserApp userApp) {
        return userApp == null ? null : new UserDTO(userApp.getId().toString(), userApp.getFirstName(), userApp.getLastName());
    }
}
