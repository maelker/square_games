package com.cda25.springboot.square_games.application.persistance;


import com.cda25.springboot.square_games.application.persistance.dao.user.UserDAO;
import com.cda25.springboot.square_games.application.persistance.dao.user.UserDAOImpl;
import com.cda25.springboot.square_games.application.persistance.dao.user.UserSquareGames;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class DAOServiceImpl implements DAOService {

    private final Collection<UserDAOImpl> mySQLUserDAOs = new ArrayList<>();

    @Override
    public UserDAO createUser(UserSquareGames userSquareGames) {
        mySQLUserDAOs.add(new UserDAOImpl(userSquareGames));
        return null;
    }

}
