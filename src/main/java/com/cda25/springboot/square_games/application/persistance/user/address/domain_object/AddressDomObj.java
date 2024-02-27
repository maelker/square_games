package com.cda25.springboot.square_games.application.persistance.user.address.domain_object;

import com.cda25.springboot.square_games.application.persistance.user.address.dto.AddressDTO;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "address")
public class AddressDomObj {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    @Column(name = "address_city")
    private String city;
    @Column(name = "address_postal_code")
    private String postalCode;
    @Column(name = "address_street_name")
    private String streetName;
    @Column(name = "address_street_number")
    private String streetNumber;

    public AddressDomObj() {
    }

    public AddressDomObj(String city, String postalCode, String streetName, String streetNumber) {
        this.city = city;
        this.postalCode = postalCode;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
    }

    public AddressDomObj(AddressDTO addressDTO) {
        this.city = addressDTO.city();
        this.postalCode = addressDTO.postalCode();
        this.streetName = addressDTO.streetName();
        this.streetNumber = addressDTO.streetNumber();
    }

    public UUID getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }


    public void setAll(AddressDTO addressDTO) {
        this.city = addressDTO.city();
        this.postalCode = addressDTO.postalCode();
        this.streetName = addressDTO.streetName();
        this.streetNumber = addressDTO.streetNumber();
    }
}
