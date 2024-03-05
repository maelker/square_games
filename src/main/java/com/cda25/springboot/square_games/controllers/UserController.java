package com.cda25.springboot.square_games.controllers;

import com.cda25.springboot.square_games.dto.user.UserDTO;
import com.cda25.springboot.square_games.dto.user.UserDTOs;
import com.cda25.springboot.square_games.entities_do.UserEntity;
import com.cda25.springboot.square_games.repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static java.lang.Long.parseLong;

@CrossOrigin
@RestController
@RequestMapping("user")
@Slf4j
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public Iterable<UserDTO> getAllUsers() {
        Iterable<UserEntity> userEntities = userRepository.findAll();
        Collection<UserEntity> users = new ArrayList<>();
        userEntities.forEach(users::add);
        log.info("GET (/user) : " + users.stream().map(UserEntity::getId).toList());
        return UserDTOs.createUsersDTO(users);
    }

    @PostMapping("")
    public UserDTO createUser(@Valid @RequestBody UserDTO userDTO) {
        log.info("POST (/user) : " + userDTO.username());
        UserEntity userEntity = new UserEntity(userDTO);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return UserDTO.createUserDTO(userRepository.save(userEntity));
    }

    @GetMapping("{userId}")
    public UserDTO getUserFromId(@PathVariable String userId) {
        log.info("GET (/user/{userId) : " + userId);
        return UserDTO.createUserDTO(userRepository.findById(parseLong(userId)).orElse(null));
    }

    @PutMapping("{userId}")
    public UserDTO updateUserPassword(@PathVariable String userId,
                                      @Valid @RequestBody String password) {
        log.info("PUT (/user/{userId) : " + userId);
        Optional<UserEntity> userEntity = userRepository.findById(parseLong(userId));
        boolean isPresent = userEntity.isPresent();
        if (isPresent) {
            userEntity.get().setPassword(password);
            userRepository.save(userEntity.get());
        }
        return isPresent ? UserDTO.createUserDTO(userEntity.get()) : null;
    }

    @DeleteMapping("{userId}")
    public UserDTO deleteUser(@PathVariable String userId) {
        log.info("DELETE (/user/{userId) : " + userId);
        Optional<UserEntity> userEntity = userRepository.findById(parseLong(userId));
        boolean isPresent = userEntity.isPresent();
        if (isPresent) {
            userRepository.deleteById(parseLong(userId));
        }
        return isPresent ? UserDTO.createUserDTO(userEntity.get()) : null;
    }
}
