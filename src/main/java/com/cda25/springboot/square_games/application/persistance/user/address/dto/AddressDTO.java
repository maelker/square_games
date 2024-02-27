package com.cda25.springboot.square_games.application.persistance.user.address.dto;

import com.cda25.springboot.square_games.application.persistance.user.address.domain_object.AddressDomObj;

public record AddressDTO(
        String city,
        String postalCode,
        String streetName,
        String streetNumber
) {

    public static AddressDTO createAddressDTO(AddressDomObj addressDomObj) {

        return addressDomObj == null ? null : new AddressDTO(
                addressDomObj.getCity(),
                addressDomObj.getPostalCode(),
                addressDomObj.getStreetName(),
                addressDomObj.getStreetNumber()
        );
    }
}
