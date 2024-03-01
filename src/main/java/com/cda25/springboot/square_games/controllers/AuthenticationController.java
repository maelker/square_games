package com.cda25.springboot.square_games.controllers;

import com.cda25.springboot.square_games.dto.user.AuthenticationParamsDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/public")
public class AuthenticationController {
    @GetMapping("login")
    public String login() {
        return "You need to log in";
    }
    @PostMapping("login")
    public AuthenticationParamsDTO login(@Valid @RequestBody AuthenticationParamsDTO authenticationParamsDTO) {
        return authenticationParamsDTO;
    }
}
