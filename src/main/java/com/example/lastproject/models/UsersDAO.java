package com.example.lastproject.models;

import com.example.lastproject.entities.Users;
import com.example.lastproject.repositories.Repo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsersDAO extends Repo<Users> {

    @Override
    public void insert(Users users) {

    }

    @Override
    public void update(Users users) {

    }

    @Override
    public ObservableList<Users> getAll() throws SQLException {
        ObservableList<Users> users = FXCollections.observableArrayList();
        String query = "SELECT * FROM users";
        Users user = null;
        Connection connection = getConnection();

        Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery(query);

        while (rs.next()) {
            user = new Users(rs.getInt(1), rs.getString(2), rs.getString(3),
                    rs.getInt(4), rs.getString(5), rs.getString(6),
                    rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
            users.add(user);
        }
        connection.close();
        statement.close();
        rs.close();
        return users;
    }
}
