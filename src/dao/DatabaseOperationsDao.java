package dao;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseOperationsDao {

    private Connection connection;

    public DatabaseOperationsDao() throws SQLException, ClassNotFoundException {
        connection = DBConnection.getInstance().getConnection();
    }

    // Method to insert data into the database
    public boolean insertData(String query, Object... parameters) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // Set parameters
            for (int i = 0; i < parameters.length; i++) {
                preparedStatement.setObject(i + 1, parameters[i]);
            }
            // Execute the insert statement
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception appropriately
            return false;
        }
    }

    // Method to retrieve data from the database
    public ResultSet retrieveData(String query, Object... parameters) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            // Set parameters
            for (int i = 0; i < parameters.length; i++) {
                preparedStatement.setObject(i + 1, parameters[i]);
            }
            // Execute the query and return the result set
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception appropriately
            return null;
        }
    }

}
