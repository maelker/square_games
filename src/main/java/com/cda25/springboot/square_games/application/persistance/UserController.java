package com.cda25.springboot.square_games.application.persistance;

import com.cda25.springboot.square_games.application.persistance.domainobject.UserDomObj;
import com.cda25.springboot.square_games.application.persistance.dto.UserDTO;
import com.cda25.springboot.square_games.application.persistance.dto.UsersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@CrossOrigin
@RestController
public class UserController {

//    @Autowired
//    private UserDAO userDAO;

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
        userDomObj.get().setAll(userDTO);
        userJPA.save(userDomObj.get());
        return UserDTO.createUserDTO(userDomObj.get());
    }

    @DeleteMapping("/users/{userId}")
    public UserDTO deleteUser(@PathVariable String userId) {
        Optional<UserDomObj> userDomObj = userJPA.findById(UUID.fromString(userId));
        userJPA.deleteById(UUID.fromString(userId));
        return UserDTO.createUserDTO(userDomObj.get());
    }
}
