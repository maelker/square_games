package com.cda25.springboot.square_games.application.persistance.user.dao;

import com.cda25.springboot.square_games.application.persistance.user.domainobject.UserDomObj;

import java.util.List;

public interface UserDAO {
    List<UserDomObj> getAllUsers();
    public UserDomObj getUserById(String id);
    public UserDomObj addUser(UserDomObj user);
    public UserDomObj updateUser(UserDomObj user, String userId);
    public UserDomObj deleteUser(String id);

}
