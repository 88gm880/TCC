package org.openjfx.model.dao;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@Component
public class LoginDAO {

    private DbConnection dbConnection = new DbConnection();
    private Connection connection;

    public LoginDAO() {
        this.connection = dbConnection.getConnection();//connection;
    }

    public boolean isValidLogin(String username, String password) {
        String query = "SELECT COUNT(1) FROM LOGIN " +
                "WHERE USER_LOGIN = '" + username + "' " +
                "AND USER_PWD = '" + password + "';";

        try {
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(query);

            while (queryResult.next()) {
                return queryResult.getInt(1) == 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return false;
    }
}
