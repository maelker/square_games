package com.cda25.springboot.square_games.application.auth;

import jakarta.validation.constraints.NotNull;

public record UserDTO(
        @NotNull
        long id,
        @NotNull
        String password,
        @NotNull
        String username,
        String role,
        @NotNull
        boolean isAccountNonExpired,
        @NotNull
        boolean isAccountNonLocked,
        @NotNull
        boolean isCredentialsNonExpired,
        @NotNull
        boolean isEnabled
) {

    public static UserDTO createUserDTO(UserEntity user) {

        return user == null ? null : new UserDTO(
                user.getId(),
                user.getPassword(),
                user.getUsername(),
                user.getRole(),
                user.isAccountNonExpired(),
                user.isAccountNonLocked(),
                user.isCredentialsNonExpired(),
                user.isEnabled()
        );
    }
}
