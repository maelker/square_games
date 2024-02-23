package com.cda25.springboot.square_games.application.persistance;


import com.cda25.springboot.square_games.application.persistance.user.UserR;
import com.cda25.springboot.square_games.application.persistance.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Primary
@Service
public class DAOServiceJDBC implements DAOService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserR createUser(UserR userR) {
        return userRepository.save(userR);
    }

    @Override
    public Collection<UserR> getAllUsers() {
        Collection<UserR> userRCollection = new ArrayList<>();
        userRepository.findAll().forEach(userRCollection::add);
        return userRCollection;
    }

    @Override
    public UserR getUserFromId(String userId) {
        Optional<UserR> userR = userRepository.findById(Integer.parseInt(userId));
        return userR.orElse(null);
    }

    @Override
    public UserR updateUser(UserR user, String userId) {
        userRepository.findById(Integer.parseInt(userId)).get();
        return userRepository.save(user);
    }

    @Override
    public UserR deleteUser(String userId) {
        Optional<UserR> userR = userRepository.findById(Integer.parseInt(userId));
        if(userR.isPresent()){
            userRepository.deleteById(Integer.parseInt(userId));
        }
        return userR.orElse(null);
    }

}
