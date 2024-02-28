package com.cda25.springboot.square_games.application.persistance.user.dto.address;

import com.cda25.springboot.square_games.application.persistance.user.domain_obj.address.AddressDomObj;

import java.util.UUID;

public record AddressDTO(
        UUID id,
        String city,
        String postalCode,
        String streetName,
        String streetNumber
) {

    public static AddressDTO createAddressDTO(AddressDomObj addressDomObj) {

        return addressDomObj == null ? null : new AddressDTO(
                addressDomObj.getId(),
                addressDomObj.getCity(),
                addressDomObj.getPostalCode(),
                addressDomObj.getStreetName(),
                addressDomObj.getStreetNumber()
        );
    }
}
