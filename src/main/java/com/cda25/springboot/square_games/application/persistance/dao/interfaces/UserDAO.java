package com.cda25.springboot.square_games.application.persistance.dao.interfaces;


import org.apache.catalina.User;

import java.util.List;

public interface UserDAO {
    List<User> getAllUsers();
    public User getUserById(int id);
    public void addUser(User user);
    public void updateUser(User user);
    public void deleteUser(int id);

}
