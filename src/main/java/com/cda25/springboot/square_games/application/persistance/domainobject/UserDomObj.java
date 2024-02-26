package com.cda25.springboot.square_games.application.persistance.domainobject;

import com.cda25.springboot.square_games.application.persistance.dto.UserDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;
import java.util.UUID;

@Entity
public class UserDomObj {
    @Id
    private UUID id;
    private String avatar;
    private Date birthDate;
    private Date creationDate;
    private UUID idParent;
    private String login;
    private String password;
    private String mail;
    private String firstName;
    private String lastName;
    private String favPayment;
    private String city;
    private String postalCode;
    private String streetName;
    private String streetNumber;

    public UserDomObj() {
    }

    public UserDomObj(UUID id, String avatar, Date birthDate, Date creationDate, UUID idParent, String login, String password, String mail, String firstName, String lastName, String favPayment, String city, String postalCode, String streetName, String streetNumber) {
        this.id = id == null ? UUID.randomUUID() : id;
        this.avatar = avatar;
        this.birthDate = birthDate;
        this.creationDate = creationDate;
        this.idParent = idParent;
        this.login = login;
        this.password = password;
        this.mail = mail;
        this.firstName = firstName;
        this.lastName = lastName;
        this.favPayment = favPayment;
        this.city = city;
        this.postalCode = postalCode;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
    }

    public UserDomObj(UserDTO userDTO) {
        this.id = userDTO.id() == null ? UUID.randomUUID() : userDTO.id();
        this.avatar = userDTO.avatar();
        this.birthDate = userDTO.birthDate();
        this.creationDate = userDTO.creationDate();
        this.idParent = userDTO.idParent();
        this.login = userDTO.login();
        this.password = userDTO.password();
        this.mail = userDTO.mail();
        this.firstName = userDTO.firstName();
        this.lastName = userDTO.lastName();
        this.favPayment = userDTO.favPayment();
        this.city = userDTO.city();
        this.postalCode = userDTO.postalCode();
        this.streetName = userDTO.streetName();
        this.streetNumber = userDTO.streetNumber();
    }

    public UUID getId() {
        return id;
    }

    public String getAvatar() {
        return avatar;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public UUID getIdParent() {
        return idParent;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getMail() {
        return mail;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFavPayment() {
        return favPayment;
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
}
