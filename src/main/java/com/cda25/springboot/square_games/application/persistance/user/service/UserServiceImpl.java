package com.cda25.springboot.square_games.application.persistance.user.service;

import com.cda25.springboot.square_games.application.persistance.user.AddressRepository;
import com.cda25.springboot.square_games.application.persistance.user.UserRepository;
import com.cda25.springboot.square_games.application.persistance.user.domain_obj.UserDomObj;
import com.cda25.springboot.square_games.application.persistance.user.domain_obj.address.AddressDomObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;


    /**
     * @return
     */
    @Override
    public Map<UserDomObj, AddressDomObj> findAll() {
        Collection<UserDomObj> userDomObjs = userRepository.findAll();
        Map<UserDomObj, AddressDomObj> userDomObjAddressDomObjMap = new HashMap<>();
        userDomObjs.stream()
                .map(
                        userDomObj -> userDomObjAddressDomObjMap.put(
                                userDomObj,
                                addressRepository.findById(userDomObj.getAddressId()).orElse(null)
                        )
                );
        return userDomObjAddressDomObjMap;
    }

    /**
     * @param userDomObj
     * @return
     */
    @Override
    public UserDomObj save(UserDomObj userDomObj, AddressDomObj addressDomObj) {
        addressRepository.save(addressDomObj);
        return userRepository.save(userDomObj);
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public UserDomObj findUserById(UUID userId) {
        return userRepository.findById(userId).orElse(null);
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public AddressDomObj findAddressById(UUID userId) {
        return addressRepository.findById(userId).orElse(null);
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public void deleteById(UUID userId) {
        userRepository.deleteById(userId);
    }
}
