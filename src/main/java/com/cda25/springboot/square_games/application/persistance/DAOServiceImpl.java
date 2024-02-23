package com.cda25.springboot.square_games.application.persistance;


import com.cda25.springboot.square_games.application.persistance.user.UserR;
import com.cda25.springboot.square_games.application.persistance.user.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class DAOServiceImpl implements DAOService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserR createUser(UserR userR) {
        userDAO.addUser(userR);
        return userDAO.getUserById(userR.id().toString());
    }

    @Override
    public Collection<UserR> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public UserR getUserFromId(String userId) {
        return userDAO.getUserById(userId);
    }

    @Override
    public UserR updateUser(UserR user, String userId) {
        return userDAO.updateUser(user, userId);
    }

    @Override
    public UserR deleteUser(String userId) {
        return userDAO.deleteUser(userId);
    }

}
