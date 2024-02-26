package com.cda25.springboot.square_games.application.persistance;

import com.cda25.springboot.square_games.application.persistance.domainobject.UserDomObj;
import com.cda25.springboot.square_games.application.persistance.dto.UserDTO;
import com.cda25.springboot.square_games.application.persistance.dto.UsersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/users/create")
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        UserDomObj userDomObj = new UserDomObj(userDTO);
        userDomObj = userJPA.save(userDomObj);
        return UserDTO.createUserDTO(userDomObj);
    }

    @GetMapping("/users/{userId}/full")
    public UserDTO getUserFromId(@PathVariable String userId) {
        return UserDTO.createUserDTO(userJPA.findById(Integer.parseInt(userId)).orElse(null));
    }

    @PutMapping("/users/{userId}/update")
    public UserDTO updateUser(@PathVariable String userId,
                              @RequestBody UserDTO userDTO) {
        UserDomObj userDomObj = userJPA.getReferenceById(Integer.parseInt(userId));
        userDomObj = new UserDomObj(userDTO);
        return UserDTO.createUserDTO(userDomObj);
    }

    @DeleteMapping("/users/{userId}/delete")
    public UserDTO deleteUser(@PathVariable String userId) {
        UserDomObj userDomObj = userJPA.getReferenceById(Integer.parseInt(userId));
        userJPA.deleteById(Integer.parseInt(userId));
        return UserDTO.createUserDTO(userDomObj);
    }
}
