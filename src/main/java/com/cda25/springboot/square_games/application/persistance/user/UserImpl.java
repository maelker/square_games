package com.cda25.springboot.square_games.application.persistance.user;

public class UserImpl implements UserApp {

    private int id = 0;
    private String firstName = "Ernest";
    private String lastName = "Vep";

    public UserImpl(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public int getId() {
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
