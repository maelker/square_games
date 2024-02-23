package com.cda25.springboot.square_games.application.persistance.user.dto;

import com.cda25.springboot.square_games.application.persistance.user.UserApp;

public record UserDTO(int id, String firstName, String LastName) {
    public static UserDTO createUsersDTO (UserApp userApp) {
        return new UserDTO(userApp.getId(), userApp.getFirstName(), userApp.getLastName());
    }
}
