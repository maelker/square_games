package com.cda25.springboot.square_games.entities_do;

import com.cda25.springboot.square_games.dto.user.UserDTO;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Slf4j
public class UserEntity implements UserDetails {

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
    @Column(name = "is_account_non_expired", nullable = false, columnDefinition = "boolean default true")
    private boolean isAccountNonExpired;
    @Column(name = "is_account _non_locked", nullable = false, columnDefinition = "boolean default true")
    private boolean isAccountNonLocked;
    @Column(name = "is_credentials_non_expired", nullable = false, columnDefinition = "boolean default true")
    private boolean isCredentialsNonExpired;
    @Column(name = "is_enabled", nullable = false, columnDefinition = "boolean default true")
    private boolean isEnabled;


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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(this.role));
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        if (!isAccountNonExpired) {
            log.info("Account expired !");
        }
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        if (!isAccountNonLocked) {
            log.info("Account locked !");
        }
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        if (!isCredentialsNonExpired) {
            log.info("Credentials expired !");
        }
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        if (!isEnabled) {
            log.info("Account disabled !");
        }
        return isEnabled;
    }
}
