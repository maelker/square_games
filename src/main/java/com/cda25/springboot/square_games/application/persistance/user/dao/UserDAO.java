package com.cda25.springboot.square_games.application.persistance.user.dao;

import com.cda25.springboot.square_games.application.persistance.user.UserR;

import java.util.List;

public interface UserDAO {
    List<UserR> getAllUsers();
    public UserR getUserById(String id);
    public UserR addUser(UserR user);
    public UserR updateUser(UserR user, String userId);
    public UserR deleteUser(String id);

}
