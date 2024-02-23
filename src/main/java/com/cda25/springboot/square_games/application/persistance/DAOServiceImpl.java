package com.cda25.springboot.square_games.application.persistance;


import com.cda25.springboot.square_games.application.persistance.user.UserApp;
import com.cda25.springboot.square_games.application.persistance.user.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class DAOServiceImpl implements DAOService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserApp createUser(UserApp userApp) {
        userDAO.addUser(userApp);
        return userDAO.getUserById(userApp.getId().toString());
    }

    @Override
    public Collection<UserApp> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public UserApp getUserFromId(String userId) {
        return userDAO.getUserById(userId);
    }

}
