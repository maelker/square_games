package com.cda25.springboot.square_games.application.persistance;


import com.cda25.springboot.square_games.application.persistance.user.UserRepositoryAddress;
import com.cda25.springboot.square_games.application.persistance.user.UserRepositoryInformations;
import com.cda25.springboot.square_games.application.persistance.user.UserRepositoryMain;
import com.cda25.springboot.square_games.application.persistance.user.domainobject.UserAddressR;
import com.cda25.springboot.square_games.application.persistance.user.domainobject.UserInformationR;
import com.cda25.springboot.square_games.application.persistance.user.domainobject.UserMainR;
import com.cda25.springboot.square_games.application.persistance.user.domainobject.UserR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Primary
@Service
public class DAOServiceJDBC implements DAOService {



    @Autowired
    private UserRepositoryMain userRepositoryMain;

    @Autowired
    private UserRepositoryInformations userRepositoryInformations;

    @Autowired
    private UserRepositoryAddress userRepositoryAddress;

    @Override
    public UserR createUser(UserR userR) {
        userRepositoryMain.save(userR.userMainR());
        userRepositoryInformations.save(userR.userInformation());
        userRepositoryAddress.save(userR.userAddress());
        return userR;
    }

    @Override
    public Collection<UserR> getAllUsers() {
        Collection<UserR> userRCollection = new ArrayList<>();
        Collection<UserMainR> userMainRCollection = new ArrayList<>();
        userRepositoryMain.findAll().forEach(userMainRCollection::add);
        Collection<UserInformationR> userInformationRCollection = new ArrayList<>();
        userRepositoryInformations.findAll().forEach(userInformationRCollection::add);
        Collection<UserAddressR> userAddressRCollection = new ArrayList<>();
        userRepositoryAddress.findAll().forEach(userAddressRCollection::add);
        for (int u = 0; u < userMainRCollection.size(); u++) {
            UserMainR uM = userMainRCollection.iterator().next();
            UserInformationR uI = userInformationRCollection
                    .stream()
                    .filter(
                            userInformationR -> userInformationR.id() == uM.id()
                    )
                    .findFirst().get();
            UserAddressR uA = userAddressRCollection
                    .stream()
                    .filter(
                            userAddressR -> userAddressR.id() == uM.id()
                    )
                    .findFirst().get();
            userRCollection.add(new UserR(uM, uI, uA));
        }
        return userRCollection;
    }

    @Override
    public UserR getUserFromId(String userId) {
        Optional<UserMainR> userMainR = userRepositoryMain.findById(Integer.parseInt(userId));
        Optional<UserInformationR> userInformationR = userRepositoryInformations.findById(Integer.parseInt(userId));
        Optional<UserAddressR> userAddressR = userRepositoryAddress.findById(Integer.parseInt(userId));
        return UserR.createUserImpl(userMainR.orElse(null), userInformationR.orElse(null), userAddressR.orElse(null));
    }

    @Override
    public UserR updateUser(UserR user, String userId) {
        UserMainR userMainR = userRepositoryMain.findById(Integer.parseInt(userId)).orElse(null);
        UserInformationR userInformationR = userRepositoryInformations.findById(Integer.parseInt(userId)).orElse(null);
        UserAddressR userAddressR = userRepositoryAddress.findById(Integer.parseInt(userId)).orElse(null);
        return getUserR(userMainR, userInformationR, userAddressR);
    }

    @Override
    public UserR deleteUser(String userId) {
        UserMainR userMainR = userRepositoryMain.findById(Integer.parseInt(userId)).orElse(null);
        UserInformationR userInformationR = userRepositoryInformations.findById(Integer.parseInt(userId)).orElse(null);
        UserAddressR userAddressR = userRepositoryAddress.findById(Integer.parseInt(userId)).orElse(null);
        return getUserR(userMainR, userInformationR, userAddressR);
    }

    private UserR getUserR(UserMainR userMainR, UserInformationR userInformationR, UserAddressR userAddressR) {
        UserR userRUpdated = null;
        if (userMainR != null && userInformationR != null && userAddressR != null) {
            userRUpdated = new UserR(userMainR, userInformationR, userAddressR);
            userRepositoryMain.save(userRUpdated.userMainR());
            userRepositoryInformations.save(userRUpdated.userInformation());
            userRepositoryAddress.save(userRUpdated.userAddress());
        }
        return userRUpdated;
    }

}
