package de.layla.jdbcgui.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnection {

    private final String url = "jdbc:mysql://localhost/versand?";

    public Connection connect(String user, String password) {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean createNewUser(String username, String password) {
        try {
            Connection connection = connectAsRoot();
            String createUser = "CREATE USER ?@localhost IDENTIFIED BY ?;";
            assert connection != null;
            PreparedStatement createUserStatement = connection.prepareStatement(createUser);
            createUserStatement.setString(1, username);
            createUserStatement.setString(2, password);
            createUserStatement.execute();

            String grantPermissions = "GRANT ALL PRIVILEGES ON versand.* To ?@'localhost' IDENTIFIED BY ?;";
            PreparedStatement grantPermissionsStatement = connection.prepareStatement(grantPermissions);
            grantPermissionsStatement.setString(1, username);
            grantPermissionsStatement.setString(2, password);
            grantPermissionsStatement.execute();
            connection.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    private Connection connectAsRoot() {
        try {
            String user = "root";
            String password = "";
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            return null;
        }
    }
}
