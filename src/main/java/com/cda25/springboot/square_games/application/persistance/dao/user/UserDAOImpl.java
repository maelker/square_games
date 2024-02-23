package com.cda25.springboot.square_games.application.persistance.dao.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    Collection<UserSquareGames> userSquareGamesCollection = new ArrayList<>();

    public UserDAOImpl(UserSquareGames userSquareGames) {
        userSquareGamesCollection.add(userSquareGames);
    }

    @Override
    public List<UserSquareGames> getAllUsers() {
        return null;
    }

    @Override
    public UserSquareGames getUserById(int id) {
        return null;
    }

    @Override
    public void addUser(UserSquareGames user) {

    }

    @Override
    public void updateUser(UserSquareGames user) {

    }

    @Override
    public void deleteUser(int id) {

    }
}
