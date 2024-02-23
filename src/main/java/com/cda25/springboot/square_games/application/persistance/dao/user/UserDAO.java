package com.cda25.springboot.square_games.application.persistance.dao.user;

import java.util.List;

public interface UserDAO {
    List<UserSquareGames> getAllUsers();
    public UserSquareGames getUserById(int id);
    public void addUser(UserSquareGames user);
    public void updateUser(UserSquareGames user);
    public void deleteUser(int id);

}
