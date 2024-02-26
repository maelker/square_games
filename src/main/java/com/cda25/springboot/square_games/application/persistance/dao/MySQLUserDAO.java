package com.cda25.springboot.square_games.application.persistance.dao;

import com.cda25.springboot.square_games.application.persistance.DbAccess;
import com.cda25.springboot.square_games.application.persistance.domainobject.UserDomObj;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Deprecated
@Service
public class MySQLUserDAO implements UserDAO {

    private static Connection con;

    Collection<UserDomObj> UserDomObjS = new ArrayList<>();

    private DbAccess dbAccess = DbAccess.getInstance();

    public MySQLUserDAO() {
        con = dbAccess.getConnectionDB();
    }

    @Override
    public List<UserDomObj> getAllUsers() {
        List<UserDomObj> userDomObjs = new ArrayList<>();

        try {
            String query = """
                    SELECT *
                    FROM users;
                    """;
            Statement stmt = con.createStatement();

            ResultSet res = stmt.executeQuery(query);

            while (res.next()) {
                userDomObjs.add(
                        new UserDomObj(
                                UUID.fromString(res.getString("id")),
                                res.getString("avatar"),
                                res.getDate("birthDate"),
                                res.getDate("creationDate"),
                                res.getString("idParent") == null ? null : UUID.fromString(res.getString("idParent")),
                                res.getString("login"),
                                res.getString("password"),
                                res.getString("mail"),
                                res.getString("firstName"),
                                res.getString("lastName"),
                                res.getString("favPayment"),
                                res.getString("city"),
                                res.getString("postalCode"),
                                res.getString("streetName"),
                                res.getString("streetNumber")
                        )
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return userDomObjs;
    }

    @Override
    public UserDomObj getUserById(String id) {
        UserDomObj userDomObj = null;

        try {
            String query = """
                    SELECT *
                    FROM users
                    WHERE id = ?;
                    """;
            PreparedStatement stmt = con.prepareStatement(query);

            stmt.setString(1, id);

            ResultSet res = stmt.executeQuery();

            while (res.next()) {
                userDomObj = new UserDomObj(
                        UUID.fromString(res.getString("id")),
                        res.getString("avatar"),
                        res.getDate("birthDate"),
                        res.getDate("creationDate"),
                        res.getString("idParent") == null ? null : UUID.fromString(res.getString("idParent")),
                        res.getString("login"),
                        res.getString("password"),
                        res.getString("mail"),
                        res.getString("firstName"),
                        res.getString("lastName"),
                        res.getString("favPayment"),
                        res.getString("city"),
                        res.getString("postalCode"),
                        res.getString("streetName"),
                        res.getString("streetNumber")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return userDomObj;
    }

    @Override
    public UserDomObj addUser(UserDomObj userDomObj) {
        int row = 0;
        try {
            String query = """
                    INSERT INTO users (id, avatar, birthDate, creationDate, idParent, login, password, mail, firstName, lastName, favPayment, city, postalCode, streetName, streetNumber)
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
                    """;
            PreparedStatement stmt = con.prepareStatement(query);

            stmt.setString(1, userDomObj.getId() == null ? null : userDomObj.getId().toString());
            stmt.setString(2, userDomObj.getAvatar() == null ? null : userDomObj.getAvatar());
            stmt.setDate(3, userDomObj.getBirthDate() == null ? null : new java.sql.Date(userDomObj.getBirthDate().getTime()));
            stmt.setDate(4, userDomObj.getCreationDate() == null ? null : new java.sql.Date(userDomObj.getCreationDate().getTime()));
            stmt.setString(5, userDomObj.getIdParent() == null ? null : userDomObj.getIdParent().toString());
            stmt.setString(6, userDomObj.getLogin() == null ? null : userDomObj.getLogin());
            stmt.setString(7, userDomObj.getPassword() == null ? null : userDomObj.getPassword());
            stmt.setString(8, userDomObj.getMail() == null ? null : userDomObj.getMail());
            stmt.setString(9, userDomObj.getFirstName() == null ? null : userDomObj.getFirstName());
            stmt.setString(10, userDomObj.getLastName() == null ? null : userDomObj.getLastName());
            stmt.setString(11, userDomObj.getFavPayment() == null ? null : userDomObj.getFavPayment());
            stmt.setString(12, userDomObj.getCity() == null ? null : userDomObj.getCity());
            stmt.setString(13, userDomObj.getPostalCode() == null ? null : userDomObj.getPostalCode());
            stmt.setString(14, userDomObj.getStreetName() == null ? null : userDomObj.getStreetName());
            stmt.setString(15, userDomObj.getStreetNumber() == null ? null : userDomObj.getStreetNumber());

            row = stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return row == 1 ? userDomObj : null;
    }

    @Override
    public UserDomObj updateUser(UserDomObj userDomObj, String userId) {
        int row = 0;
        try {
            String update = """
                UPDATE users
                SET password = ?
                WHERE id = ?
                AND mail = ?
                """;
            PreparedStatement stmt = con.prepareStatement(update);
            stmt.setString(1, userDomObj.getPassword());
            stmt.setString(2, userId);
            stmt.setString(3, userDomObj.getMail());
            row = stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return row == 1 ? userDomObj : null;
    }

    @Override
    public UserDomObj deleteUser(String id) {
        int row = 0;
        UserDomObj userDomObj = getUserById(id);
        try {
            String update = """
                DELETE FROM users
                WHERE id = ?
                """;
            PreparedStatement stmt = con.prepareStatement(update);
            stmt.setString(1, id);
            row = stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return row == 1 ? userDomObj : null;
    }
}
