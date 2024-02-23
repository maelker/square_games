package com.cda25.springboot.square_games.application.persistance.dao;

import com.cda25.springboot.square_games.application.persistance.dao.interfaces.UserDAO;
import org.apache.catalina.User;

import java.util.List;

public class MySQLUserDAO implements UserDAO {
    /**
     * @return
     */
    @Override
    public List<User> getAllUsers() {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public User getUserById(int id) {
        return null;
    }

    /**
     * @param user
     */
    @Override
    public void addUser(User user) {

    }

    /**
     * @param user
     */
    @Override
    public void updateUser(User user) {

    }

    /**
     * @param id
     */
    @Override
    public void deleteUser(int id) {

    }
}
