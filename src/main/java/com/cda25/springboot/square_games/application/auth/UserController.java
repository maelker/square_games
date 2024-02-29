package com.cda25.springboot.square_games.application.auth;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static java.lang.Long.parseLong;

@CrossOrigin
@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user")
    public Iterable<UserDTO> getAllUsers() {
        Iterable<UserEntity> userEntities = userRepository.findAll();
        Collection<UserEntity> users = new ArrayList<>();
        userEntities.forEach(users::add);
        log.info("GET (/users) : " + users.stream().map(UserEntity::getId).toList());
        return UsersDTO.createUsersDTO(users);
    }

    @PostMapping("/user")
    public UserDTO createUser(@Valid @RequestBody UserDTO userDTO) {
        log.info("POST (/users) : " + userDTO.id() + " " + userDTO.username());
        return UserDTO.createUserDTO(userRepository.save(new UserEntity(userDTO)));
    }

    @GetMapping("/user/{userId}/id")
    public UserDTO getUserFromId(@PathVariable String userId) {
        log.info("GET (/user/{userId) : " + userId);
        return UserDTO.createUserDTO(userRepository.findById(parseLong(userId)).orElse(null));
    }

    @GetMapping("/user/{username}/username")
    public UserDTO getUserFromUsername(@PathVariable String username) {
        log.info("GET (/user/{username) : " + username);
        return UserDTO.createUserDTO(userRepository.findByUsername(username).orElse(null));
    }

    @PutMapping("/user/{userId}/id")
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

    @DeleteMapping("/user/{userId}")
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
