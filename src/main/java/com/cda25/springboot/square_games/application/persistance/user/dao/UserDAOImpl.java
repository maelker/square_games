package com.cda25.springboot.square_games.application.persistance.user.dao;

import com.cda25.springboot.square_games.application.persistance.user.UserR;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Component
public class UserDAOImpl implements UserDAO {

    Collection<UserR> userRS = new ArrayList<>();

    @Override
    public List<UserR> getAllUsers() {
        return userRS.stream().toList();
    }

    @Override
    public UserR getUserById(String id) {
        List<UserR> userRList = userRS.stream().filter(UserImpl -> Objects.equals(UserImpl.id().toString(), id)).toList();
        return userRList.isEmpty() ? null : userRList.getFirst();
    }

    @Override
    public UserR addUser(UserR user) {
        return userRS.add(user) ? user : null;
    }

    @Override
    public UserR updateUser(UserR user, String userId) {
        return userRS.contains(getUserById(userId)) ? userRS.stream().filter(userImpl -> Objects.equals(userImpl.id().toString(), userId)).map(UserImpl -> user).iterator().next() : null;
    }

    @Override
    public UserR deleteUser(String id) {
        UserR UserR = getUserById(id);
        return userRS.remove(UserR) ? UserR : null;
    }
}
