package com.cda25.springboot.square_games.controllers;

import com.cda25.springboot.square_games.dto.user.AuthenticationParamsDTO;
import com.cda25.springboot.square_games.dto.user.UserDTO;
import com.cda25.springboot.square_games.entities_do.UserEntity;
import com.cda25.springboot.square_games.repositories.UserRepository;
import com.cda25.springboot.square_games.security.JwtTokenUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("public")
@Slf4j
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("login")
    public String login() {
        return "You need to log in";
    }

    @PostMapping("login")
    public String login(@Valid @RequestBody AuthenticationParamsDTO authenticationParamsDTO, HttpServletResponse response) {

        UserEntity user = userRepository.findByUsername(authenticationParamsDTO.getUsername()).orElse(null);

        String jwtToken = "";
        String result = "";

        if (user != null) {
            Authentication authenticationRequest =
                    UsernamePasswordAuthenticationToken.unauthenticated(
                            authenticationParamsDTO.getUsername(),
                            authenticationParamsDTO.getPassword()
                    );

            Authentication authenticationResponse =
                    this.authenticationManager.authenticate(authenticationRequest);

            if (authenticationResponse.isAuthenticated()
                    && user.isAccountNonExpired()
                    && user.isAccountNonLocked()
                    && user.isCredentialsNonExpired()
                    && user.isEnabled()) {
                jwtToken = jwtTokenUtil.generateToken(user.getUsername());
                log.info("Token generated");
                result = "Token created.\nExpiration date : " + jwtTokenUtil.getExpirationDateFromToken(jwtToken);
                response.addHeader("Authorization", "Bearer " + jwtToken);
            } else if (!authenticationResponse.isAuthenticated()) {
                log.error("User not authenticated");
            }
        } else {
            log.error("Username or password incorrect");
        }


        return result;
    }

    @PostMapping("user")
    public UserDTO createUser(@Valid @RequestBody UserDTO userDTO) {
        log.info("POST (/user) : " + userDTO.username());
        UserEntity userEntity = new UserEntity(userDTO);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return UserDTO.createUserDTO(userRepository.save(userEntity));
    }
}
