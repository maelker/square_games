package com.cda25.springboot.square_games.application.persistance;

import com.cda25.springboot.square_games.application.persistance.dao.UserDAO;
import com.cda25.springboot.square_games.application.persistance.domainobject.UserDomObj;
import com.cda25.springboot.square_games.application.persistance.dto.UserDTO;
import com.cda25.springboot.square_games.application.persistance.dto.UsersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @GetMapping("/users")
    public Iterable<UserDTO> getAllUsers() {
        return UsersDTO.createUsersDTO(userDAO.getAllUsers());
    }

    @PostMapping("/users/create")
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        UserDomObj userDomObj = UserDomObj.createUserDomObj(userDTO);
        userDomObj = userDAO.addUser(userDomObj);
        return UserDTO.createUsersDTO(userDomObj);
    }

    @GetMapping("/users/{userId}/full")
    public UserDTO getUserFromId(@PathVariable String userId) {
        return UserDTO.createUsersDTO(userDAO.getUserById(userId));
    }

    @PutMapping("/users/{userId}/update")
    public UserDTO updateUser(@PathVariable String userId,
                              @RequestBody UserDTO userDTO) {

        return UserDTO.createUsersDTO(userDAO.updateUser(UserDomObj.createUserDomObj(userDTO), userId));
    }

    @DeleteMapping("/users/{userId}/delete")
    public UserDTO deleteUser(@PathVariable String userId) {
        return UserDTO.createUsersDTO(userDAO.deleteUser(userId));
    }
}
