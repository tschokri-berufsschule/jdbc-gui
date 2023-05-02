package de.layla.jdbcgui.db;

import java.sql.*;

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

            assert connection != null;

            if (!userExists(connection, username)) {
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
            }
        } catch (SQLException e) {
            return false;
        }
        return false;
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

    private boolean userExists(Connection connection, String username) {
        try {
            String userLookup = "SELECT USER FROM mysql.user WHERE USER = ?";
            PreparedStatement statement = connection.prepareStatement(userLookup);
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();

            int count = 1;

            while (rs.next()) {
                if (rs.getString(count).equals(username)) {
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
