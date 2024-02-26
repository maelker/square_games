package com.cda25.springboot.square_games.application.persistance;

import com.cda25.springboot.square_games.application.persistance.user.domainobject.UserR;

import java.util.Collection;

public interface DAOService {

    UserR createUser(UserR UserR);

    Collection<UserR> getAllUsers();

    UserR getUserFromId(String userId);

    UserR deleteUser(String userId);

    UserR updateUser(UserR user, String userId);

}
