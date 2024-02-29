package com.cda25.springboot.square_games.application.auth;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "user")
@Getter
@Setter
public class UserEntity {//implements UserDetails {

    @Id
    @Column(name = "id_user", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Column(name = "role")
    private String role;
    @Column(name = "is_account_non_expired", nullable = false)
    private boolean isAccountNonExpired;
    @Column(name = "is_account _non_locked", nullable = false)
    private boolean isAccountNonLocked;
    @Column(name = "is_credentials_non_expired", nullable = false)
    private boolean isCredentialsNonExpired;
    @Column(name = "is_enabled", nullable = false)
    private boolean isEnabled;

    public UserEntity() {

    }

    public UserEntity(UserDTO userDTO) {
        id = userDTO.id();
        password = userDTO.password();
        username = userDTO.username();
        role = userDTO.role();
        isAccountNonExpired = userDTO.isAccountNonExpired();
        isAccountNonLocked = userDTO.isAccountNonLocked();
        isCredentialsNonExpired = userDTO.isCredentialsNonExpired();
        isEnabled = userDTO.isEnabled();
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
//        authorities.add(new SimpleGrantedAuthority(this.role));
//        return authorities;
//    }

//    @Override
    public String getPassword() {
        return password;
    }

//    @Override
    public String getUsername() {
        return username;
    }

//    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

//    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

//    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

//    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
