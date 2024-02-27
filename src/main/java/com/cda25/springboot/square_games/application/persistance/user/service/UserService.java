package com.cda25.springboot.square_games.application.persistance.user.service;

import com.cda25.springboot.square_games.application.persistance.user.domain_obj.UserDomObj;
import com.cda25.springboot.square_games.application.persistance.user.domain_obj.address.AddressDomObj;

import java.util.Map;
import java.util.UUID;

public interface UserService {
    Map<UserDomObj, AddressDomObj> findAll();
    UserDomObj save(UserDomObj userDomObj, AddressDomObj addressDomObj);
    UserDomObj findUserById(UUID userId);

    AddressDomObj findAddressById(UUID addressId);
    void deleteById(UUID userId);
}
