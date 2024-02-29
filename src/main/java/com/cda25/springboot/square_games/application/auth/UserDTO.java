package com.cda25.springboot.square_games.application.auth;

public record UserDTO(
        long id,
        String password,
        String username,
        String role,
        boolean isAccountNonExpired,
        boolean isAccountNonLocked,
        boolean isCredentialsNonExpired,
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
