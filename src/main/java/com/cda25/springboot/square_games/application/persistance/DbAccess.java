package com.cda25.springboot.square_games.application.persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbAccess {

    private static DbAccess instance;

    private static Connection con;

    public static DbAccess getInstance() {
        if (instance == null) instance = new DbAccess();
        return instance;
    }

    private DbAccess() {
    }

    public Connection getConnectionDB() {
        try {
            if (con == null) {
                String urlDb = "jdbc:mysql://localhost:6603/square_games";
                String usernameDb = "root";
                String passwordDb = "helloworld";
                con = DriverManager.getConnection(
                        urlDb,
                        usernameDb,
                        passwordDb);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }
}
