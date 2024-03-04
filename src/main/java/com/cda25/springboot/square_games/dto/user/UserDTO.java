package com.cda25.springboot.square_games.dto.user;

import com.cda25.springboot.square_games.entities_do.UserEntity;
import jakarta.validation.constraints.NotNull;

public record UserDTO(
        @NotNull
        String password,
        @NotNull
        String username,
        String role
) {

    public static UserDTO createUserDTO(UserEntity user) {

        return user == null ? null : new UserDTO(
                user.getPassword(),
                user.getUsername(),
                user.getRole()
        );
    }
}
