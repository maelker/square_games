package com.cda25.springboot.square_games.application.persistance;

import com.cda25.springboot.square_games.application.persistance.user.UserApp;

import java.util.Collection;

public interface DAOService {

    UserApp createUser(UserApp userApp);

    Collection<UserApp> getAllUsers();

    UserApp getUserFromId(int userId);
}
