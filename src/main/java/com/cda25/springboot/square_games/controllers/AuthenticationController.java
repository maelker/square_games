package com.cda25.springboot.square_games.controllers;

import com.cda25.springboot.square_games.dto.user.AuthenticationParamsDTO;
import com.cda25.springboot.square_games.entities_do.UserEntity;
import com.cda25.springboot.square_games.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("public")
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("login")
    public String login() {
        return "You need to log in";
    }
    @PostMapping("login")
    public String login(@Valid @RequestBody AuthenticationParamsDTO authenticationParamsDTO) {

        UserEntity user = userRepository.findByUsername(authenticationParamsDTO.getUsername()).orElse(null);

        assert user != null;

        String jwtToken = Jwts.builder()
                .claim("username", user.getUsername())
                .claim("password", user.getPassword())
                .setSubject("jane")
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(5l, ChronoUnit.MINUTES)))
                .compact();

//        UsernamePasswordAuthenticationToken authenticationToken = authenticationManager.getAuthentication(request);

//        SecurityContextHolder.getContext().setAuthentication(authenticationToken);


        return jwtToken;
    }
}
