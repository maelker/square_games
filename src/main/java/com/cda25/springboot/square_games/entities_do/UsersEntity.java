package com.cda25.springboot.square_games.entities_do;

import com.cda25.springboot.square_games.dto.users.UsersDTO;
import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "users")
public class UsersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "birth_date")
    private Date birthDate;
    @Column(name = "creation_date")
    private Date creationDate;
    @Column(name = "id_parent")
    private UUID idParent;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "mail")
    private String mail;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "fav_payment")
    private String favPayment;
    @Column(name = "address_city")
    private String city;
    @Column(name = "address_postal_code")
    private String postalCode;
    @Column(name = "address_street_name")
    private String streetName;
    @Column(name = "address_street_number")
    private String streetNumber;

    public UsersEntity() {
    }

    public UsersEntity(String avatar, Date birthDate, Date creationDate, UUID idParent, String login, String password, String mail, String firstName, String lastName, String favPayment, String city, String postalCode, String streetName, String streetNumber) {
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

    public UsersEntity(UsersDTO userDTO) {
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

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public void setAll(UsersDTO userDTO) {
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
}
