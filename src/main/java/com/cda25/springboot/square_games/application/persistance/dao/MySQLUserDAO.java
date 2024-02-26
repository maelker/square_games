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
                        UserDomObj.createUserDomObj(
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
                userDomObj = UserDomObj.createUserDomObj(
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

            stmt.setString(1, userDomObj.id() == null ? null : userDomObj.id().toString());
            stmt.setString(2, userDomObj.avatar() == null ? null : userDomObj.avatar());
            stmt.setDate(3, userDomObj.birthDate() == null ? null : new java.sql.Date(userDomObj.birthDate().getTime()));
            stmt.setDate(4, userDomObj.creationDate() == null ? null : new java.sql.Date(userDomObj.creationDate().getTime()));
            stmt.setString(5, userDomObj.idParent() == null ? null : userDomObj.idParent().toString());
            stmt.setString(6, userDomObj.login() == null ? null : userDomObj.login());
            stmt.setString(7, userDomObj.password() == null ? null : userDomObj.password());
            stmt.setString(8, userDomObj.mail() == null ? null : userDomObj.mail());
            stmt.setString(9, userDomObj.firstName() == null ? null : userDomObj.firstName());
            stmt.setString(10, userDomObj.lastName() == null ? null : userDomObj.lastName());
            stmt.setString(11, userDomObj.favPayment() == null ? null : userDomObj.favPayment());
            stmt.setString(12, userDomObj.city() == null ? null : userDomObj.city());
            stmt.setString(13, userDomObj.postalCode() == null ? null : userDomObj.postalCode());
            stmt.setString(14, userDomObj.birthDate() == null ? null : userDomObj.streetName());
            stmt.setString(15, userDomObj.streetNumber() == null ? null : userDomObj.streetNumber());

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
            stmt.setString(1, userDomObj.password());
            stmt.setString(2, userId);
            stmt.setString(3, userDomObj.mail());
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
