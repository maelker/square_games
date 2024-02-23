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
        List<UserApp> userAppList = userApps.stream().filter(userApp -> Objects.equals(userApp.getId().toString(), id)).toList();
        return userAppList.isEmpty() ? null : userAppList.getFirst();
    }

    @Override
    public UserApp addUser(UserApp user) {
        return userApps.add(user) ? user : null;
    }

    @Override
    public UserApp updateUser(UserApp user, String userId) {
        return userApps.contains(getUserById(userId)) ? userApps.stream().filter(userApp -> Objects.equals(userApp.getId().toString(), userId)).map(userApp -> user).iterator().next() : null;
    }

    @Override
    public UserApp deleteUser(String id) {
        UserApp userApp = getUserById(id);
        return userApps.remove(userApp) ? userApp : null;
    }
}
