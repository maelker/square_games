package com.cda25.springboot.square_games.application.persistance.user.controller;

import com.cda25.springboot.square_games.application.persistance.user.domain_obj.UserDomObj;
import com.cda25.springboot.square_games.application.persistance.user.domain_obj.address.AddressDomObj;
import com.cda25.springboot.square_games.application.persistance.user.dto.UserDTO;
import com.cda25.springboot.square_games.application.persistance.user.dto.UsersDTO;
import com.cda25.springboot.square_games.application.persistance.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public Iterable<UserDTO> getAllUsers() {
        return UsersDTO.createUsersDTO(userService.findAll());
    }

    @PostMapping("/users")
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        UserDomObj userDomObj = new UserDomObj(userDTO);
        AddressDomObj addressDomObj = new AddressDomObj(userDTO.address());
        userDomObj = userService.save(userDomObj, addressDomObj);
        return UserDTO.createUserDTO(userDomObj, addressDomObj);
    }

    @GetMapping("/users/{userId}")
    public UserDTO getUserFromId(@PathVariable String userId) {
        UserDomObj userDomObj = userService.findUserById(UUID.fromString(userId));
        assert userDomObj != null;
        AddressDomObj addressDomObj = userService.findAddressById(userDomObj.getAddressId());
        return UserDTO.createUserDTO(userDomObj, addressDomObj);
    }

    @PutMapping("/users/{userId}")
    public UserDTO updateUser(@PathVariable String userId,
                              @RequestBody UserDTO userDTO) {
        UserDomObj userDomObj = userService.findUserById(UUID.fromString(userId));
        AddressDomObj addressDomObj = new AddressDomObj(userDTO.address());
        if (userDomObj != null) {
            userDomObj.setAll(userDTO, userDTO.address());
            userService.save(userDomObj, addressDomObj);
        }
        return userDomObj == null ? null :UserDTO.createUserDTO(userDomObj, addressDomObj);
    }

    @DeleteMapping("/users/{userId}")
    public UserDTO deleteUser(@PathVariable String userId) {
        UserDomObj userDomObj = userService.findUserById(UUID.fromString(userId));
        AddressDomObj addressDomObj;
        if (userDomObj != null) {
            addressDomObj = userService.findAddressById(userDomObj.getAddressId());
            userService.deleteById(UUID.fromString(userId));
        } else {
            addressDomObj = null;
        }
        return userDomObj == null ? null :UserDTO.createUserDTO(userDomObj, addressDomObj);
    }
}
