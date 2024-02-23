package com.cda25.springboot.square_games.application.persistance;

import com.cda25.springboot.square_games.application.persistance.dao.user.UserDAO;
import com.cda25.springboot.square_games.application.persistance.dao.user.UserSquareGames;

public interface DAOService {

    UserDAO createUser(UserSquareGames userSquareGames);

}
