package com.cda25.springboot.square_games.application.persistance.user;

import com.cda25.springboot.square_games.application.persistance.user.domain_obj.UserDomObj;
import com.cda25.springboot.square_games.application.persistance.user.dto.UsersDTO;
import com.cda25.springboot.square_games.application.persistance.user.dto.UsersDTOs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin
@RestController
@Slf4j
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/users")
    public Iterable<UsersDTO> getAllUsers() {
        Collection<UserDomObj> userDomObj = usersRepository.findAll();
        log.info("GET (/users) : " + userDomObj.stream().map(UserDomObj::getId).toList());
        return UsersDTOs.createUsersDTO(userDomObj);
    }

    @PostMapping("/users")
    public UsersDTO createUser(@RequestBody UsersDTO userDTO) {
        log.info("POST (/users) : " + userDTO.firstName() + " " + userDTO.lastName());
        return UsersDTO.createUserDTO(usersRepository.save(new UserDomObj(userDTO)));
    }

    @GetMapping("/users/{userId}")
    public UsersDTO getUserFromId(@PathVariable String userId) {
        log.info("GET (/users/{userId) : " + userId);
        return UsersDTO.createUserDTO(usersRepository.findById(UUID.fromString(userId)).orElse(null));
    }

    @PutMapping("/users/{userId}")
    public UsersDTO updateUser(@PathVariable String userId,
                               @RequestBody UsersDTO userDTO) {
        log.info("PUT (/users/{userId) : " + userId);
        Optional<UserDomObj> userDomObj = usersRepository.findById(UUID.fromString(userId));
        userDomObj.get().setAll(userDTO);
        usersRepository.save(userDomObj.get());

        return UsersDTO.createUserDTO(userDomObj.get());
    }

    @DeleteMapping("/users/{userId}")
    public UsersDTO deleteUser(@PathVariable String userId) {
        log.info("DELETE (/users/{userId) : " + userId);
        Optional<UserDomObj> userDomObj = usersRepository.findById(UUID.fromString(userId));
        usersRepository.deleteById(UUID.fromString(userId));
        return UsersDTO.createUserDTO(userDomObj.get());
    }
}
