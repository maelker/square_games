package com.cda25.springboot.square_games.controllers;

import com.cda25.springboot.square_games.dto.users.UsersDTO;
import com.cda25.springboot.square_games.dto.users.UsersDTOs;
import com.cda25.springboot.square_games.entities_do.UsersEntity;
import com.cda25.springboot.square_games.repositories.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin
@RestController @RequestMapping("users")
@Slf4j
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("")
    public Iterable<UsersDTO> getAllUsers() {
        Collection<UsersEntity> usersEntities = usersRepository.findAll();
        log.info("GET (/users) : " + usersEntities.stream().map(UsersEntity::getId).toList());
        return UsersDTOs.createUsersDTO(usersEntities);
    }

    @PostMapping("")
    public UsersDTO createUser(@RequestBody UsersDTO userDTO) {
        log.info("POST (/users) : " + userDTO.firstName() + " " + userDTO.lastName());
        return UsersDTO.createUserDTO(usersRepository.save(new UsersEntity(userDTO)));
    }

    @GetMapping("{userId}")
    public UsersDTO getUserFromId(@PathVariable String userId) {
        log.info("GET (/users/{userId) : " + userId);
        return UsersDTO.createUserDTO(usersRepository.findById(UUID.fromString(userId)).orElse(null));
    }

    @PutMapping("{userId}")
    public UsersDTO updateUser(@PathVariable String userId,
                               @RequestBody UsersDTO userDTO) {
        log.info("PUT (/users/{userId) : " + userId);
        Optional<UsersEntity> usersEntity = usersRepository.findById(UUID.fromString(userId));
        usersEntity.get().setAll(userDTO);
        usersRepository.save(usersEntity.get());

        return UsersDTO.createUserDTO(usersEntity.get());
    }

    @DeleteMapping("{userId}")
    public UsersDTO deleteUser(@PathVariable String userId) {
        log.info("DELETE (/users/{userId) : " + userId);
        Optional<UsersEntity> usersEntity = usersRepository.findById(UUID.fromString(userId));
        usersRepository.deleteById(UUID.fromString(userId));
        return UsersDTO.createUserDTO(usersEntity.get());
    }
}
