package com.cda25.springboot.square_games.services;

import com.cda25.springboot.square_games.entities_do.UserEntity;
import com.cda25.springboot.square_games.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * @param username the username identifying the user whose data is required.
     * @return UserEntity or null
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElse(new UserEntity());
    }
}
