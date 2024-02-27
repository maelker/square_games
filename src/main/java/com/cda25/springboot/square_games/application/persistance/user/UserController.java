package com.cda25.springboot.square_games.application.persistance.user;

import com.cda25.springboot.square_games.application.persistance.user.domain_object.UserDomObj;
import com.cda25.springboot.square_games.application.persistance.user.dto.UserDTO;
import com.cda25.springboot.square_games.application.persistance.user.dto.UsersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private UserRepository userJPA;

    @GetMapping("/users")
    public Iterable<UserDTO> getAllUsers() {
        return UsersDTO.createUsersDTO(userJPA.findAll());
    }

    @PostMapping("/users")
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        UserDomObj userDomObj = new UserDomObj(userDTO);
        userDomObj = userJPA.save(userDomObj);
        return UserDTO.createUserDTO(userDomObj);
    }

    @GetMapping("/users/{userId}")
    public UserDTO getUserFromId(@PathVariable String userId) {
        return UserDTO.createUserDTO(userJPA.findById(UUID.fromString(userId)).orElse(null));
    }

    @PutMapping("/users/{userId}")
    public UserDTO updateUser(@PathVariable String userId,
                              @RequestBody UserDTO userDTO) {
        Optional<UserDomObj> userDomObj = userJPA.findById(UUID.fromString(userId));
        if (userDomObj.isPresent()) {
            userDomObj.get().setAll(userDTO);
            userJPA.save(userDomObj.get());
        }
        return userDomObj.map(UserDTO::createUserDTO).orElse(null);
    }

    @DeleteMapping("/users/{userId}")
    public UserDTO deleteUser(@PathVariable String userId) {
        Optional<UserDomObj> userDomObj = userJPA.findById(UUID.fromString(userId));
        if (userDomObj.isPresent()) {
            userJPA.deleteById(UUID.fromString(userId));
        }
        return userDomObj.map(UserDTO::createUserDTO).orElse(null);
    }
}
