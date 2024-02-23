package com.cda25.springboot.square_games.application.persistance.user;

import java.util.UUID;

public class UserImpl implements UserApp {

    private UUID id;
    private String firstName;
    private String lastName;

    public UserImpl(String firstName, String lastName) {
        this.id = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }
}
