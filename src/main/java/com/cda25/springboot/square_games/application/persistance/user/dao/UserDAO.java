package com.cda25.springboot.square_games.application.persistance.user.dao;

import com.cda25.springboot.square_games.application.persistance.user.UserApp;

import java.util.List;

public interface UserDAO {
    List<UserApp> getAllUsers();
    public UserApp getUserById(String id);
    public void addUser(UserApp user);
    public void updateUser(UserApp user);
    public void deleteUser(String id);

}
