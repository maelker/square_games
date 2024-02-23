package com.cda25.springboot.square_games.application.persistance.user;

import java.util.UUID;

public interface UserApp {

    UUID getId();

    String getLastName();

    String getFirstName();
}
