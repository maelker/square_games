package com.cda25.springboot.square_games.application.persistance.dao;

import com.cda25.springboot.square_games.application.persistance.domainobject.UserDomObj;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service
public class JPAUserDAO implements UserDAO {

    /**
     * @return
     */
    @Override
    public List<UserDomObj> getAllUsers() {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public UserDomObj getUserById(String id) {
        return null;
    }

    /**
     * @param user
     * @return
     */
    @Override
    public UserDomObj addUser(UserDomObj user) {
        return null;
    }

    /**
     * @param user
     * @param userId
     * @return
     */
    @Override
    public UserDomObj updateUser(UserDomObj user, String userId) {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public UserDomObj deleteUser(String id) {
        return null;
    }
}
