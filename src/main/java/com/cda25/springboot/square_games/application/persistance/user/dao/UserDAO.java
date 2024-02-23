package com.cda25.springboot.square_games.application.persistance.user.dao;

import com.cda25.springboot.square_games.application.persistance.user.UserApp;

import java.util.List;

public interface UserDAO {
    List<UserApp> getAllUsers();
    public UserApp getUserById(String id);
    public UserApp addUser(UserApp user);
    public UserApp updateUser(UserApp user, String userId);
    public UserApp deleteUser(String id);

}
