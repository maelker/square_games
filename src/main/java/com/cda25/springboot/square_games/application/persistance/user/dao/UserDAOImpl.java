package com.cda25.springboot.square_games.application.persistance.user.dao;

import com.cda25.springboot.square_games.application.persistance.user.domainobject.UserDomObj;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
public class UserDAOImpl implements UserDAO {

    Collection<UserDomObj> UserDomObjS = new ArrayList<>();

    @Override
    public List<UserDomObj> getAllUsers() {
        return UserDomObjS
                .stream()
                .toList();
    }

    @Override
    public UserDomObj getUserById(String id) {
        List<UserDomObj> userDomObj = UserDomObjS
                .stream()
                .filter(
                        UserImpl -> Objects.equals(UserImpl.id().toString(), id)
                )
                .toList();
        return userDomObj.isEmpty()
                ?
                null
                :
                userDomObj.getFirst();
    }

    @Override
    public UserDomObj addUser(UserDomObj userDomObj) {
        return UserDomObjS.add(userDomObj)
                ?
                userDomObj
                :
                null;
    }

    @Override
    public UserDomObj updateUser(UserDomObj userDomObj, String userId) {
        return UserDomObjS.contains(getUserById(userId))
                ?
                UserDomObjS.stream()
                        .filter(
                                userImpl -> Objects.equals(userImpl.id().toString(), userId)
                        )
                        .map(UserImpl -> userDomObj)
                        .iterator()
                        .next()
                :
                null;
    }

    @Override
    public UserDomObj deleteUser(String id) {
        UserDomObj userDomObj = getUserById(id);
        return UserDomObjS.remove(userDomObj)
                ?
                userDomObj
                :
                null;
    }
}
