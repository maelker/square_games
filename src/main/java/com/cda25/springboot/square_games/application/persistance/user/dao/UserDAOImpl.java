package com.cda25.springboot.square_games.application.persistance.user.dao;

import com.cda25.springboot.square_games.application.persistance.user.UserApp;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Component
public class UserDAOImpl implements UserDAO {

    Collection<UserApp> userApps = new ArrayList<>();

    @Override
    public List<UserApp> getAllUsers() {
        return userApps.stream().toList();
    }

    @Override
    public UserApp getUserById(String id) {
        return userApps.stream().filter(userApp -> Objects.equals(userApp.getId().toString(), id)).toList().getFirst();
    }

    @Override
    public void addUser(UserApp user) {
        userApps.add(user);
    }

    @Override
    public void updateUser(UserApp user) {
        userApps.stream().filter(userApp -> userApp.getId() == user.getId()).map(userApp -> user);
    }

    @Override
    public void deleteUser(String id) {
        userApps.remove(getUserById(id));
    }
}
