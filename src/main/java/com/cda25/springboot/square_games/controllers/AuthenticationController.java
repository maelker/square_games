package com.cda25.springboot.square_games.controllers;

import com.cda25.springboot.square_games.dto.user.AuthenticationParamsDTO;
import com.cda25.springboot.square_games.entities_do.UserEntity;
import com.cda25.springboot.square_games.repositories.UserRepository;
import com.cda25.springboot.square_games.security.JwtTokenUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("public")
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @GetMapping("login")
    public String login() {
        return "You need to log in";
    }

    @PostMapping("login")
    public String login(@Valid @RequestBody AuthenticationParamsDTO authenticationParamsDTO, HttpServletResponse response) throws Exception {
        UserEntity user = userRepository.findByUsername(authenticationParamsDTO.getUsername()).orElse(null);
        assert user != null;
        String jwtToken = jwtTokenUtil.generateToken(user.getUsername());
        response.addHeader("Authorization", "Bearer " + jwtToken);

        return "Token created.\nExpiration date : " + jwtTokenUtil.getExpirationDateFromToken(jwtToken);
    }
}
