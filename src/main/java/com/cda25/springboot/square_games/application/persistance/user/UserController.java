package com.cda25.springboot.square_games.application.persistance.user;

import com.cda25.springboot.square_games.application.persistance.user.domain_obj.UserDomObj;
import com.cda25.springboot.square_games.application.persistance.user.dto.UserDTO;
import com.cda25.springboot.square_games.application.persistance.user.dto.UsersDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin
@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public Iterable<UserDTO> getAllUsers() {
        Collection<UserDomObj> userDomObj = userRepository.findAll();
        log.info(userDomObj.iterator().next().getId().toString());
        return UsersDTO.createUsersDTO(userDomObj);
    }

    @PostMapping("/users")
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        return UserDTO.createUserDTO(userRepository.save(new UserDomObj(userDTO)));
    }

    @GetMapping("/users/{userId}")
    public UserDTO getUserFromId(@PathVariable String userId) {
        return UserDTO.createUserDTO(userRepository.findById(UUID.fromString(userId)).orElse(null));
    }

    @PutMapping("/users/{userId}")
    public UserDTO updateUser(@PathVariable String userId,
                              @RequestBody UserDTO userDTO) {
        Optional<UserDomObj> userDomObj = userRepository.findById(UUID.fromString(userId));
        userDomObj.get().setAll(userDTO);
        userRepository.save(userDomObj.get());

        return UserDTO.createUserDTO(userDomObj.get());
    }

    @DeleteMapping("/users/{userId}")
    public UserDTO deleteUser(@PathVariable String userId) {
        Optional<UserDomObj> userDomObj = userRepository.findById(UUID.fromString(userId));
        userRepository.deleteById(UUID.fromString(userId));
        return UserDTO.createUserDTO(userDomObj.get());
    }
}
